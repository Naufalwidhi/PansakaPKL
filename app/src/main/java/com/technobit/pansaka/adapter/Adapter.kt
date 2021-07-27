package com.technobit.pansaka.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.technobit.pansaka.R
import com.technobit.pansaka.model.TransaksiDashboard

class Adapter(private val listdata: ArrayList<TransaksiDashboard>): RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class AdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.nama_produk_dashboard)
        var tvDetail: TextView = itemView.findViewById(R.id.nama_toko_dashboard)
        var tvYear: TextView = itemView.findViewById(R.id.jumlah_pcs_dashboard)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_produk_dashboard)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_dashboard, parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val data = listdata[position]
        Glide.with(holder.itemView.context)
            .load(data.imageId)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)
        holder.tvName.setText(data.nameProduct)
        holder.tvDetail.setText(data.nameShop)
        holder.tvYear.setText(data.pcs)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listdata[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TransaksiDashboard)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }
}