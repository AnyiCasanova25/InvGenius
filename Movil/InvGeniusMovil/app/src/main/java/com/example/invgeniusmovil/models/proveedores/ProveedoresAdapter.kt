package com.example.invgeniusmovil.models.proveedores

import android.app.Application
import android.content.Context
import android.media.ApplicationMediaCapabilities
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.invgeniusmovil.R
import org.json.JSONArray
import org.json.JSONObject

class ProveedoresAdapter(private var datos: List<Proveedores>) :
    RecyclerView.Adapter<ProveedoresAdapter.ViewHolder>() {
        lateinit var rv_listar:RecyclerView
        lateinit var ProveedoresArray:List<Proveedores>

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var documento: TextView = view.findViewById(R.id.txt_documento)
        var nombre: TextView = view.findViewById(R.id.txt_nombre)
        var apellido: TextView = view.findViewById(R.id.txt_apellido)
        var telefono: TextView = view.findViewById(R.id.txt_telefono)
        var empresa: TextView = view.findViewById(R.id.txt_empresa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_proveedor, parent, false)

        // solicitud de get
        val context: Context = parent.context
        val solicitud = Volley.newRequestQueue(context)
        val url = "http://10.192.80.173:8080/api/v1/proveedor/"
       //get
        ProveedoresArray= arrayListOf()
        val respuesta = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Manejar la respuesta exitosa
                val info=JSONObject(response)
                val proveedores=info.getJSONArray("proveedor")
                ProveedoresArray=(0 until proveedores.length()).map {i->
                    val fila= proveedores.getJSONObject(i)
                    Proveedores(
                        id_Proveedor=fila.getString("id"),
                        documentoProveedor=fila.getString("documento_proveedor"),
                        nombreProveedores=fila.getString("nombre_proveedor"),
                        apellidoProveedor=fila.getString("apellido_proveedor"),
                        telefonoProveedor=fila.getString("numero_proveedor"),
                        empresaProveedor=fila.getString("empresa_proveedor"),

                    )

                }
               val adaptador=ProveedoresAdapter(ProveedoresArray)
                rv_listar.layoutManager = LinearLayoutManager(context)
                rv_listar.adapter=adaptador
            },
            { err ->
                // Manejar el error
                println("Error: ${err.message}")
            }
        )

// Añadir la solicitud a la cola
        solicitud.add(respuesta)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.documento.text = datos[position].documentoProveedor
        holder.nombre.text = datos[position].nombreProveedores
        holder.apellido.text = datos[position].apellidoProveedor
        holder.telefono.text = datos[position].telefonoProveedor
        holder.empresa.text = datos[position].empresaProveedor
        // Se podrían manejar más operaciones aquí si es necesario
    }

    override fun getItemCount() = datos.size
}
