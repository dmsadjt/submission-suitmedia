package com.example.suitmediatestapplication.data

import android.content.Context

class UserPreference(ctx: Context) {
    private val preference = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setName(name : String) {
        val editor = preference.edit()
        editor.putString(NAME, name)
        editor.apply()
    }

    fun setSelectedUser(user : String) {
        val editor = preference.edit()
        editor.putString(SELECTED_USER_NAME, user)
        editor.apply()
    }

    fun getUser() : User {
        val user = User(
            name = preference.getString(NAME, ""),
            selected = preference.getString(SELECTED_USER_NAME, "")
        )
        return user
    }

    companion object {
        private const val PREF_NAME = "user_pref"
        private const val NAME = "name"
        private const val SELECTED_USER_NAME = "selected_user_name"
    }
}