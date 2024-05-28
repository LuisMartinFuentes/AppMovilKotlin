package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Inciador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inciador)

        // Obtener los datos del cliente desde SharedPreferences
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val clienteNombre = sharedPreferences.getString("cliente_nombre", "Usuario")
        val clientePuntos = sharedPreferences.getInt("cliente_puntos", 0)

        val convsersion = 10 //10 puntos equivalen a 1 peso
        val dineroV = clientePuntos / convsersion

        // Actualizar los TextView con los datos del cliente
        val nombreClienteTextView: TextView = findViewById(R.id.nombreCliente)
        val puntosClientesTextView: TextView = findViewById(R.id.puntosClientes)
        val puntosTextView: TextView = findViewById(R.id.puntos)
        val dineroTextView: TextView = findViewById(R.id.dinero)

        nombreClienteTextView.text = "Hola, ${clienteNombre?.split(" ")?.get(0)}" // Solo primer nombre
        puntosClientesTextView.text = "Puntos: $clientePuntos"
        puntosTextView.text = "$clientePuntos"
        dineroTextView.text = "$$dineroV"

        // Configurar listeners de clic para los ImageButton
        val imageButtonProductos: ImageButton = findViewById(R.id.imageView11)
        val imageButtonLocalizacion: ImageButton = findViewById(R.id.imageLocalizacion)
        val imageButtonCupones: ImageButton = findViewById(R.id.imageCupones)
        val imageButtonAjustes: ImageButton = findViewById(R.id.botonajustes1)
        val imageButtonTarjeta: ImageButton = findViewById(R.id.botontarjeta1)

        imageButtonLocalizacion.setOnClickListener {
            val intent = Intent(this, Localizacion::class.java)
            startActivity(intent)
        }

        imageButtonCupones.setOnClickListener {
            val intent = Intent(this, Cupones::class.java)
            startActivity(intent)
        }

        imageButtonTarjeta.setOnClickListener {
            val intent = Intent(this, Tarjeta::class.java)
            startActivity(intent)
        }

        imageButtonAjustes.setOnClickListener {
            val intent = Intent(this, Ajustes2::class.java)
            startActivity(intent)
        }

        imageButtonProductos.setOnClickListener {
            val intent = Intent(this, Productos::class.java)
            startActivity(intent)
        }
    }
}