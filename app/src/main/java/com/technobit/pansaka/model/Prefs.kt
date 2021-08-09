package com.technobit.pansaka.model

import android.content.Context
import androidx.core.content.edit

class PrefsToken(context: Context) {
    private val prefs by lazy { context.getSharedPreferences("PansakaToken", 0) }
    fun saveusertoken(token: String) {
        prefs.edit {
            putString("token", token)
            commit()
        }
    }

    fun getusertoken() = prefs.getString("token", "")!!

    fun deletetoken() = prefs.edit().remove("token").commit()
}