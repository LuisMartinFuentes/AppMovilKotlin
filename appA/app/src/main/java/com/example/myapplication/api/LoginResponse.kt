package com.example.myapplication.api

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val cliente: Cliente?,
    val puntos: Int?,
    val codigoBarras: String?
)

data class Cliente(
    val id: Int,
    val nombre_completo: String,
    val telefono: Long,
    val contrasenia: String,
    val createdAt: String,
    val updatedAt: String
)

data class Puntos(
    val id_cliente: Int,
    val cantidad: Int
)

data class CodigoBarras(
    val id_cliente: Int,
    val codigo: String
)

data class Cupon(
    val codigo: String,
    val descuento: Double,
    val validez: String
)

data class Producto(
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val cantidad_disponible: Int,
    val imagen: String?
)