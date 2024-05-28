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

class Productos : AppCompatActivity() {

    private lateinit var productoAdapter: ProductoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var noProductosTextView: TextView
    private lateinit var puntosTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val imageButtonInciador: ImageButton = findViewById(R.id.imageButton123)
        puntosTextView = findViewById(R.id.puntosCliente)

        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val clientePuntos = sharedPreferences.getInt("cliente_puntos", 0)
        puntosTextView.text = "$clientePuntos"

        noProductosTextView = findViewById(R.id.noProductosTextView)
        recyclerView = findViewById(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        imageButtonInciador.setOnClickListener {
            // Crear un Intent para navegar a la actividad de Pedido
            val intent = Intent(this,Inciador ::class.java)
            startActivity(intent) }


        obtenerProductos()
    }

    private fun obtenerProductos() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getProductos()
                withContext(Dispatchers.Main) {
                    if (response.isEmpty()) {
                        noProductosTextView.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    } else {
                        noProductosTextView.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        productoAdapter = ProductoAdapter(response)
                        recyclerView.adapter = productoAdapter
                    }
                }
            } catch (e: Exception) {
                Log.e("Productos", "Error al obtener productos", e)
                withContext(Dispatchers.Main) {
                    noProductosTextView.text = "Error al cargar productos"
                    noProductosTextView.visibility = View.VISIBLE
                }
            }
        }
    }
}