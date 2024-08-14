package com.example.invgeniusmovil.adapters.Novedades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.invgeniusmovil.R
import com.example.invgeniusmovil.models.Novedades.productos_a_caducar

class adapterProductosACaducar(
    private val listNovedadesDeProductosACaducar: List<productos_a_caducar>
) : RecyclerView.Adapter<adapterProductosACaducar.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblProductoACaducado: TextView = itemView.findViewById(R.id.lblProductoACaducado)
        val lblVencimientoProducto: TextView = itemView.findViewById(R.id.lblVencimientoProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_novedades_productos_a_caducar, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val novedadesDeProductosACaducar = listNovedadesDeProductosACaducar[position]
        holder.lblProductoACaducado.text = novedadesDeProductosACaducar.num
        holder.lblVencimientoProducto.text = novedadesDeProductosACaducar.date
    }

    override fun getItemCount(): Int = listNovedadesDeProductosACaducar.size
}