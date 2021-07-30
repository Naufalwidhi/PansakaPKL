package com.technobit.pansaka.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.technobit.pansaka.fragment.CustomerFragment
import com.technobit.pansaka.fragment.DashboardFragment
import com.technobit.pansaka.R
import com.technobit.pansaka.fragment.ProfileFragment
import com.technobit.pansaka.fragment.TransaksiFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nav_view = findViewById<BottomNavigationView>(R.id.nav_view)
        nav_view.setOnNavigationItemSelectedListener (onNavigationItemSelectedListener)

        if (savedInstanceState == null)
            nav_view.selectedItemId = R.id.dashboard

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