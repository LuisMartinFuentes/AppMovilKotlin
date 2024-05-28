package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.ImageButton


class Ajustes2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ajustes2)


        // Obtener referencias a los TextView de ayuda, términos y condiciones, y aviso de privacidad
        val textViewAyuda: TextView = findViewById(R.id.textAyudabotton)
        val textViewTerminos: TextView = findViewById(R.id.textTerminosTCond)
        val textViewAviso: TextView = findViewById(R.id.textAviso)

        // Obtener el ID del cliente desde SharedPreferences (suponiendo que lo guardaste al iniciar sesión)
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val clientePuntos = sharedPreferences.getInt("cliente_puntos", 0)

        val puntosClienteTextView: TextView = findViewById(R.id.puntosCliente)
        puntosClienteTextView.text = "$clientePuntos"

        // Configurar listener de clic para el TextView de ayuda
        textViewAyuda.setOnClickListener {
            // Crear un Intent para navegar a la actividad de Ayuda
            val intent = Intent(this, Ayuda2::class.java)
            startActivity(intent)
        }

        // Configurar listener de clic para el TextView de términos y condiciones
        textViewTerminos.setOnClickListener {
            // Crear un Intent para navegar a la actividad de Términos y Condiciones
            val intent = Intent(this, Terminosycondiciones::class.java)
            startActivity(intent)
        }

        // Configurar listener de clic para el TextView de aviso de privacidad
        textViewAviso.setOnClickListener {
            // Crear un Intent para navegar a la actividad de Aviso de Privacidad
            val intent = Intent(this, Avisodeprivacidad::class.java)
            startActivity(intent)
        }

        val imageButtonAjustes: ImageButton = findViewById(R.id.regresarinicio1)
        imageButtonAjustes.setOnClickListener {
            // Crear un Intent para navegar a la actividad de Pedido
            val intent = Intent(this, Inciador ::class.java)
            startActivity(intent)
        }

    }
}