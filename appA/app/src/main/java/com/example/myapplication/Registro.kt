package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.ApiService
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.api.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val nombreCompletoEditText: EditText = findViewById(R.id.registronombre)
        val numeroTelefonoEditText: EditText = findViewById(R.id.registrotelefono)
        val contrasenaEditText: EditText = findViewById(R.id.registrocontrasena)
        val aceptarButton: Button = findViewById(R.id.aceptarregistro)

        aceptarButton.setOnClickListener {
            val nombreCompleto = nombreCompletoEditText.text.toString()
            val numeroTelefono = numeroTelefonoEditText.text.toString().toLong()
            val contrasena = contrasenaEditText.text.toString()

            if (nombreCompleto.isBlank()) {
                nombreCompletoEditText.error = "Debe llenar este campo"
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(numeroTelefono.toString())) {
                numeroTelefonoEditText.error = "Debe llenar este campo"
                return@setOnClickListener
            }

            if (contrasena.isBlank()) {
                contrasenaEditText.error = "Debe llenar este campo"
                return@setOnClickListener
            }

            if (contrasena.length < 8) {
                contrasenaEditText.error = "La contraseña debe tener al menos 8 caracteres"
                return@setOnClickListener
            }

            val user = User(nombreCompleto, numeroTelefono, contrasena)

            GlobalScope.launch(Dispatchers.IO) {
                val call = RetrofitClient.apiService.registerUser(user)
                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Log.d("Registro", "Registro exitoso")
                            // Guardar los datos del cliente en SharedPreferences
                            val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
                            with(sharedPreferences.edit()) {
                                putString("cliente_nombre", nombreCompleto)
                                putInt("cliente_puntos", 0) // Inicialmente, los puntos pueden ser 0
                                putString("codigo_barras", "xxxxxxxxxxxxx")
                                apply()
                            }

                            // Iniciar la actividad Inciador con los datos del cliente
                            startActivity(Intent(this@Registro, Iniciosecion::class.java))
                        } else {
                            Log.e("Registro", "Error en la respuesta: ${response.code()}")
                            runOnUiThread {
                                Toast.makeText(
                                    this@Registro,
                                    "Ya existe un usuario con este numero de telefono. Intentelo con otro numero de telefono.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.e("Registro", "Error en la solicitud", t)
                        runOnUiThread {
                            Toast.makeText(
                                this@Registro,
                                "Error de conexión. Verifique su red y vuelva a intentarlo.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            }

        }
    }
}