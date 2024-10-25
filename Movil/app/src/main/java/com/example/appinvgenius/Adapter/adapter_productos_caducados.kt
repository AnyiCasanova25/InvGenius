package com.example.appinvgenius.Adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.appinvgenius.R


class adapter_productos_caducados(
    var listaLote: JSONArray,
    var context: Context,
) : RecyclerView.Adapter<adapter_productos_caducados.MyHolder>()
{
    /*
    Se crea la clase MyHolder
     */
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*
        Dentro de la clase MyHolder se crean las variables
        y se asocian los objetos de la vista item
         */
        lateinit var lblNumeroLote: TextView
        lateinit var lblFecha_de_Vencimiento: TextView

        init {
            lblNumeroLote = itemView.findViewById(R.id.lblNumeroLote)
            lblFecha_de_Vencimiento = itemView.findViewById(R.id.lblFecha_de_Vencimiento)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter_productos_caducados.MyHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_productos_caducados, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: adapter_productos_caducados.MyHolder, position: Int) {
        val lote = listaLote.getJSONObject(position)
        holder.lblNumeroLote.text = lote.optString("codigoLote", "N/A") // Ahora usas codigoLote
        holder.lblFecha_de_Vencimiento.text = lote.optString("fechaVencimiento", "N/A") // Ahora usas fechaVencimiento
    }


    /*
    getItemCount: retorna el número de elementos
    de la lista
     */
    override fun getItemCount(): Int {
        return listaLote.length()
    }
}