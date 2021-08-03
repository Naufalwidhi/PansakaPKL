package com.technobit.pansaka.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.technobit.pansaka.R
import com.technobit.pansaka.adapter.CustSellerListener
import com.technobit.pansaka.adapter.CustomerSellerAdapter
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.model.CustomerSeller
import com.technobit.pansaka.model.PrefsId
import com.technobit.pansaka.model.PrefsToken
import kotlinx.android.synthetic.main.fragment_customer_buyer.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Callback
import retrofit2.Response

class CustomerSellerFragment : Fragment(), CustSellerListener {

    private val myPrefid by lazy { PrefsId(this.requireContext()) }
    private val myPreftoken by lazy { PrefsToken(this.requireContext()) }
    private lateinit var customerSellerAdapter: CustomerSellerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_buyer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadcustseller()
        setView()
    }

    private fun loadcustseller() {
        swipe.isRefreshing = true
        val token = "Bearer " + myPreftoken.getusertoken()
        val iduser = myPrefid.getuserid()
        val appkey = "x5fgFV9nK9UohrCeSDHO4LuHVLySNM4Y"
        val appid = "1"

        Client.myApiClient().customerseller(appid, appkey, token)
            .enqueue(object : Callback<CustomerSeller> {
                override fun onResponse(
                    call: retrofit2.Call<CustomerSeller>,
                    response: Response<CustomerSeller>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            customerSellerAdapter.addData(
                                CustomerSeller(
                                    it.sellername,
                                    it.selleraddress,
                                    it.selleremail,
                                    it.sellerstatus,
                                    it.sellerprofile
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: retrofit2.Call<CustomerSeller>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun setView() {
        loadcustseller()
        customerSellerAdapter = CustomerSellerAdapter(this)
        rv_transaksi_dashboard?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customerSellerAdapter
        }
    }

    override fun onClick(position: Int, movie: CustomerSeller) {

    }
}