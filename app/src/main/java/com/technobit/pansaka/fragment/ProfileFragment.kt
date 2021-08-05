package com.technobit.pansaka.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.ChangePasswordActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_transaksi.*


class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar_profile)
        (activity as AppCompatActivity).supportActionBar?.title = "Profile"

        setChangePassButton()
    }

    private fun setChangePassButton(){
        btn_change_password.setOnClickListener{
            val intent = Intent(context,ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }
}