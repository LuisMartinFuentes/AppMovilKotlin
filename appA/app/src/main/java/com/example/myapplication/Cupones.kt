package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Cupones : AppCompatActivity() {

    private lateinit var cuponAdapter: CuponAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var puntosTextView: TextView
    private lateinit var noCuponesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cupones)

        puntosTextView = findViewById(R.id.puntosCliente)
        noCuponesTextView = findViewById(R.id.noCuponesTextView)
        recyclerView = findViewById(R.id.recyclerViewCupones)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val imageButtonInciador: ImageButton = findViewById(R.id.imageButton8)
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val clientePuntos = sharedPreferences.getInt("cliente_puntos", 0)
        puntosTextView.text = "$clientePuntos"

        imageButtonInciador.setOnClickListener {
            // Crear un Intent para navegar a la actividad de Pedido
            val intent = Intent(this,Inciador ::class.java)
            startActivity(intent) }

        obtenerCupones()
    }

    private fun obtenerCupones() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getCupones()
                withContext(Dispatchers.Main) {
                    if (response.isEmpty()) {
                        noCuponesTextView.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    } else {
                        noCuponesTextView.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        cuponAdapter = CuponAdapter(response)
                        recyclerView.adapter = cuponAdapter
                    }
                }
            } catch (e: Exception) {
                Log.e("Cupones", "Error al obtener cupones", e)
                withContext(Dispatchers.Main) {
                    noCuponesTextView.text = "Error al cargar cupones"
                    noCuponesTextView.visibility = View.VISIBLE
                }
            }
        }
    }
}