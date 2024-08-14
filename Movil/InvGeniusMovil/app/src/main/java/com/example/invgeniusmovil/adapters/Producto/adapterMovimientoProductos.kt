package com.example.invgeniusmovil.adapters.Producto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.invgeniusmovil.R
import com.example.invgeniusmovil.models.Producto.movimiento_productos

data class adapterMovimientoProductos(
    private val listMovimientoProductos: List<movimiento_productos>
) : RecyclerView.Adapter<adapterMovimientoProductos.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblNombreProducto: TextView = itemView.findViewById(R.id.lblNombreProducto)
        val lblMarcaProducto: TextView = itemView.findViewById(R.id.lblMarcaProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_movimiento_productos, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val movimientoProductos = listMovimientoProductos[position]
        holder.lblNombreProducto.text = movimientoProductos.nombre
        holder.lblMarcaProducto.text = movimientoProductos.marca
    }

    override fun getItemCount(): Int = listMovimientoProductos.size
}