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
import com.technobit.pansaka.model.ChangeResponse
import com.technobit.pansaka.model.PrefsToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {

    private val myPreftoken by lazy { PrefsToken(this) }
    private lateinit var passlama: EditText
    private lateinit var passbaru: EditText
    private lateinit var repassbaru: EditText
    private lateinit var btnganti: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        passlama = findViewById(R.id.et_password_lama)
        passbaru = findViewById(R.id.et_password_baru)
        repassbaru = findViewById(R.id.et_confirm_password)
        btnganti = findViewById(R.id.btn_ganti_password)

        btnganti.setOnClickListener {
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
                repassbaru.error = "Mohon Masukkan Kembali Password Baru"
                repassbaru.requestFocus()
                return@setOnClickListener
            }
            if (passbaru.text.toString().equals(repassbaru.text.toString())) {
                gantipassword(passbaru.text.toString())
            } else{
                Toast.makeText(this, "Mohon Konfirmasi lagi Password Baru", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun gantipassword(passbaru: String) {
        val token = "Bearer "+myPreftoken.getusertoken()
        Client.myApiClient().changepassword(constant.appId, constant.key, token, passbaru)
            .enqueue(object : Callback<ChangeResponse>{
                override fun onFailure(call: Call<ChangeResponse>, t: Throwable) {
                    Toast.makeText(this@ChangePasswordActivity, t.message, Toast.LENGTH_LONG).show()
                }
                override fun onResponse(
                    call: Call<ChangeResponse>,
                    response: Response<ChangeResponse>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            val message = it.message
                            if (message.equals("Password berhasil diubah")){
                                Toast.makeText(this@ChangePasswordActivity, "Password Berhasil Diubah", Toast.LENGTH_LONG).show()
                                val intent = Intent(this@ChangePasswordActivity, MainActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this@ChangePasswordActivity, "User Tidak Ditemukan", Toast.LENGTH_LONG).show()
                            }
                        }
                    }else{
                        Toast.makeText(this@ChangePasswordActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }
}