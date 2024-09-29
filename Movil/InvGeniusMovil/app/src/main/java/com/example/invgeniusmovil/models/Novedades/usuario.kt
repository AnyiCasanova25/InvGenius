package com.example.invgeniusmovil.models.Novedades



data class User(
    val nombre: String,
    val apellido: String,
    val documentos: String,
    val celular: Int,
    val correo: String,
    val contraseña: String,
    val confirmar_contraseña: String,
    val rol: Boolean,

)

