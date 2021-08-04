package com.technobit.pansaka.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.technobit.pansaka.R

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var passlama : EditText
    private lateinit var passbaru : EditText
    private lateinit var repassbaru : EditText
    private lateinit var btnchange : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        passlama = findViewById(R.id.et_password_sekarang_change)
        passbaru = findViewById(R.id.et_password_baru_change)
        repassbaru = findViewById(R.id.et_confirm_password_change)
        btnchange = findViewById(R.id.btn_ganti_password)

        btnchange.setOnClickListener {
            if (passlama.text.toString().isEmpty()) {
                passlama.error = "Mohon Masukkan Password Lama"
                passlama.requestFocus()
                return@setOnClickListener
            }
            if (passbaru.text.toString().isEmpty()) {
                passbaru.error = "Mohon Masukkan Password Baru"
                passbaru.requestFocus()
                return@setOnClickListener
            }
            if (repassbaru.text.toString().isEmpty()) {
                repassbaru.error = "Mohon Masukkan Ulang Password Baru"
                repassbaru.requestFocus()
                return@setOnClickListener
            }
            if (repassbaru.text.toString() != passbaru.text.toString()){
                passbaru.error = "Your Password Isn't Match"
                passbaru.requestFocus()
                return@setOnClickListener
            }
            change()
        }
    }
    private fun change(){

    }
}