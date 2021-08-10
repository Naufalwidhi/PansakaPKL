package com.technobit.pansaka.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technobit.pansaka.R
import com.technobit.pansaka.model.Transaction
import kotlinx.android.synthetic.main.list_item_transaksi.view.*

class TransaksiAdapter(val listener: TransaksiListener) : RecyclerView.Adapter<TransaksiVH>() {

    private val transaksiData = arrayListOf<Transaction>()

    fun updateData(data: ArrayList<Transaction>) {
        transaksiData.clear()
        transaksiData.addAll(data)
        notifyDataSetChanged()
    }
//
//    fun addData(Transaction: Transaction) {
//        transaksiData.add(Transaction)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_transaksi, parent, false)

        return TransaksiVH(view)
    }


    override fun getItemCount(): Int {
        return transaksiData.size
    }

    override fun onBindViewHolder(holder: TransaksiVH, position: Int) {
        holder.bind(transaksiData[position], listener)
    }

}

class TransaksiVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: Transaction, listener: TransaksiListener) {

        itemView.nama_produk_dashboard?.text = data.name
        itemView.nama_toko_dashboard?.text = data.shop
        itemView.jumlah_pcs_dashboard?.text = data.qty
        itemView.img_produk_dashboard?.apply {

//            load gambar
            Glide.with(context)
                .load(data.product_image)
                .into(this)

        }
        itemView.rootView.setOnClickListener {
            listener.onClick(adapterPosition, data)

        }
    }

}

interface TransaksiListener {

    fun onClick(position: Int, transaksi: Transaction)

}