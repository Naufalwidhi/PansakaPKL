package com.technobit.pansaka.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.technobit.pansaka.R
import com.technobit.pansaka.model.TransaksiDashboard
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.list_item_dashboard.*

class DetailTransaksiActivity : AppCompatActivity() {

    private lateinit var transaksi : TransaksiDashboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi)

        transaksi = intent.getParcelableExtra("transaksi")!!

        nama_produk_dashboard?.text = transaksi.nameProduct
        nama_toko_dashboard?.text = transaksi.nameShop
        txt_detail_transaksi_dashboard?.text = transaksi.txtDetail
        jumlah_pcs_dashboard?.text = transaksi.pcs
        img_produk_dashboard?.apply {

            Glide.with(context)
                .load(transaksi.imageId)
                .into(this)
        }

        setUpToolbar(transaksi.txtDetail)

    }

    private fun setUpToolbar(title: String){
        setSupportActionBar(toolbar)
        supportActionBar?.title = title

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}