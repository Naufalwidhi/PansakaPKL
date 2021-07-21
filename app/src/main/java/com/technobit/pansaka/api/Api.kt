package com.technobit.pansaka.api

import com.technobit.pansaka.data.User
import retrofit2.Call
import retrofit2.http.*

interface Api {

    // -----------------------------------------------
    // Login
    // -----------------------------------------------
    @Headers(
        "key: x5fgFV9nK9UohrCeSDHO4LuHVLySNM4Y",
        "appId: 1"
    )
    @FormUrlEncoded
    @POST("/api/v1/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<User>

    // -----------------------------------------------
    // Dashboard
    // -----------------------------------------------
    @Headers(
        "key: x5fgFV9nK9UohrCeSDHO4LuHVLySNM4Y",
        "appId: 1"
    )
    @FormUrlEncoded
    @POST("/api/v1/login")
    fun summary(
        @Header("Authorization") token: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<User>
}