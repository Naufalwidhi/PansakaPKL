package com.technobit.pansaka.data

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
class DashboardSummary (
    @SerializedName("total_transaksi") val totaltransaksi: String,
    @SerializedName("total_omset") val totalomset: String,
    @SerializedName("total_seller") val totalseller: String,
    @SerializedName("total_buyer") val totalbuyer: String
):Parcelable

@Parcelize
@Entity
class DashboardListTransaction(
    @SerializedName("no_invoice") val noinvoice: String,
    @SerializedName("product_name") val productname: String,
    @SerializedName("image_path_product") val imagepathproduct: String,
    @SerializedName("jumlah") val jumlah: String,
    @SerializedName("total_harga") val totalharga: String
):Parcelable