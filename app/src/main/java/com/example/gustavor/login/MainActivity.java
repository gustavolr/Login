package com.example.gustavor.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        LoginUtils loginUtils = new LoginUtils(getApplicationContext());
    }

    //já que eu tenho a minha propria toolbar, eu tenho que popular ela com meu item de menu
    //no caso o de logout, ele esta definido em menu->toolbar_items.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //aqui eu trato o click dos botãoes de menu, no caso, realizo logout quando clicado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logoff:
                LoginUtils loginUtils = new LoginUtils(getApplicationContext());
                loginUtils.eraseData();
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.logout), Toast.LENGTH_SHORT);
                toast.show();
                final Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true; //não sei pq diabos mas precisa desse return true

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }

    }

}
