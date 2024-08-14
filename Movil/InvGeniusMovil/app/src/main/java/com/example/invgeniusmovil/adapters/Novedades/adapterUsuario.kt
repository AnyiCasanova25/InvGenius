package com.example.invgeniusmovil.adapters.Novedades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.invgeniusmovil.R
import com.example.invgeniusmovil.models.Novedades.usuario

class adapterUsuario(
    private val listNovedadesDeUsuario: List<usuario>
) : RecyclerView.Adapter<adapterUsuario.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblNovedad: TextView = itemView.findViewById(R.id.lblNovedad)
        val lblFechaEnvio: TextView = itemView.findViewById(R.id.lblFechaEnvio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_novedades_de_usuario, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val novedadesDeUsuario = listNovedadesDeUsuario[position]
        holder.lblNovedad.text = novedadesDeUsuario.num
        holder.lblFechaEnvio.text = novedadesDeUsuario.date
    }

    override fun getItemCount(): Int = listNovedadesDeUsuario.size
}
