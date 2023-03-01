package com.example.retrofit_gettingstarted.api

import com.example.retrofit_gettingstarted.models.UserArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api/users")
    fun getUsersList(@Query("page") page: Int): Call<UserArray>

}