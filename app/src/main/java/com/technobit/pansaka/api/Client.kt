package com.technobit.pansaka.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    val  retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8000/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun myApiClient() = retrofit.create(Api::class.java)
}