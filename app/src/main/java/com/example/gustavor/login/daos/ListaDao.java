package com.example.gustavor.login.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavor.login.models.Lista;
import com.example.gustavor.login.tables.ListaTable;
import com.example.gustavor.login.utils.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavor on 19/06/2017.
 */

public class ListaDao {

    private Context mContext;
    private SQLiteDatabase mSqlite;

    public ListaDao(Context ctx) {
        mContext = ctx;
        DBOpenHelper gamingPortalOpenHelper = new DBOpenHelper(mContext);
        mSqlite = gamingPortalOpenHelper.getWritableDatabase();
    }

    public List<Lista> getListas(int userId){
        String find = ListaTable.USER_ID + " = '" + userId + "';";
        List<Lista> listas = new ArrayList<>();
        final Cursor cursor = mSqlite.query(ListaTable.TABLE_NAME, null, find, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Lista lista = new Lista();
                    lista.setmId(cursor.getInt(cursor.getColumnIndex(ListaTable.ID)));
                    lista.setmListName(cursor.getString(cursor.getColumnIndex(ListaTable.LIST_NAME)));
                    lista.setmUserId(cursor.getInt(cursor.getColumnIndex(ListaTable.USER_ID)));
                    listas.add(lista);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        return listas;
    }

    public void insertLista(String listName, int userId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ListaTable.LIST_NAME, listName);
        contentValues.put(ListaTable.USER_ID, userId);
        mSqlite.insert(ListaTable.TABLE_NAME, null, contentValues);
    }

    public void removeLista(int listaId) {
        String find = ListaTable.ID + " = '" + listaId + "';";
        ItemCpompraDao itemCpompraDao = new ItemCpompraDao(mContext);
        itemCpompraDao.removeItemsLista(listaId);
        mSqlite.delete(ListaTable.TABLE_NAME, find, null);
    }

}
