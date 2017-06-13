package com.example.gustavor.login

/**
 * Created by gustavor on 12/06/2017.
 */

interface UserTable {
    companion object {
        //cria-se uma interface correspondente a cada tabela do banco, contendo uma string para cada campo
        //assim como a string para criaação da tabela.
        val TABLE_NAME = "user"
        val COMA_SEPARATION = ", "
        val ID = "_id"
        val USER_USERNAME = "user_username"
        val USER_EMAIL = "user_email"
        val USER_SENHA = "user_senha"

        val SQL_CREATE = "CREATE TABLE $TABLE_NAME ( $ID INTEGER PRIMARY KEY AUTOINCREMENT$COMA_SEPARATION$USER_USERNAME TEXT$COMA_SEPARATION$USER_EMAIL TEXT$COMA_SEPARATION$USER_SENHA TEXT);"
    }

}
