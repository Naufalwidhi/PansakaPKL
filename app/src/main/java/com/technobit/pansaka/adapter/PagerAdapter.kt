package com.technobit.pansaka.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.technobit.pansaka.fragment.CustomerBuyerFragment
import com.technobit.pansaka.fragment.CustomerSellerFragment

class PagerAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CustomerBuyerFragment()
            1 -> CustomerSellerFragment()
            else-> throw IllegalStateException()
        }
    }

}