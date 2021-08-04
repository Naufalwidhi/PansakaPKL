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

    @POST("validatetoken")
    fun validatetoken(
        @Header("appId") appId: String,
        @Header("key") key: String,
        @Header("Authorization") token: String
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
    ): Call<CustomerBuyerResponse>

    @GET("getcustomerseller")
    fun customerseller(
        @Header("appId") appId: String,
        @Header("key") appKey: String,
        @Header("Authorization") token: String
    ): Call<CustomerSellerResponse>

    @GET("getprofile")
    fun profile(
        @Header("appId") appId: String,
        @Header("key") appKey: String,
        @Header("Authorization") token: String
    ): Call<Profile>

    @GET("getlisttransactiondetail")
    fun listTransactionDetail(
        @Header("appId") appId: String,
        @Header("key") appKey: String,
        @Header("Authorization") token: String
    ): Call<TransactionResponse>
}