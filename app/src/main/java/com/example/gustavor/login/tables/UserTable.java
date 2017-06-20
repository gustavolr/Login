package com.example.gustavor.login.tables;

/**
 * Created by gustavor on 12/06/2017.
 */

public interface UserTable {
    //cria-se uma interface correspondente a cada tabela do banco, contendo uma string para cada campo
    //assim como a string para criaação da tabela.
    String TABLE_NAME = "user";
    String COMA_SEPARATION = ", ";
    String ID = "_id";
    String USER_USERNAME = "user_username";
    String USER_EMAIL = "user_email";
    String USER_SENHA = "user_senha";

    String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMA_SEPARATION + USER_USERNAME + " TEXT" + COMA_SEPARATION + USER_EMAIL
            + " TEXT" + COMA_SEPARATION + USER_SENHA + " TEXT" + ");";

}
