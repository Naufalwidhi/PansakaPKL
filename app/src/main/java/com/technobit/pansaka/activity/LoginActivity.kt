package com.technobit.pansaka.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.technobit.pansaka.R
import com.technobit.pansaka.api.Client
import com.technobit.pansaka.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var login: Button

    private val myPrefid by lazy { PrefsId(this) }
    private val myPreftoken by lazy { PrefsToken(this) }

    override fun onStart() {
        super.onStart()
        val token = myPreftoken.getusertoken()
        val idusers = myPrefid.getuserid()
        if (token.isNotEmpty()) {
            Client.myApiClient()
                .validatetoken(idusers)
                .enqueue(object : Callback<token> {
                    override fun onResponse(call: Call<token>, response: Response<token>) {
                        if (response.isSuccessful) {
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    override fun onFailure(call: Call<token>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                    }

                })
        } else {
            Toast.makeText(
                this@LoginActivity,
                "Maaf, Sesi anda telah habis. Silahkan login kembali",
                Toast.LENGTH_SHORT
            ).show()
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
            .login(username, password)
            .enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<User>, response: Response<User>) {

                    if (response.isSuccessful) {

                        val id = response.body()?.id_users
                        val token = response.body()?.token

                        myPrefid.saveuserid(id.toString())
                        myPreftoken.saveusertoken(token.toString())

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Email / Password salah",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }
}