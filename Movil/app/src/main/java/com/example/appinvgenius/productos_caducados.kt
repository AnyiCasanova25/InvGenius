package com.example.appinvgenius

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.appinvgenius.Adapter.adapter_productos_caducados
import com.example.appinvgenius.Config.config

class productos_caducados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos_caducados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargar_productos_caducados() // Asegúrate de llamar a este método aquí
    }

    private fun cargar_productos_caducados() {
        try {
            val request = JsonArrayRequest(
                Request.Method.GET,
                config.urlProductosCaducados,
                null,
                { response ->
                    // Se crea y asocia una variable con el objeto de la vista
                    val recycler = findViewById<RecyclerView>(R.id.RVProductos_caducados)
                    recycler.layoutManager = LinearLayoutManager(this) // Usa 'this' para el contexto de la actividad

                    // Se crea el adaptador
                    val adapterProductos = adapter_productos_caducados(response, this) // Usa 'this' para el contexto de la actividad

                    // Se asocia el adaptador con el objeto
                    recycler.adapter = adapterProductos
                },
                { error ->
                    Toast.makeText(this, "Error en la consulta", Toast.LENGTH_LONG).show() // Usa 'this' para el contexto de la actividad
                }
            )
            val queue = Volley.newRequestQueue(this) // Usa 'this' para el contexto de la actividad
            queue.add(request)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun Volver(view: View) {
        val intent = Intent(application, vista_previa::class.java)
        startActivity(intent)
    }

}
