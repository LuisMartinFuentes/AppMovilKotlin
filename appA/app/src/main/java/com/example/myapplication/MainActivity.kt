

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iniciarSesionButton: Button = findViewById(R.id.buttonIniciar)
        val noTienesCuentaTextView: TextView = findViewById(R.id.retristrar_cuenta)

        iniciarSesionButton.setOnClickListener {
            // Abrir la interfaz de Inicio de Sesión
            val intent = Intent(this, Iniciosecion::class.java)
            startActivity(intent)
        }

        noTienesCuentaTextView.setOnClickListener {
            // Abrir la interfaz de Registro
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}
