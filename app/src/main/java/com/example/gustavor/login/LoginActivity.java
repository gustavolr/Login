package com.example.gustavor.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername, mPassword;
    private Button mConfirm, mRegister;
    private UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //como eu quero customizar a minha Toolbar eu criei uma para mim hehe
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //mas tenho de dizer para o codigo q ela é a toolbar que ele devo usar
        //assim como alterar o tema para algum que não a inclua, isso pode ser visto em styles
        setSupportActionBar(myToolbar);
        userDao = new UserDao(getApplicationContext());
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mConfirm = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUsername.getText().toString().isEmpty()) {
                    mUsername.setError(getString(R.string.username_error));
                    mUsername.requestFocus();
                } else if (mPassword.getText().toString().isEmpty()) {
                    mPassword.setError(getString(R.string.password_error));
                    mPassword.requestFocus();
                } else if(userDao.getUser(mUsername.getText().toString()).getSenha() == null){
                    //verifica seexiste algum usuario com esse username
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.invalid_username), Toast.LENGTH_SHORT);
                    toast.show();
                    mUsername.requestFocus();
                } else if (!userDao.getUser(mUsername.getText().toString()).getSenha().equals(mPassword.getText().toString())) {
                    mPassword.requestFocus();
                    mPassword.setError(getString(R.string.invalid_password));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.welcome) + " " + mUsername.getText().toString() + "!", Toast.LENGTH_SHORT);
                    toast.show();
                    LoginUtils loginUtils = new LoginUtils(getApplicationContext());
                    loginUtils.saveLogin(mUsername.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }

}
