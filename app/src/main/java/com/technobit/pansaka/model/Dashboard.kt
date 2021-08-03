package com.technobit.pansaka.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class DashboardSummaryResponse(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: ArrayList<DashboardSummary>
)

@Parcelize
@Entity
data class DashboardSummary (
    @SerializedName("total_transaksi") val totaltransaksi: String,
    @SerializedName("total_omset") val totalomset: String,
    @SerializedName("total_seller") val totalseller: String,
    @SerializedName("total_buyer") val totalbuyer: String
):Parcelable

data class DashboardListTransactionResponse(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: ArrayList<DashboardListTransaction>
)

@Parcelize
@Entity
class DashboardListTransaction(
    @SerializedName("id_checkout") val id_checkout: String,
    @SerializedName("product_image") val product_image: String,
    @SerializedName("name") val name: String,
    @SerializedName("shop") val shop: String,
    @SerializedName("qty") val qty: String
):Parcelable