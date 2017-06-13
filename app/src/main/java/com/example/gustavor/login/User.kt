package com.example.gustavor.login

/**
 * Created by gustavor on 12/06/2017.
 */

class User {
    //criasse tambem uma classe com setters and getters para cada tabela, para facilitar as transações
    //no banco de dados.
    var mId: Int = 0
    var mUsername: String = ""
    var mEmail: String = ""
    var senha: String = ""

    fun getmId(): Int {
        return mId
    }

    fun setmId(mId: Int) {
        this.mId = mId
    }

    fun getmUsername(): String {
        return mUsername
    }

    fun setmUsername(mUsername: String) {
        this.mUsername = mUsername
    }

    fun getmEmail(): String {
        return mEmail
    }

    fun setmEmail(mEmail: String) {
        this.mEmail = mEmail
    }
}
