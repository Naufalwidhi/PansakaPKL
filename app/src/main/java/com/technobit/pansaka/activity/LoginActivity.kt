package com.technobit.pansaka.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.technobit.pansaka.R
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.api.constant
import com.technobit.pansaka.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var login: Button

    private val myPreftoken by lazy { PrefsToken(this) }

    override fun onStart() {
        super.onStart()
        val token = myPreftoken.getusertoken()
        if (token.isNotEmpty()) {
            val token1 = "Bearer " + token
            Client.myApiClient()
                .validatetoken(constant.appId, constant.key, token1)
                .enqueue(object : Callback<TokenResponse> {
                    override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val validate = it.message
                                if (validate.equals("Token Valid")) {
                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Silahkan Login Kembali",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Tidak Ada Internet", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.et_username_login)
        val password = findViewById<EditText>(R.id.et_password_login)
        login = findViewById(R.id.btn_login)

        login.setOnClickListener {
            if (username.text.toString().isEmpty()) {
                username.error = "Mohon Masukkan Username"
                username.requestFocus()
                return@setOnClickListener
            }
            if (password.text.toString().isEmpty()) {
                password.error = "Mohon Masukkan Password"
                password.requestFocus()
                return@setOnClickListener
            }
            signin(username.text.toString(), password.text.toString())
        }
    }

    private fun signin(username: String, password: String) {
        Client.myApiClient()
            .login(constant.appId, constant.key, username, password)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {

                        response.body()?.let {
                            val message = it.message
                            if (message.equals("Success login")){
                                val token = it.data.token
                                myPreftoken.saveusertoken(token)
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Password Salah",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Something Wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }
}