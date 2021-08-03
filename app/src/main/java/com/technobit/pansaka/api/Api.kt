package com.technobit.pansaka.api

import com.technobit.pansaka.model.*
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
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<User>

    @Headers(
        "key: x5fgFV9nK9UohrCeSDHO4LuHVLySNM4Y",
        "appId: 1"
    )
    @FormUrlEncoded
    @POST("validatetoken")
    fun validatetoken(
        @Field("id_users") id_users: String
    ): Call<token>


    // -----------------------------------------------
    // Dashboard
    // -----------------------------------------------
    @GET("getsummary")
    fun summary(
        @Header("key") appKey: String,
        @Header("Authorization") token: String,
        @Header("appId") appId: String
    ): Call<DashboardSummary>

    @GET("getlisttransaction")
    fun listtransaction(
        @Header("key") appKey: String,
        @Header("Authorization") token: String,
        @Header("appId") appId: String
    ): Call<DashboardListTransaction>

    @GET("getcustomerbuyer")
    fun customerbuyer(
        @Header("key") appKey: String,
        @Header("Authorization") token: String,
        @Header("appId") appId: String
    ): Call<CustomerBuyer>

    @GET("getcustomerseller")
    fun customerseller(
        @Header("key") appKey: String,
        @Header("Authorization") token: String,
        @Header("appId") appId: String
    ): Call<CustomerSeller>

    @GET("getprofile")
    fun profile(
        @Header("key") appKey: String,
        @Header("Authorization") token: String,
        @Header("appId") appId: String
    ): Call<Profile>

    @GET("getListTransactionDetail")
    fun listTransactionDetail(
        @Header("key") appKey: String,
        @Header("Authorization") token: String,
        @Header("appId") appId: String
    ): Call<Transaction>
}