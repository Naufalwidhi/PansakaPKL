package com.technobit.pansaka.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technobit.pansaka.R
import com.technobit.pansaka.model.DashboardListTransaction
import kotlinx.android.synthetic.main.list_item_dashboard.view.*

class TransaksiDashboardAdapter(val listener: TransaksiDashboardListener) :
    RecyclerView.Adapter<TransaksiDashboardVH>() {

    private val transaksiDashboardData = arrayListOf<DashboardListTransaction>()

    fun updateData(data: ArrayList<DashboardListTransaction>) {

        transaksiDashboardData.clear()
        transaksiDashboardData.addAll(data.take(10))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiDashboardVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_dashboard, parent, false)
        return TransaksiDashboardVH(view)
    }


    override fun getItemCount(): Int {
        return transaksiDashboardData.size
    }

    override fun onBindViewHolder(holder: TransaksiDashboardVH, position: Int) {
        holder.bind(transaksiDashboardData[position], listener)
    }
}

class TransaksiDashboardVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: DashboardListTransaction, listener: TransaksiDashboardListener) {

        itemView.nama_produk_dashboard?.text = data.name
        itemView.nama_toko_dashboard?.text = data.shop
        itemView.jumlah_pcs_dashboard?.text = data.qty
        itemView.img_produk_dashboard?.apply {
            Glide.with(context)
                .load(data.product_image)
                .into(this)

        }
        itemView.rootView.setOnClickListener {
            listener.onClick(adapterPosition, data)

        }
    }

}

interface TransaksiDashboardListener {

    fun onClick(position: Int, transaksiDashboard: DashboardListTransaction)

}