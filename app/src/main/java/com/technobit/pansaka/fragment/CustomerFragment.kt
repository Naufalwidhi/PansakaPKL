package com.technobit.pansaka.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.technobit.pansaka.R
import com.technobit.pansaka.adapter.PagerAdapter
import kotlinx.android.synthetic.main.fragment_customer.*

class CustomerFragment : Fragment() {

    private val pagerAdapter by lazy {
        activity?.let {
            PagerAdapter(
                it.supportFragmentManager,
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
        }
    }

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

        pagerAdapter?.addFragment(CustomerBuyerFragment(), "Buyer")
        pagerAdapter?.addFragment(CustomerSellerFragment(), "Seller")

        view_pager?.apply {
            adapter = pagerAdapter
        }

        tab_customer?.setupWithViewPager(view_pager)


    }
}