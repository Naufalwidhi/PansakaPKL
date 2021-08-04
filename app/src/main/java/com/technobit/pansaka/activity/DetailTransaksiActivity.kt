package com.technobit.pansaka.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.technobit.pansaka.R
import com.technobit.pansaka.model.Transaction
import kotlinx.android.synthetic.main.activity_detail_transaksi.*

class DetailTransaksiActivity : AppCompatActivity() {

    private lateinit var transaksi: Transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi)

        transaksi = intent.getParcelableExtra("detail")!!

        status_transaksi?.text = transaksi.transaction_status
        tanggal_transaksi?.text = transaksi.dt_transaction
        nama_produk_transaksi?.text = transaksi.name
        nama_toko_transaksi?.text = transaksi.shop
        harga_transaksi?.text = transaksi.price
        qty_transaksi?.text = transaksi.qty
        invoice_transaksi?.text = transaksi.no_invoice
        img_produk_transaksi?.apply {

            Glide.with(context)
                .load(transaksi.product_image)
                .into(this)
        }

        setUpToolbar("Detail Transaksi")
    }

    private fun setUpToolbar(title: String){
        setSupportActionBar(toolbar_transaksi) // set toolbar
        supportActionBar?.title = title //set title

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}