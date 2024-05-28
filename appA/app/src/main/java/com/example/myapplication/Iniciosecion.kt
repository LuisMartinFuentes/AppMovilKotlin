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
import com.example.myapplication.api.LoginResponse
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.api.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Iniciosecion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciosecion)

        val numeroTelefonoEditText: EditText = findViewById(R.id.editTextPhone)
        val contraseniaEditText: EditText = findViewById(R.id.editTextPassword)
        val iniciarSesionButton: Button = findViewById(R.id.buttoniniciariniciador)

        iniciarSesionButton.setOnClickListener {
            val numeroTelefono = numeroTelefonoEditText.text.toString().toLong()
            val contrasenia = contraseniaEditText.text.toString()

            if (TextUtils.isEmpty(numeroTelefono.toString())) {
                numeroTelefonoEditText.error = "Debe llenar este campo"
                return@setOnClickListener
            }

            if (contrasenia.isBlank()) {
                contraseniaEditText.error = "Debe llenar este campo"
                return@setOnClickListener
            }

            val user = User("", numeroTelefono, contrasenia)

            GlobalScope.launch(Dispatchers.IO) {
                val call = RetrofitClient.apiService.loginUser(user)
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse != null && loginResponse.success) {
                                val cliente = loginResponse.cliente
                                val puntos = loginResponse.puntos
                                val codigobarra = loginResponse.codigoBarras
                                if (cliente != null && puntos != null) {
                                    val clienteId = cliente.id
                                    val clienteNombre = cliente.nombre_completo
                                    val clientePuntos = puntos ?: 0
                                    val codigoBarras = codigobarra ?: "xxxxxxxxxxxxx"
                                    // Guardar el ID del cliente y sus puntos en SharedPreferences u otro lugar
                                    val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
                                    with(sharedPreferences.edit()) {
                                    putInt("cliente_id", clienteId)
                                    putString("cliente_nombre", clienteNombre)
                                    putInt("cliente_puntos", clientePuntos)
                                    putString("codigo_barras", codigoBarras)
                                    apply()
                                    }
                                    // Iniciar la actividad de Inciador
                                    startActivity(Intent(this@Iniciosecion, Inciador::class.java))
                                } else {
                                    Log.e("InicioSesion", "Error: Cliente o puntos nulos")
                                    showToast("Error en los datos recibidos del servidor")
                                }
                            } else {
                                Log.e("InicioSesion", "Error: Inicio de sesión fallido")
                                showToast("Inicio de sesión fallido. Verifique sus credenciales.")
                            }
                        } else {
                            Log.e("InicioSesion", "Error en la respuesta: ${response.code()}")
                            showToast("Error en la respuesta del servidor")
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.e("InicioSesion", "Error en la solicitud", t)
                        showToast("Error de conexión. Verifique su red y vuelva a intentarlo.")
                    }
                })
            }
        }
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this@Iniciosecion, message, Toast.LENGTH_SHORT).show()
        }
    }
}