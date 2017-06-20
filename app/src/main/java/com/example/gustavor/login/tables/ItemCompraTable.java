package com.example.gustavor.login.tables;

/**
 * Created by gustavor on 19/06/2017.
 */

public interface ItemCompraTable {

    String TABLE_NAME = "item";
    String COMA_SEPARATION = ", ";
    String ID = "_id";
    String LIST_ID = "list_id";
    String ITEM_NAME = "item_name";
    String ITEM_QTD = "item_qtd";
    String ITEM_COMPRADO = "item_comprado";

    String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMA_SEPARATION
            + LIST_ID + " INTEGER" + COMA_SEPARATION
            + ITEM_NAME + " TEXT" + COMA_SEPARATION
            + ITEM_QTD + " INTEGER" + COMA_SEPARATION
            + ITEM_COMPRADO + " INTEGER" + " );";

}
