package com.technobit.pansaka.fragment

import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.technobit.pansaka.R
import com.technobit.pansaka.adapter.CustBuyerListener
import com.technobit.pansaka.adapter.CustomerBuyerAdapter
import com.technobit.pansaka.adapter.TransaksiDashboardAdapter
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.model.CustomerBuyer
import com.technobit.pansaka.model.DashboardListTransaction
import com.technobit.pansaka.model.PrefsId
import com.technobit.pansaka.model.PrefsToken
import kotlinx.android.synthetic.main.fragment_customer_buyer.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Callback
import retrofit2.Response

class CustomerBuyerFragment : Fragment(), CustBuyerListener {

    private val myPrefid by lazy { PrefsId(this.requireContext()) }
    private val myPreftoken by lazy { PrefsToken(this.requireContext()) }
    private lateinit var customerBuyerAdapter: CustomerBuyerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_buyer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadcustbuyer()
        setView()
    }

    private fun loadcustbuyer() {
        swipe.isRefreshing = true
        val token = myPreftoken.getusertoken()
        val iduser = myPrefid.getuserid()
        val appkey = "x5fgFV9nK9UohrCeSDHO4LuHVLySNM4Y"
        val appid = "1"

        Client.myApiClient().customerbuyer(appid, appkey, iduser, token)
            .enqueue(object : Callback<CustomerBuyer>{
                override fun onResponse(call: retrofit2.Call<CustomerBuyer>, response: Response<CustomerBuyer>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            customerBuyerAdapter.addData(CustomerBuyer(it.custname, it.custaddress, it.custemail, it.custstatus, it.custprofile))
                        }
                    }
                }
                override fun onFailure(call: retrofit2.Call<CustomerBuyer>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
    private fun setView() {
        loadcustbuyer()
        customerBuyerAdapter = CustomerBuyerAdapter(this)
        rv_transaksi_dashboard?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customerBuyerAdapter
        }
    }

    override fun onClick(position: Int, movie: CustomerBuyer) {

    }
}