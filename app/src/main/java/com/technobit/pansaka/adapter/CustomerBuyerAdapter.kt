package com.technobit.pansaka.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technobit.pansaka.R
import com.technobit.pansaka.model.CustomerBuyer
import kotlinx.android.synthetic.main.list_item_customer_buyer.view.*

class CustomerBuyerAdapter(val listener: CustBuyerListener) : RecyclerView.Adapter<CustBuyerVH>() {
    private val mData = arrayListOf<CustomerBuyer>()
    fun updateData(data: ArrayList<CustomerBuyer>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustBuyerVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_customer_buyer, parent, false)
        return CustBuyerVH(view)
    }
    override fun getItemCount(): Int {
        return mData.size
    }
    override fun onBindViewHolder(holder: CustBuyerVH, position: Int) {
        holder.bind(mData[position], listener)
    }
}

class CustBuyerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: CustomerBuyer, listener: CustBuyerListener) {
        itemView.nama_produk_customer_buyer?.text = data.custname
        itemView.email_customer_buyer?.text = data.custemail
        itemView.alamat_buyer?.text = data.custaddress
        itemView.img_produk_customer_buyer?.apply {
            //load gambar
            Glide.with(context)
                .load(data.custprofile)
                .into(this)
        }
        itemView.rootView.setOnClickListener {
            listener.onClick(adapterPosition, data)
        }
    }
}

interface CustBuyerListener{
    fun onClick(position: Int, movie: CustomerBuyer)
}