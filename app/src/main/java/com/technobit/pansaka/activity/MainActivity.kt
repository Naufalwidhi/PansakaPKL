package com.technobit.pansaka.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.technobit.pansaka.fragment.CustomerFragment
import com.technobit.pansaka.fragment.DashboardFragment
import com.technobit.pansaka.R
import com.technobit.pansaka.fragment.ProfileFragment
import com.technobit.pansaka.fragment.TransaksiFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationview = findViewById<BottomNavigationView>(R.id.nav_view)
        navigationview.setOnNavigationItemSelectedListener (onNavigationItemSelectedListener)

        if (savedInstanceState == null)
            navigationview.selectedItemId = R.id.dashboard

    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            val fragment: Fragment
            when (menuItem.itemId) {

                R.id.dashboard -> {
                    fragment = DashboardFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        fragment,
                        fragment.javaClass.simpleName
                    ).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.customer -> {
                    fragment = CustomerFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        fragment,
                        fragment.javaClass.simpleName
                    ).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.transaksi -> {
                    fragment = TransaksiFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        fragment,
                        fragment.javaClass.simpleName
                    ).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    fragment = ProfileFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        fragment,
                        fragment.javaClass.simpleName
                    ).commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

}