package com.example.gustavor.login.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gustavor.login.utils.LoginUtils;
import com.example.gustavor.login.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LoginUtils loginUtils = new LoginUtils(getApplicationContext());
        //verificando se o usuario já esta logado no app para direciona-lo para o login ou a main
        //verifica-se atraves do SharedPreferences
        if (loginUtils.isLogged()) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            //essas flags adicionadas ao intent impedem que o usuario acesse a activity anterior
            //atraves do BackButton nativo.
            //o operador "|" faz uma comparação "ou" entre bits (isso é muito dahora suahsuahsauhs)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
