package com.technobit.pansaka.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
class Transaction(
    @SerializedName("id_checkout") val id_checkout: String,
    @SerializedName("no_invoice") val no_invoice: String,
    @SerializedName("transaction_status") val transaction_status: String,
    @SerializedName("dt_transaction") val dt_transaction: String,
    @SerializedName("price") val price: String,
    @SerializedName("product_image") val product_image: String,
    @SerializedName("name") val name: String,
    @SerializedName("shop") val shop: String,
    @SerializedName("qty") val qty: String
) : Parcelable