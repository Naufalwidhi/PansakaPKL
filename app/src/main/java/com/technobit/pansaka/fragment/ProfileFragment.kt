package com.technobit.pansaka.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.ChangePasswordActivity
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.api.constant
import com.technobit.pansaka.model.PrefsToken
import com.technobit.pansaka.model.Profile
import com.technobit.pansaka.model.ProfileResponse
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private val myPreftoken by lazy { PrefsToken(this.requireContext()) }
    private lateinit var profile: Profile
    private lateinit var token: String
    private lateinit var name : TextView
    private lateinit var image : ImageView

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

        setChangePassButton()
        loadProfile()
    }

    private fun setChangePassButton(){
        btn_change_password.setOnClickListener{
            val intent = Intent(context,ChangePasswordActivity::class.java)
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