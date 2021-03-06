package com.technobit.pansaka.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: User
)

@Parcelize
@Entity
data class User (
    @SerializedName("token") val token: String,
    @SerializedName("id_users") val id_users: Int,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name1: String,
    @SerializedName("email") val email: String,
    @SerializedName("url_profile_picture") val profilepic: String
):Parcelable

data class ProfileResponse (
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ArrayList<Profile>
)

@Parcelize
@Entity
data class Profile (
    @SerializedName("name") val nameprofile: String,
    @SerializedName("address") val address: String,
    @SerializedName("email") val email: String,
    @SerializedName("url_profile_picture") val profilepic: String
):Parcelable

@Parcelize
@Entity
data class TokenResponse (
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
):Parcelable

@Parcelize
@Entity
data class ChangeResponse (
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
):Parcelable

@Parcelize
@Entity
data class LogoutResponse (
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
):Parcelable