package com.example.invgeniusmovil.adapters.Novedades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.invgeniusmovil.R
import com.example.invgeniusmovil.models.Novedades.productos_caducados

class adapterProductosCaducados(
    private val listNovedadesDeProductosCaducados: List<productos_caducados>
) : RecyclerView.Adapter<adapterProductosCaducados.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblProductoCaducado: TextView = itemView.findViewById(R.id.lblProductoCaducado)
        val lblVencimiento: TextView = itemView.findViewById(R.id.lblVencimiento)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_novedades_productos_caducados, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val novedadesDeProductosCaducados = listNovedadesDeProductosCaducados[position]
        holder.lblProductoCaducado.text = novedadesDeProductosCaducados.num
        holder.lblVencimiento.text = novedadesDeProductosCaducados.date
    }

    override fun getItemCount(): Int = listNovedadesDeProductosCaducados.size
}