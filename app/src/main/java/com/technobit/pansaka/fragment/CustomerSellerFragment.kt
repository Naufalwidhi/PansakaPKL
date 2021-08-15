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
import com.technobit.pansaka.api.Constant
import com.technobit.pansaka.model.CustomerSeller
import com.technobit.pansaka.model.CustomerSellerResponse
import com.technobit.pansaka.model.PrefsToken
import kotlinx.android.synthetic.main.fragment_customer_buyer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerSellerFragment : Fragment(), CustSellerListener {

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
        swipe?.isRefreshing = true
        val token = "Bearer " + myPreftoken.getusertoken()

        Client.myApiClient().customerseller(Constant.appId, Constant.key, token)
            .enqueue(object : Callback<CustomerSellerResponse> {
                override fun onResponse(
                    call: Call<CustomerSellerResponse>,
                    response: Response<CustomerSellerResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            customerSellerAdapter.updateData(it.data)
                        }
                        swipe?.isRefreshing = false
                    }
                }
                override fun onFailure(call: Call<CustomerSellerResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun setView() {
        swipe.setOnRefreshListener {
            loadcustseller()
        }
        customerSellerAdapter = CustomerSellerAdapter(this)
        rv_customer?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customerSellerAdapter
        }
    }

    override fun onClick(position: Int, movie: CustomerSeller) {

    }
}