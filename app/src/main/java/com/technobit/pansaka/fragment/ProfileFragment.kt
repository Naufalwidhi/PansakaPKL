package com.technobit.pansaka.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.ChangePasswordActivity
import com.technobit.pansaka.activity.LoginActivity
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.api.constant
import com.technobit.pansaka.model.LogoutResponse
import com.technobit.pansaka.model.PrefsToken
import com.technobit.pansaka.model.Profile
import com.technobit.pansaka.model.ProfileResponse
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private val myPreftoken by lazy { PrefsToken(this.requireContext()) }
    private lateinit var token: String
    private lateinit var name: TextView
    private lateinit var image: ImageView
    private lateinit var logout: Button
    private lateinit var gantipassword: Button

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

        name = view.findViewById(R.id.profile_name)
        image = view.findViewById(R.id.img_profile)
        logout = view.findViewById(R.id.btn_logout)
        gantipassword = view.findViewById(R.id.btn_change_password)

        setChangePassButton()
        loadProfile()

        logout.setOnClickListener {
            logout()
        }
        btn_change_password.setOnClickListener {
            val intent = Intent(context, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logout() {
        val token = myPreftoken.getusertoken()
        Client.myApiClient().logout(constant.appId, constant.key, token)
            .enqueue(object : Callback<LogoutResponse> {
                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            val message = it.message
                            if (message.equals("Berhasil logout")){
                                myPreftoken.deletetoken()
                                val intent = Intent(context, LoginActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(context, "User Session telah habis", Toast.LENGTH_SHORT).show()
                                val intent = Intent(context, LoginActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }else{
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }

            })

    }

    private fun setChangePassButton() {
        btn_change_password.setOnClickListener {
            val intent = Intent(context, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadProfile() {
        token = "Bearer " + myPreftoken.getusertoken()
        Client.myApiClient()
            .profile(constant.appId, constant.key, token)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val name_profile = it.data[0].nameprofile
                            profile_name?.text = name_profile
                            img_profile.apply {
                                context?.let { it1 ->
                                    Glide.with(it1)
                                        .load(it.data[0].profilepic)
                                        .into(this)
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }
}