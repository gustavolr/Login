package com.example.gustavor.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.my_toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar as Toolbar)
    }

    //já que eu tenho a minha propria toolbar, eu tenho que popular ela com meu item de menu
    //no caso o de logout, ele esta definido em menu->toolbar_items.xml
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //aqui eu trato o click dos botãoes de menu, no caso, realizo logout quando clicado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logoff -> {
                val loginUtils = LoginUtils(applicationContext)
                loginUtils.eraseData()
                val toast = Toast.makeText(applicationContext, getString(R.string.logout), Toast.LENGTH_SHORT)
                toast.show()
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                return true //não sei pq diabos mas precisa desse return true
            }

            else ->
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item)
        }

    }

}
