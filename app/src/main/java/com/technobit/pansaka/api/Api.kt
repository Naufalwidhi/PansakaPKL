package com.technobit.pansaka.api

import com.technobit.pansaka.model.*
import retrofit2.Call
import retrofit2.http.*

interface Api {

    // -----------------------------------------------
    // Login
    // -----------------------------------------------
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Header("appId") appId: String,
        @Header("key") key: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<UserResponse>

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
        @Header("appId") appId: String,
        @Header("key") appKey: String,
        @Header("Authorization") token: String
    ): Call<DashboardSummaryResponse>

    @GET("getlisttransaction")
    fun listtransaction(
        @Header("appId") appId: String,
        @Header("key") appKey: String,
        @Header("Authorization") token: String
    ): Call<DashboardListTransactionResponse>

    @GET("getcustomerbuyer")
    fun customerbuyer(
        @Header("appId") appId: String,
        @Header("key") appKey: String,
        @Header("Authorization") token: String
    ): Call<CustomerBuyer>

    @GET("getcustomerseller")
    fun customerseller(
        @Header("appId") appId: String,
        @Header("key") appKey: String,
        @Header("Authorization") token: String
    ): Call<CustomerSeller>

    @GET("getprofile")
    fun profile(
        @Header("appId") appId: String,
        @Header("key") appKey: String,
        @Header("Authorization") token: String
    ): Call<Profile>

    @GET("getListTransactionDetail")
    fun listTransactionDetail(
        @Header("appId") appId: String,
        @Header("key") appKey: String,
        @Header("Authorization") token: String
    ): Call<Transaction>
}