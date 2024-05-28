package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.Cupon

class CuponAdapter(private val cupones: List<Cupon>) : RecyclerView.Adapter<CuponAdapter.CuponViewHolder>() {

    class CuponViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val codigoTextView: TextView = view.findViewById(R.id.codigoCupon)
        val descuentoTextView: TextView = view.findViewById(R.id.descuentoCupon)
        val validezTextView: TextView = view.findViewById(R.id.validezCupon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuponViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cupon_item, parent, false)
        return CuponViewHolder(view)
    }

    override fun onBindViewHolder(holder: CuponViewHolder, position: Int) {
        val cupon = cupones[position]
        holder.codigoTextView.text = cupon.codigo
        holder.descuentoTextView.text = "${cupon.descuento}%"
        holder.validezTextView.text = cupon.validez.toString()
    }

    override fun getItemCount() = cupones.size
}