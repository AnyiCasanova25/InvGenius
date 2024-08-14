package com.example.invgeniusmovil.adapters.Novedades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.invgeniusmovil.R
import com.example.invgeniusmovil.models.Novedades.bajo_stock

data class adapterBajoStock(
    private val listNovedadesBajoStock: List<bajo_stock>
) : RecyclerView.Adapter<adapterBajoStock.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblProductoBajoStock: TextView = itemView.findViewById(R.id.lblProductoBajoStock)
        val lblNombreProducto: TextView = itemView.findViewById(R.id.lblNombreProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_novedades_productos_bajo_stock, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val novedadesBajoStock = listNovedadesBajoStock[position]
        holder.lblProductoBajoStock.text = novedadesBajoStock.num
        holder.lblNombreProducto.text = novedadesBajoStock.producto
    }

    override fun getItemCount(): Int = listNovedadesBajoStock.size
}