package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Localizacion : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var txtLatitud: TextView
    private lateinit var txtLongitud: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localizacion)

        txtLatitud = findViewById(R.id.txtlatitud)
        txtLongitud = findViewById(R.id.txtlongitud)

        // Coordenadas de la ubicación
        val veracruzLocation = LatLng(19.1966742, -96.1813035)
        txtLatitud.text = "Latitude: ${veracruzLocation.latitude}"
        txtLongitud.text = "Longitude: ${veracruzLocation.longitude}"

        val imageButtonIniciador: ImageButton = findViewById(R.id.imageButton11)
        imageButtonIniciador.setOnClickListener {
            val intent = Intent(this, Inciador::class.java)
            startActivity(intent)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as? SupportMapFragment

        if (mapFragment != null) {
            mapFragment.getMapAsync(this)
        } else {
            Toast.makeText(this, "Error loading map fragment", Toast.LENGTH_SHORT).show()
        }

        // Obtener el ID del cliente desde SharedPreferences (suponiendo que lo guardaste al iniciar sesión)
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val clientePuntos = sharedPreferences.getInt("cliente_puntos", 0)

        val puntosClienteTextView: TextView = findViewById(R.id.puntosCliente)
        puntosClienteTextView.text = "$clientePuntos"
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Coordenadas de la ubicación
        val veracruzLocation = LatLng(19.1966742, -96.1813035)

        // Añadir un marcador en la ubicación y mover la cámara
        mMap.addMarker(MarkerOptions().position(veracruzLocation).title("Av Úrsulo Galván 62, Tarimoya, Veracruz"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(veracruzLocation, 15f))
    }
}
