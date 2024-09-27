package com.example.invgeniusmovil

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.invgeniusmovil.databinding.ActivityMainBinding
import com.example.invgeniusmovil.models.Novedades.User
import com.google.android.material.navigation.NavigationView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // Retrofit setup
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding setup
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.appBarMain.toolbar)

        // Setup DrawerLayout and NavigationView
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // AppBarConfiguration for navigation
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_novedades, R.id.nav_producto, R.id.nav_categoria, R.id.nav_miPerfil, R.id.nav_cambiarContrasena
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Initialize Retrofit
        initRetrofit()

        // Fetch data using Retrofit
        fetchUsers()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Initialize Retrofit
    private fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/")  // Ajusta la URL de la API
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Crear instancia de ApiService
        apiService = retrofit.create(ApiService::class.java)
    }

    // Fetch users data from the API
    private fun fetchUsers() {
        val call = apiService.getUsers()

        // Ejecutar la llamada de Retrofit
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    // Aquí puedes procesar los usuarios obtenidos
                    users?.forEach {
                        Log.d("MainActivity", "User: ${it.nombre} ${it.apellido} ${it.documentos} ${it.correo} ${it.celular} ${it.contraseña} ${it.confirmar_contraseña} ${it.rol}")
                    }
                } else {
                    Log.e("MainActivity", "Error en la respuesta: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("MainActivity", "Error en la llamada a la API", t)
            }
        })
    }
}
