package com.example.appinvgenius

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class navegacionvista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_navegacionvista)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun vistaPrevia(view: View) {
        val intent = Intent(application, vista_previa::class.java)
        startActivity(intent)
    }

    fun categoria(view: View) {
        val intent = Intent(application, categoria::class.java)
        startActivity(intent)
    }

    fun miperfil(view: View) {
        val intent = Intent(application, perfil::class.java)
        startActivity(intent)
    }

    fun actualizar(view: View) {
        val intent = Intent(application, cambiar_contrasena::class.java)
        startActivity(intent)
    }

    fun cerrarSesion(view: View) {
        val intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
    }
}