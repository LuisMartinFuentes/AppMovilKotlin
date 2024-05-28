package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.api.Producto

class ProductoAdapter(private val productos: List<Producto>) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreProducto)
        val descripcionTextView: TextView = view.findViewById(R.id.descripcionProducto)
        val precioTextView: TextView = view.findViewById(R.id.precioProducto)
        val cantidadTextView: TextView = view.findViewById(R.id.cantidadProducto)
        val imagenImageView: ImageView = view.findViewById(R.id.imagenProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.producto_item, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombreTextView.text = producto.nombre
        holder.descripcionTextView.text = producto.descripcion
        holder.precioTextView.text = producto.precio.toString()
        holder.cantidadTextView.text = "Disponible: ${producto.cantidad_disponible}"
        if (producto.imagen != null) {
            Glide.with(holder.itemView.context)
                .load(producto.imagen)
                .into(holder.imagenImageView)
        } else {
            holder.imagenImageView.setImageResource(R.drawable.placeholder_image)
        }
    }

    override fun getItemCount() = productos.size
}