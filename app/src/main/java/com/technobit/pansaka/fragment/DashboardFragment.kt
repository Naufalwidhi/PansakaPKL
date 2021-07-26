package com.technobit.pansaka.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.DetailTransaksiActivity
import com.technobit.pansaka.adapter.TransaksiDashboardAdapter
import com.technobit.pansaka.adapter.TransaksiDashboardListener
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.databinding.FragmentDashboardBinding
import com.technobit.pansaka.model.TransaksiDashboard
import com.technobit.pansaka.model.TransaksiResponse
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment(), TransaksiDashboardListener {

    override fun onClick(position: Int, transaksiDashboard: TransaksiDashboard) {

        val intent = Intent(context, DetailTransaksiActivity::class.java)

        intent.putExtra("transaksi", transaksiDashboard)

        startActivity(
            intent
        )

    }

    private lateinit var transaksiDashboardAdapter : TransaksiDashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
//        loadTransaksi()
    }

//    private fun loadTransaksi(){
//
//        Client.myApiClient()
//            .getNowPlaying()
//            .enqueue(object : Callback<TransaksiResponse> {
//
//                override fun onFailure(call: Call<TransaksiResponse>, t: Throwable) {
//
//                    //kodingan kalo gagal
//                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//
//                }
//
//                override fun onResponse(
//                    call: Call<TransaksiResponse>,
//                    response: Response<TransaksiResponse>
//                ) {
//                    //kodingan kalo sukses
//                    if(response.isSuccessful){
//
//                        response.body()?.let {
//
//                            transaksiDashboardAdapter.updateData(it.results)
//
//                        }
//
//                    }
//                }
//
//            })
//
//    }

    private fun setView(){

//            loadMovie()

        transaksiDashboardAdapter = TransaksiDashboardAdapter(this)

        rv_transaksi_dashboard?.apply {

            layoutManager = LinearLayoutManager(context)

            adapter = transaksiDashboardAdapter

        }

    }
}