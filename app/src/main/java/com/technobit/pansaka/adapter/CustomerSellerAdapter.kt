package com.technobit.pansaka.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technobit.pansaka.R
import com.technobit.pansaka.model.CustomerSeller
import kotlinx.android.synthetic.main.list_item_customer_buyer.view.*

class CustomerSellerAdapter(val listener: CustSellerListener) : RecyclerView.Adapter<CustSellerVH>() {

    private val mData = arrayListOf<CustomerSeller>()

    fun updateData(data: ArrayList<CustomerSeller>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustSellerVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_customer_buyer, parent, false)
        return CustSellerVH(view)
    }
    override fun getItemCount(): Int {
        return mData.size
    }
    override fun onBindViewHolder(holder: CustSellerVH, position: Int) {
        holder.bind(mData[position], listener)
    }
}

class CustSellerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: CustomerSeller, listener: CustSellerListener) {

        itemView.nama_produk_customer_buyer?.text = data.sellername
        itemView.email_customer_buyer?.text = data.selleremail
        itemView.alamat_buyer?.text = data.selleraddress
        itemView.img_produk_customer_buyer?.apply {
            //load gambar
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+data.sellerprofile)
                .into(this)
        }
        itemView.rootView.setOnClickListener {
            listener.onClick(adapterPosition, data)
        }
    }
}

interface CustSellerListener {
    fun onClick(position: Int, movie: CustomerSeller)
}


