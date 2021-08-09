package com.technobit.pansaka.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.DetailTransaksiActivity
import com.technobit.pansaka.adapter.TransaksiAdapter
import com.technobit.pansaka.adapter.TransaksiListener
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.api.constant
import com.technobit.pansaka.model.PrefsToken
import com.technobit.pansaka.model.Transaction
import com.technobit.pansaka.model.TransactionResponse
import kotlinx.android.synthetic.main.fragment_transaksi.*
import kotlinx.android.synthetic.main.fragment_transaksi.toolbar_transaksi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransaksiFragment : Fragment(), TransaksiListener {
    private val myPrefToken by lazy { PrefsToken(this.requireContext()) }
    private lateinit var transaksiAdapter: TransaksiAdapter
    private lateinit var rvTransaksi: RecyclerView
    private lateinit var token: String

    override fun onClick(position: Int, transaksi: Transaction) {

        val intent = Intent(context, DetailTransaksiActivity::class.java)

        intent.putExtra("detail", transaksi)

        startActivity(
            intent
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTransaksi = requireView().findViewById(R.id.rv_transaksi)

        loadTransaksi()
        setView()

        (activity as AppCompatActivity).setSupportActionBar(toolbar_transaksi)
        (activity as AppCompatActivity).supportActionBar?.title = "Transaction"

    }

    private fun loadTransaksi() {
        token = "Bearer " + myPrefToken.getusertoken()
        swipe_transaksi?.isRefreshing = true

        Client.myApiClient()
            .listTransactionDetail(constant.appId, constant.key, token)
            .enqueue(object : Callback<TransactionResponse> {
                override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
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
        loadTransaksi()
        transaksiAdapter = TransaksiAdapter(this)
        rv_transaksi?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transaksiAdapter
        }
    }


}