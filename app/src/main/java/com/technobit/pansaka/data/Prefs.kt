package com.technobit.pansaka.data

import android.content.Context
import androidx.core.content.edit

class Prefs(context: Context) {
    private val prefs by lazy { context.getSharedPreferences("Pansaka", 0) }
    fun saveuser(id: Int, name: String, username: String, email: String, token: String){
        prefs.edit {
            putInt("id", id)
            putString("name", name)
            putString("username", username)
            putString("email", email)
            putString("token", token)
            commit()
        }
    }
    fun getuserid() = prefs.getString("id", "")!!
    fun getusername() = prefs.getString("name", "")!!
    fun getusername1() = prefs.getString("username", "")!!
    fun getuseremail() = prefs.getString("email", "")!!
    fun getusertoken() = prefs.getString("token", "")!!


}