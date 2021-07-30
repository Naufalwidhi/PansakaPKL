package com.technobit.pansaka.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
class CustomerBuyer(
    @SerializedName("name") val custname: Int,
    @SerializedName("address") val custaddress: String,
    @SerializedName("email") val custemail: String,
    @SerializedName("image_path_profile_picture") val custprofile: String,
    @SerializedName("status") val custstatus: String
) : Parcelable

@Parcelize
@Entity
class CustomerSeller(
    @SerializedName("name") val sellername: Int,
    @SerializedName("address") val selleraddress: String,
    @SerializedName("email") val selleremail: String,
    @SerializedName("image_path_profile_picture") val sellerprofile: String,
    @SerializedName("status") val sellerstatus: String
) : Parcelable