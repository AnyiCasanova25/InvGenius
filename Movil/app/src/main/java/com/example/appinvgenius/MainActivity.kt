package com.example.appinvgenius

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.appinvgenius.Config.config
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            iniciarSesion()
        }
    }

    private fun iniciarSesion() {
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val params = JSONObject()
        params.put("userName", username)
        params.put("password", password)

        val request = JsonObjectRequest(
            Request.Method.POST, config.urlLogin, params,
            Response.Listener { response ->
                try {
                    val token = response.getString("token")
                    Log.d("Login", "Token JWT: $token")

                    // Guardar el token JWT y navegar a la siguiente pantalla
                    val intent = Intent(this, navegacionvista::class.java)
                    intent.putExtra("TOKEN", token)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                // Verificar si el servidor envía un mensaje de error específico
                val statusCode = error.networkResponse?.statusCode
                val errorMessage = if (error.networkResponse?.data != null) {
                    try {
                        val responseBody = String(error.networkResponse.data, Charsets.UTF_8)
                        val jsonError = JSONObject(responseBody)
                        jsonError.getString("message")
                    } catch (e: Exception) {
                        "Error en el inicio de sesión. Por favor, inténtelo de nuevo."
                    }
                } else {
                    "Error en el inicio de sesión. Por favor, inténtelo de nuevo."
                }

                // Mostrar el mensaje adecuado según el codigo de estado
                if (statusCode == 401) {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        )

        requestQueue.add(request)
    }


    fun olvidarContrasena(view: View) {
        val intent = Intent(application, olvidar_contrasena::class.java)
        startActivity(intent)
    }
}
