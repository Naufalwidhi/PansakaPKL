package com.technobit.pansaka.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.technobit.pansaka.fragment.CustomerFragment
import com.technobit.pansaka.fragment.DashboardFragment
import com.technobit.pansaka.R
import com.technobit.pansaka.fragment.TransaksiFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var dashboardFragment: DashboardFragment
    lateinit var customerFragment: CustomerFragment
    lateinit var transaksiFragment: TransaksiFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Pansaka Backoffice"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            (R.string.open),
            (R.string.close)
        ) {

        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        // Implement navigation item selected listener
        // Default fragment
        dashboardFragment = DashboardFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, dashboardFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        // Create related fragment
        when (menuItem.itemId) {
            R.id.dashboard -> {
                dashboardFragment = DashboardFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, dashboardFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.customer -> {
                customerFragment = CustomerFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, customerFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.transaksi -> {
                transaksiFragment = TransaksiFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, transaksiFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}