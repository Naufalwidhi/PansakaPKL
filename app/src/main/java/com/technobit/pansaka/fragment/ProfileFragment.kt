package com.technobit.pansaka.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.ChangePasswordActivity
import com.technobit.pansaka.activity.LoginActivity
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.api.Constant
import com.technobit.pansaka.model.LogoutResponse
import com.technobit.pansaka.model.PrefsToken
import com.technobit.pansaka.model.ProfileResponse
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_logout_dialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private val myPreftoken by lazy { PrefsToken(this.requireContext()) }
    private lateinit var token: String

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

        val logout = view.findViewById<LinearLayout>(R.id.btn_logout)
        val gantipassword = view.findViewById<LinearLayout>(R.id.btn_change_password)

        setChangePassButton()
        loadProfile()

        logout.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.layout_logout_dialog, null)
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            mDialogView.btn_logout_iya.setOnClickListener {
                logout()
                mAlertDialog.dismiss()
            }

            mDialogView.btn_logout_tidak.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
        gantipassword.setOnClickListener {
            val intent = Intent(context, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logout() {
        val token = "Bearer "+ myPreftoken.getusertoken()
        Client.myApiClient().logout(Constant.appId, Constant.key, token)
            .enqueue(object : Callback<LogoutResponse> {
                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    Toast.makeText(context, "Halo", Toast.LENGTH_SHORT).show()
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
            .profile(Constant.appId, Constant.key, token)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val name_profile = it.data[0].nameprofile
                            val emaill = it.data[0].email
                            profile_name?.text = name_profile
                            email?.text = emaill
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