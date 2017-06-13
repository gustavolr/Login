package com.example.gustavor.login

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by gustavor on 12/06/2017.
 */

class LoginUtils(ctx: Context) {
    //LoginUtils é a minha SharedPreferences do android, qualquer dado salvo aqui sera persistido
    //para futuras inicializações do app, assim tambem podendo serem buscados a partir de qualquer
    //classe do projeto
    private val mSharedPreferences: SharedPreferences

    init {
        mSharedPreferences = ctx.getSharedPreferences(IS_LOGGED, Context.MODE_PRIVATE)
    }

    fun saveLogin(username: String) {
        val editor = mSharedPreferences.edit()
        editor.putBoolean(IS_LOGGED, true)
        editor.putString(USER_NAME, username)
        editor.apply()
    }

    fun eraseData() {
        val editor: SharedPreferences.Editor = mSharedPreferences.edit()
        editor.putBoolean(IS_LOGGED, false)
        editor.putString(USER_NAME, null)
        editor.apply()
    }

    val userName: String
        get() = mSharedPreferences.getString(USER_NAME, "shit")

    val isLogged: Boolean
        get() = mSharedPreferences.getBoolean(IS_LOGGED, false)

    companion object {
        val IS_LOGGED = "entrou"
        val USER_NAME = "username"
    }

}
