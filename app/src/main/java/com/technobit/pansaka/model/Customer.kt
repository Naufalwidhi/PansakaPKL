package com.technobit.pansaka.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CustomerBuyerResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ArrayList<CustomerBuyer>
)
data class CustomerSellerResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ArrayList<CustomerSeller>
)

@Parcelize
@Entity
data class CustomerBuyer(
    @SerializedName("name") val custname: String,
    @SerializedName("address") val custaddress: String,
    @SerializedName("email") val custemail: String,
    @SerializedName("image_path_profile_picture") val custprofile: String,
    @SerializedName("status") val custstatus: String
) : Parcelable

@Parcelize
@Entity
data class CustomerSeller(
    @SerializedName("name") val sellername: String,
    @SerializedName("address") val selleraddress: String,
    @SerializedName("email") val selleremail: String,
    @SerializedName("image_path_profile_picture") val sellerprofile: String,
    @SerializedName("status") val sellerstatus: String
) : Parcelable