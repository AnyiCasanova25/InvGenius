package com.example.appinvgenius.Config

class config {

    companion object{
        val urlBase = "http://:8080/api/v1/"

        val urlLogin = urlBase + "public/user/login"

        val urlProductosCaducados = urlBase + "lote/" + "loteVencido/"

    }


}