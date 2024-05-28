package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ayuda2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ayuda2)
        val imageButtonAjustes: ImageButton = findViewById(R.id.imageButton7)
        imageButtonAjustes.setOnClickListener {
            // Crear un Intent para navegar a la actividad de Pedido
            val intent = Intent(this, Ajustes2 ::class.java)
            startActivity(intent)
        }
        }
    }
