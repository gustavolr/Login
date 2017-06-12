package com.example.gustavor.login;

/**
 * Created by gustavor on 12/06/2017.
 */

public class User {
    //criasse tambem uma classe com setters and getters para cada tabela, para facilitar as transações
    //no banco de dados.
    public int mId;
    public String mUsername;
    public String mEmail;
    public String Senha;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }
}
