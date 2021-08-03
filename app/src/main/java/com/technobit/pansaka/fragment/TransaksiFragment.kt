package com.technobit.pansaka.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.DetailTransaksiActivity
import com.technobit.pansaka.adapter.TransaksiAdapter
import com.technobit.pansaka.adapter.TransaksiListener
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.model.PrefsToken
import com.technobit.pansaka.model.Transaction
import kotlinx.android.synthetic.main.fragment_transaksi.*
import retrofit2.Call
import retrofit2.Response

class TransaksiFragment : Fragment(), TransaksiListener {
    private val myPrefToken by lazy { PrefsToken(this.requireContext()) }
    private lateinit var transaksiAdapter: TransaksiAdapter
    private lateinit var rvTransaksi: RecyclerView
    private lateinit var appKey: String
    private lateinit var appId: String
    private lateinit var token: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTransaksi = requireView().findViewById(R.id.rv_transaksi)

        appKey = "x5fgFV9nK9UohrCeSDHO4LuHVLySNM4Y"
        appId = "1"
        token = "Bearer " + myPrefToken.getusertoken()

        loadTransaksi()
        setView()
    }

    private fun loadTransaksi() {
        Client.myApiClient()
            .listTransactionDetail(appKey, appId, token)
            .enqueue(object : retrofit2.Callback<Transaction> {
                override fun onResponse(call: Call<Transaction>, response: Response<Transaction>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            transaksiAdapter.addData(
                                Transaction(
                                    it.id_checkout,
                                    it.no_invoice,
                                    it.transaction_status,
                                    it.dt_transaction,
                                    it.price,
                                    it.name,
                                    it.product_image,
                                    it.shop,
                                    it.qty
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<Transaction>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            })
    }

    private fun setView(){
        loadTransaksi()
        transaksiAdapter = TransaksiAdapter(this)
        rv_transaksi?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transaksiAdapter
        }
    }

    override fun onClick(position: Int, transaksi: Transaction) {

        val intent = Intent(context, DetailTransaksiActivity::class.java)

        intent.putExtra("detail", transaksi)

        startActivity(
            intent
        )
    }

}