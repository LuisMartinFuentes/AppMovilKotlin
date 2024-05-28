package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.api.CodigoBarras
import com.example.myapplication.api.Puntos
import com.example.myapplication.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Tarjeta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tarjeta)
        val imageButtonAjustes: ImageButton = findViewById(R.id.imageButton44)
        imageButtonAjustes.setOnClickListener {
            // Crear un Intent para navegar a la actividad de Pedido
            val intent = Intent(this, Inciador ::class.java)
            startActivity(intent)
        }
        // Obtener el ID del cliente desde SharedPreferences (suponiendo que lo guardaste al iniciar sesión)
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val clientePuntos = sharedPreferences.getInt("cliente_puntos", 0)
        val codigoBarras = sharedPreferences.getString("codigo_barras", "xxxxxxxxxxxxx")
        // Obtener referencias a los TextView donde se mostrarán los datos del cliente

        val puntosClienteTextView: TextView = findViewById(R.id.puntosCliente)
        val codigoBarrasTextView: TextView = findViewById(R.id.codigoBarras)


        puntosClienteTextView.text = "$clientePuntos"
        codigoBarrasTextView.text = "$codigoBarras"

    }
}
