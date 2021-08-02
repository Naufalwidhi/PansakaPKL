package com.technobit.pansaka.model

import android.content.Context
import androidx.core.content.edit

class PrefsId(context: Context) {
    private val prefs by lazy { context.getSharedPreferences("PansakaId", 0) }
    fun saveuserid(id: String) {
        prefs.edit {
            putString("id", id)
            commit()
        }
    }

    fun getuserid() = prefs.getString("id", "")!!
}

class PrefsToken(context: Context) {
    private val prefs by lazy { context.getSharedPreferences("PansakaToken", 0) }
    fun saveusertoken(token: String) {
        prefs.edit {
            putString("token", token)
            commit()
        }
    }

    fun getusertoken() = prefs.getString("token", "")!!
}