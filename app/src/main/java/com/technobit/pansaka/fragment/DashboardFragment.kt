package com.technobit.pansaka.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technobit.pansaka.R
import com.technobit.pansaka.adapter.TransaksiDashboardAdapter
import com.technobit.pansaka.adapter.TransaksiDashboardListener
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.api.constant
import com.technobit.pansaka.model.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class DashboardFragment : Fragment(), TransaksiDashboardListener {
    private val myPreftoken by lazy { PrefsToken(this.requireContext()) }
    private lateinit var transaksiDashboardAdapter: TransaksiDashboardAdapter
    private lateinit var rvhome: RecyclerView
    private lateinit var totaltransaksi: TextView
    private lateinit var totalomset: TextView
    private lateinit var totalbuyer: TextView
    private lateinit var totalseller: TextView
    private lateinit var token: String

    override fun onClick(position: Int, transaksiDashboard: DashboardListTransaction) {

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvhome = view.findViewById(R.id.rv_transaksi_dashboard)

        totaltransaksi = view.findViewById(R.id.tv_total_transaksi_dashboard)
        totalomset = view.findViewById(R.id.tv_total_omset_dashboard)
        totalbuyer = view.findViewById(R.id.tv_total_buyer_dashboard)
        totalseller = view.findViewById(R.id.tv_total_seller_dashboard)

        loadsummary()

        loadTransaksi()
        setView()
    }

    private fun loadsummary() {
        token = "Bearer " + myPreftoken.getusertoken()
        Client.myApiClient()
            .summary(constant.appId, constant.key, token)
            .enqueue(object : Callback<DashboardSummaryResponse> {
                override fun onResponse(
                    call: Call<DashboardSummaryResponse>,
                    response: Response<DashboardSummaryResponse>
                ) {

                    if (response.isSuccessful) {
                        response.body()?.let {
                            val currencyformat = DecimalFormat("#,###")
                            val totaltransaksi1 = it.data[0].totaltransaksi
                            val totalomset2 = it.data[0].totalomset
                            val totalbuyer3 = it.data[0].totalbuyer
                            val totalseller4 = it.data[0].totalseller
                            val omset = currencyformat.format(totalomset2.toInt())
                            tv_total_transaksi_dashboard.text = totaltransaksi1
                            tv_total_omset_dashboard.text = "IDR "+omset.toString()
                            tv_total_buyer_dashboard.text = totalbuyer3
                            tv_total_seller_dashboard.text = totalseller4
                        }
                    }
                }

                override fun onFailure(call: Call<DashboardSummaryResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun loadTransaksi() {
        token = "Bearer " + myPreftoken.getusertoken()
        Client.myApiClient()
            .listtransaction(constant.appId, constant.key, token)
            .enqueue(object : Callback<DashboardListTransactionResponse> {
                override fun onFailure(call: Call<DashboardListTransactionResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<DashboardListTransactionResponse>,
                    response: Response<DashboardListTransactionResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            transaksiDashboardAdapter.updateData(it.data)
                        }
                    }
                }
            })
    }

    private fun setView() {
        loadTransaksi()
        transaksiDashboardAdapter = TransaksiDashboardAdapter(this)
        rv_transaksi_dashboard?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transaksiDashboardAdapter
        }
    }
}