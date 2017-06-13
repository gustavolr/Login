package com.example.gustavor.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //como eu quero customizar a minha Toolbar eu criei uma para mim hehe
        val myToolbar = findViewById(R.id.my_toolbar) as Toolbar
        //mas tenho de dizer para o codigo q ela é a toolbar que ele devo usar
        //assim como alterar o tema para algum que não a inclua, isso pode ser visto em styles
        setSupportActionBar(myToolbar)
        val userDao = UserDao(applicationContext)
        register.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            if (username.text.toString().isEmpty()) {
                username.error = getString(R.string.username_error)
                username.requestFocus()
            } else if (password.text.toString().isEmpty()) {
                password.error = getString(R.string.password_error)
                password.requestFocus()
            } else if (userDao.getUser(username.text.toString()).senha == null) { //não sei pq diabos ele fala q é sempre false sendo q ja fiz dar true
                //verifica seexiste algum usuario com esse username
                val toast = Toast.makeText(applicationContext, getString(R.string.invalid_username), Toast.LENGTH_SHORT)
                toast.show()
                username.requestFocus()
            } else if (userDao!!.getUser(username.text.toString()).senha != password.text.toString()) {
                password.requestFocus()
                password.error = getString(R.string.invalid_password)
            } else {
                val toast = Toast.makeText(applicationContext, getString(R.string.welcome) + " " + username.text.toString() + "!", Toast.LENGTH_SHORT)
                toast.show()
                val loginUtils = LoginUtils(applicationContext)
                loginUtils.saveLogin(username.text.toString())
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
        }
    }

}
