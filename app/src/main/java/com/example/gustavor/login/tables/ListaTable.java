package com.example.gustavor.login.tables;

/**
 * Created by gustavor on 19/06/2017.
 */

public interface ListaTable {


    String TABLE_NAME = "list";
    String COMA_SEPARATION = ", ";
    String ID = "_id";
    String LIST_NAME = "list_name";
    String USER_ID = "user_id";

    String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMA_SEPARATION
            + LIST_NAME + " TEXT" + COMA_SEPARATION
            + USER_ID + " INTEGER" + " );";

}
