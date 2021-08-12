package com.technobit.pansaka.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technobit.pansaka.R
import com.technobit.pansaka.adapter.TransaksiAdapter
import com.technobit.pansaka.adapter.TransaksiListener
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.api.constant
import com.technobit.pansaka.model.PrefsToken
import com.technobit.pansaka.model.Transaction
import com.technobit.pansaka.model.TransactionResponse
import kotlinx.android.synthetic.main.activity_filtered_transaction.*
import kotlinx.android.synthetic.main.fragment_transaksi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilteredTransaction : AppCompatActivity(), TransaksiListener {

    private lateinit var token: String
    private val myPrefToken by lazy { PrefsToken(this) }
    private lateinit var transaksiAdapter: TransaksiAdapter
    private lateinit var rvTransaksi: RecyclerView
    private var getdate:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtered_transaction)

        getdate = intent.getStringExtra("Date")!!

        setSupportActionBar(toolbar_transaksi2)
        supportActionBar?.title = "Transaction"

        rvTransaksi = findViewById(R.id.rv_transaksi2)

        loadTransaksiFiltered()
        setView()
    }

    private fun loadTransaksiFiltered() {
        val startdate = getdate+"01"
        val enddate = getdate+"30"
        token = "Bearer " + myPrefToken.getusertoken()
        swipe_transaksi?.isRefreshing = true

        Client.myApiClient()
            .listTransactionDetail(constant.appId, constant.key, token, startdate, enddate)
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
                        swipe_transaksi?.isRefreshing = false
                    }
                }
            })
    }

    private fun setView() {
        loadTransaksiFiltered()
        transaksiAdapter = TransaksiAdapter(this)
        rv_transaksi2?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transaksiAdapter
        }
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