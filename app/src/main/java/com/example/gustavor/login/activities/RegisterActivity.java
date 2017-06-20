package com.example.gustavor.login.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gustavor.login.utils.LoginUtils;
import com.example.gustavor.login.R;
import com.example.gustavor.login.daos.UserDao;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsername, mEmail, mPassword, mConfirmP;
    private Button mValidate, mCancel;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        mUsername = (EditText) findViewById(R.id.username);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mConfirmP = (EditText) findViewById(R.id.confirmP);
        mValidate = (Button) findViewById(R.id.confirmB);
        mCancel = (Button) findViewById(R.id.cancel);
        userDao = new UserDao(getApplicationContext());
        mValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    if(!userDao.insertUser(mUsername.getText().toString(), mEmail.getText().toString(), mPassword.getText().toString())){
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.repetitive_user), Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        LoginUtils loginUtils = new LoginUtils(getApplicationContext());
                        loginUtils.saveLogin(mUsername.getText().toString(), userDao.getUser(mUsername.getText().toString()).getmId());
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.registered_login) + " " + mUsername.getText().toString() + "!", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    //pode alterar esse metodo para que ele se encaixe a suas necessidades de persistencia de dados
    private boolean validateFields() {
        if (mUsername.getText().toString().isEmpty()) {
            //setError e requestFocus são otimos para alertar o usuario de que algo esta errado
            mUsername.setError(getString(R.string.username_error));
            mUsername.requestFocus();
            return false;
        } else if (mEmail.getText().toString().isEmpty()) {
            mEmail.setError(getString(R.string.email_error));
            mEmail.requestFocus();
            return false;
        } else if (mPassword.getText().toString().isEmpty()) {
            mPassword.setError(getString(R.string.password_error));
            mPassword.requestFocus();
            return false;
        } else if (!mPassword.getText().toString().equals(mConfirmP.getText().toString())){
            mConfirmP.setError(getString(R.string.confirm_password_error));
            mConfirmP.requestFocus();
            return false;
        }
        return true;
    }
}
