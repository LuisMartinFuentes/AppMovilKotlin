package com.example.myapplication.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("clientes/")
    fun registerUser(@Body user: User): Call<Void>

    @POST("clientes/login")
    fun loginUser(@Body user: User): Call<LoginResponse>

    @GET("clientes/{id}")
    fun getClienteById(@Path("id") id: Int): Call<Cliente>

    @GET("puntos/{id_cliente}")
    fun getPuntosById(@Path("id_cliente") idCliente: Int):Call<Puntos>

    @GET("codigo_barras/{id_cliente}")
    fun getCodigoBarrasCliente(@Path("id_cliente") idCliente: Int): Call<CodigoBarras>

    @GET("cupones/")
    suspend fun getCupones(): List<Cupon>

    @GET("productos/")
    suspend fun getProductos(): List<Producto>

}
