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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.DetailTransaksiActivity
import com.technobit.pansaka.adapter.Adapter
import com.technobit.pansaka.adapter.TransaksiDashboardAdapter
import com.technobit.pansaka.adapter.TransaksiDashboardListener
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.model.DashboardListTransaction
import com.technobit.pansaka.model.DashboardSummary
import com.technobit.pansaka.model.Prefs
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment(), TransaksiDashboardListener {

    private val myPref by lazy { Prefs(this.requireContext()) }
    private lateinit var transaksiDashboardAdapter: TransaksiDashboardAdapter
    private lateinit var rvhome: RecyclerView
    private lateinit var totaltransaksi: TextView
    private lateinit var totalomset: TextView
    private lateinit var totalbuyer: TextView
    private lateinit var totalseller: TextView
    private lateinit var appkey: String
    private lateinit var appid: String
    private lateinit var token: String
    private lateinit var id_user: String

    override fun onClick(position: Int, transaksiDashboard: DashboardListTransaction) {

        val intent = Intent(context, DetailTransaksiActivity::class.java)

        intent.putExtra("transaksi", transaksiDashboard)

        startActivity(
            intent
        )
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

        rvhome = requireView().findViewById(R.id.rv_transaksi_dashboard)

        totaltransaksi = requireView().findViewById(R.id.tv_total_transaksi_dashboard)
        totalomset = requireView().findViewById(R.id.tv_total_omset_dashboard)
        totalbuyer = requireView().findViewById(R.id.tv_total_buyer_dashboard)
        totalseller = requireView().findViewById(R.id.tv_total_seller_dashboard)

        appkey = "x5fgFV9nK9UohrCeSDHO4LuHVLySNM4Y"
        appid = "1"
        token = myPref.getusertoken()
        id_user = myPref.getuserid()

        loadsummary()

        loadTransaksi()
        setView()
    }

    private fun loadsummary(){
        Client.myApiClient()
            .summary(appkey, appid, token, id_user)
            .enqueue(object : Callback<DashboardSummary>{
                override fun onResponse(call: Call<DashboardSummary>, response: Response<DashboardSummary>) {
                    if (response.isSuccessful){
                        val total_transaksi1 = response.body()!!.totaltransaksi
                        val total_omset1 = response.body()!!.totalomset
                        val total_buyer1 = response.body()!!.totalbuyer
                        val total_seller1 = response.body()!!.totalseller

                        totaltransaksi.setText(total_transaksi1)
                        totalomset.setText(total_omset1)
                        totalbuyer.setText(total_buyer1)
                        totalseller.setText(total_seller1)
                    }
                }

                override fun onFailure(call: Call<DashboardSummary>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun loadTransaksi() {
        Client.myApiClient()
            .listtransaction(appkey, appid, token, id_user)
            .enqueue(object : Callback<DashboardListTransaction> {
                override fun onFailure(call: Call<DashboardListTransaction>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<DashboardListTransaction>,
                    response: Response<DashboardListTransaction>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            transaksiDashboardAdapter.addData(DashboardListTransaction(it.id_checkout,it.name,it.product_image,it.shop,it.qty))
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