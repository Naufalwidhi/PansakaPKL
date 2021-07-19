package com.technobit.pansaka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.et_username_login)
        password = findViewById(R.id.et_password_login)
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
        }
    }
}