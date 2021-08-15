package com.technobit.pansaka.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technobit.pansaka.R
import com.technobit.pansaka.adapter.TransaksiAdapter
import com.technobit.pansaka.adapter.TransaksiListener
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.api.Constant
import com.technobit.pansaka.model.PrefsToken
import com.technobit.pansaka.model.Transaction
import com.technobit.pansaka.model.TransactionResponse
import kotlinx.android.synthetic.main.activity_filtered_transaction.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilteredTransaction : AppCompatActivity(), TransaksiListener {

    private lateinit var token: String
    private val myPrefToken by lazy { PrefsToken(this) }
    private lateinit var transaksiAdapter: TransaksiAdapter
    private lateinit var rvTransaksi: RecyclerView
    private var getdate: String = ""
    private var getdate2: String = ""
    private lateinit var tanggal_mulai: TextView
    private lateinit var tanggal_akhir: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtered_transaction)

        setUpToolbar("Transaction Filtered")

        rvTransaksi = findViewById(R.id.rv_transaksi2)
        tanggal_mulai = findViewById(R.id.tanggal_mulai)
        tanggal_akhir = findViewById(R.id.tanggal_akhir)

        getdate = intent.getStringExtra("startDate")!!
        getdate2 = intent.getStringExtra("endDate")!!

        loadTransaksiFiltered()
        setView()
    }

    private fun loadTransaksiFiltered() {
        val startdate = getdate
        val enddate = getdate2

        tanggal_mulai.text = startdate
        tanggal_akhir.text = enddate

        token = "Bearer " + myPrefToken.getusertoken()
        swipe_transaksi2?.isRefreshing = true
//        Toast.makeText(this, "$startdate", Toast.LENGTH_SHORT).show()

        Client.myApiClient()
            .listTransactionDetail(Constant.appId, Constant.key, token, startdate, enddate)
            .enqueue(object : Callback<TransactionResponse> {
                override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                    Toast.makeText(this@FilteredTransaction, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<TransactionResponse>,
                    response: Response<TransactionResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            transaksiAdapter.updateData(it.data)
                        }
                        swipe_transaksi2?.isRefreshing = false
                    }
                }
            })
    }

    private fun setView() {
        swipe_transaksi2.setOnRefreshListener {
            loadTransaksiFiltered()
        }
        transaksiAdapter = TransaksiAdapter(this)
        rv_transaksi2?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transaksiAdapter
        }
    }

    private fun setUpToolbar(title: String) {
        setSupportActionBar(toolbar_transaksi2) // set toolbar
        supportActionBar?.title = title //set title

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(position: Int, transaksi: Transaction) {
        val intent = Intent(this, DetailTransaksiActivity::class.java)

        intent.putExtra("detail", transaksi)

        startActivity(
            intent
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}