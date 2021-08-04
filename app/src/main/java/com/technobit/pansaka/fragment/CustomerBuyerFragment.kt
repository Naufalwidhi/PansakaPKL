package com.technobit.pansaka.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.technobit.pansaka.R
import com.technobit.pansaka.adapter.CustBuyerListener
import com.technobit.pansaka.adapter.CustomerBuyerAdapter
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.api.constant
import com.technobit.pansaka.model.CustomerBuyer
import com.technobit.pansaka.model.CustomerBuyerResponse
import com.technobit.pansaka.model.PrefsToken
import kotlinx.android.synthetic.main.fragment_customer_buyer.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerBuyerFragment : Fragment(), CustBuyerListener {

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

        setView()
        loadcustbuyer()
    }

    private fun loadcustbuyer() {
        swipe.isRefreshing = true
        val token = "Bearer " + myPreftoken.getusertoken()

        Client.myApiClient().customerbuyer(constant.appId, constant.key, token)
            .enqueue(object : Callback<CustomerBuyerResponse> {
                override fun onResponse(
                    call: Call<CustomerBuyerResponse>,
                    response: Response<CustomerBuyerResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            customerBuyerAdapter.updateData(it.data)
                        }
                        swipe.isRefreshing = false
                    }
                }

                override fun onFailure(call: Call<CustomerBuyerResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun setView() {
        loadcustbuyer()
        customerBuyerAdapter = CustomerBuyerAdapter(this)
        rv_customer?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customerBuyerAdapter
        }
    }

    override fun onClick(position: Int, movie: CustomerBuyer) {

    }
}