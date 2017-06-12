package com.example.gustavor.login;

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
    }


    public static final String IS_LOGGED = "entrou";
    public static final String USER_NAME = "username";

    public void saveLogin(String username) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(IS_LOGGED, true);
        editor.putString(USER_NAME, username);
        editor.apply();
    }

    public void eraseData() {
        SharedPreferences.Editor editor;
        editor = mSharedPreferences.edit();
        editor.putBoolean(IS_LOGGED, false);
        editor.putString(USER_NAME, null);
        editor.apply();
    }

    public String getUserName() {
        return mSharedPreferences.getString(USER_NAME, "shit");
    }

    public boolean isLogged() {
        return mSharedPreferences.getBoolean(IS_LOGGED, false);
    }

}
