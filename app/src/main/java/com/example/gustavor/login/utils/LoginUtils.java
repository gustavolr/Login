package com.example.gustavor.login.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gustavor on 12/06/2017.
 */

public class LoginUtils {
    //LoginUtils é a minha SharedPreferences do android, qualquer dado salvo aqui sera persistido
    //para futuras inicializações do app, assim tambem podendo serem buscados a partir de qualquer
    //classe do projeto
    private SharedPreferences mSharedPreferences;

    public LoginUtils(Context ctx) {
        mSharedPreferences = ctx.getSharedPreferences(IS_LOGGED, Context.MODE_PRIVATE);
        mSharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE);
        mSharedPreferences = ctx.getSharedPreferences(USER_ID, Context.MODE_PRIVATE);
    }


    public static final String IS_LOGGED = "entrou";
    public static final String USER_NAME = "username";
    public static final String USER_ID = "user_id";

    public void saveLogin(String username, int userId) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(IS_LOGGED, true);
        editor.putString(USER_NAME, username);
        editor.putInt(USER_ID, userId);
        editor.apply();
    }

    public void eraseData() {
        SharedPreferences.Editor editor;
        editor = mSharedPreferences.edit();
        editor.putBoolean(IS_LOGGED, false);
        editor.putString(USER_NAME, null);
        editor.putInt(USER_ID, -1);
        editor.apply();
    }

    public String getUserName() {
        return mSharedPreferences.getString(USER_NAME, "shit");
    }

    public int getUserId() {
        return mSharedPreferences.getInt(USER_ID, 0);
    }

    public boolean isLogged() {
        return mSharedPreferences.getBoolean(IS_LOGGED, false);
    }

}
