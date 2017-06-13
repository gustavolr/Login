package com.example.gustavor.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(my_toolbar as Toolbar)
        val userDao = UserDao(applicationContext)
        //referencia direta ao ID da view hehe
        confirmB.setOnClickListener {
            if (validateFields()) {
                //salva o usuario do cara no SharedPreferences e tambem o salva como "logado"
                val loginUtils = LoginUtils(applicationContext)
                loginUtils.saveLogin(username.text.toString())
                //inserindo no db
                userDao!!.insertUser(username.text.toString(), email.text.toString(), password.text.toString())
                val toast = Toast.makeText(applicationContext, getString(R.string.registered_login) + " " + username.text.toString() + "!", Toast.LENGTH_SHORT)
                toast.show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
        }

        cancel.setOnClickListener { onBackPressed() }
    }

    //pode alterar esse metodo para que ele se encaixe a suas necessidades de persistencia de dados
    private fun validateFields(): Boolean {
        if (username.text.toString().isEmpty()) {
            //setError e requestFocus são otimos para alertar o usuario de que algo esta errado
            username.error = getString(R.string.username_error)
            username.requestFocus()
            return false
        } else if (email.text.toString().isEmpty()) {
            email.error = getString(R.string.email_error)
            email.requestFocus()
            return false
        } else if (password.text.toString().isEmpty()) {
            password.error = getString(R.string.password_error)
            password.requestFocus()
            return false
        } else if (password.text.toString() != confirmP.text.toString()) {
            confirmP.error = getString(R.string.confirm_password_error)
            confirmP.requestFocus()
            return false
        }
        return true
    }
}
