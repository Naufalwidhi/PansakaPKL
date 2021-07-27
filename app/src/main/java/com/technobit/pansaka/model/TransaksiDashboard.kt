package com.technobit.pansaka.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TransaksiResponse(
    @SerializedName("results") val results:ArrayList<TransaksiDashboard>
)

@Parcelize
@Entity
class TransaksiDashboard (
    @SerializedName ("imagId") val imageId : Int,
    @SerializedName ("nameProduct") val nameProduct : String,
    @SerializedName ("nameShop") val nameShop : String,
    @SerializedName ("pcs") val pcs : String
    ):Parcelable