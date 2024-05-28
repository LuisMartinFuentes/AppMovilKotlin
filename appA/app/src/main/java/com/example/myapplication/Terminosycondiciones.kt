package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Terminosycondiciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_terminosycondiciones)
        val imageButtonAjustes: ImageButton = findViewById(R.id.imageButton4)
        imageButtonAjustes.setOnClickListener {
            // Crear un Intent para navegar a la actividad de Pedido
            val intent = Intent(this, Ajustes2 ::class.java)
            startActivity(intent)
        }

    }
    }
