package com.example.invgeniusmovil.interfaces;

import com.example.invgeniusmovil.models.Novedades.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiService {
    @GET("api/v1/user")
    <fun>
    fun getUser(); Call<List<User>>

}
