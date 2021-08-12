package com.technobit.pansaka.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.technobit.pansaka.R
import com.technobit.pansaka.adapter.PagerAdapter
import kotlinx.android.synthetic.main.fragment_customer.*

class CustomerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar_customer)
        (activity as AppCompatActivity).supportActionBar?.title = "Customer"

        val adapter = PagerAdapter(childFragmentManager, lifecycle)
        view_pager.offscreenPageLimit = 2
        view_pager.adapter = adapter
        TabLayoutMediator(tab_customer, view_pager) { tab, i ->
            when (i) {
                0 -> tab.text = "Buyer"
                1 -> tab.text = "Seller"
            }
        }.attach()
    }
}