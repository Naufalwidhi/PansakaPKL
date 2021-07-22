package com.technobit.pansaka.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
class User (
    @SerializedName("token") val token: String,
    @SerializedName("id_users") val id_users: Int,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("url_profile_picture") val profilepic: String
):Parcelable