package com.example.gustavor.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val loginUtils = LoginUtils(applicationContext)
        //verificando se o usuario já esta logado no app para direciona-lo para o login ou a main
        //verifica-se atraves do SharedPreferences
        if (loginUtils.isLogged) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            //essas flags adicionadas ao intent impedem que o usuario acesse a activity anterior
            //atraves do BackButton nativo.
            //o operador "|" faz uma comparação "ou" entre bits (isso é muito dahora suahsuahsauhs)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}
