package com.example.gustavor.login.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavor.login.models.ItemCompra;
import com.example.gustavor.login.models.Lista;
import com.example.gustavor.login.tables.ItemCompraTable;
import com.example.gustavor.login.tables.ListaTable;
import com.example.gustavor.login.utils.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavor on 19/06/2017.
 */

public class ItemCompraDao {

    private Context mContext;
    private SQLiteDatabase mSqlite;

    public ItemCompraDao(Context ctx) {
        mContext = ctx;
        DBOpenHelper gamingPortalOpenHelper = new DBOpenHelper(mContext);
        mSqlite = gamingPortalOpenHelper.getWritableDatabase();
    }

    public List<ItemCompra> getItemsCompra(int listId){
        String find = ItemCompraTable.LIST_ID + " = '" + listId + "';";
        List<ItemCompra> itemCompras = new ArrayList<>();
        final Cursor cursor = mSqlite.query(ItemCompraTable.TABLE_NAME, null, find, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    ItemCompra itemCompra = new ItemCompra();
                    itemCompra.setmId(cursor.getInt(cursor.getColumnIndex(ItemCompraTable.ID)));
                    itemCompra.setmListId(cursor.getInt(cursor.getColumnIndex(ItemCompraTable.LIST_ID)));
                    itemCompra.setmItemName(cursor.getString(cursor.getColumnIndex(ItemCompraTable.ITEM_NAME)));
                    itemCompra.setmQtd(cursor.getInt(cursor.getColumnIndex(ItemCompraTable.ITEM_QTD)));
                    itemCompra.setComprado(cursor.getInt(cursor.getColumnIndex(ItemCompraTable.ITEM_COMPRADO)));
                    itemCompras.add(itemCompra);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        return itemCompras;
    }

    public void insertItemCompra(int listId, String itemName, int qtd) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemCompraTable.LIST_ID, listId);
        contentValues.put(ItemCompraTable.ITEM_NAME, itemName);
        contentValues.put(ItemCompraTable.ITEM_QTD, qtd);
        contentValues.put(ItemCompraTable.ITEM_COMPRADO, 0);
        mSqlite.insert(ItemCompraTable.TABLE_NAME, null, contentValues);
    }

    public void setItemComprado(int itemId){
        String find = ItemCompraTable.ID + " = '" + itemId + "';";
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemCompraTable.ITEM_COMPRADO, 1);
        mSqlite.update(ItemCompraTable.TABLE_NAME, contentValues, find, null);
    }

    public void unsetItemComprado(int itemId){
        String find = ItemCompraTable.ID + " = '" + itemId + "';";
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemCompraTable.ITEM_COMPRADO, 0);
        mSqlite.update(ItemCompraTable.TABLE_NAME, contentValues, find, null);
    }

    public void removeItem(int itemId) {
        String find = ItemCompraTable.ID + " = '" + itemId + "';";
        mSqlite.delete(ItemCompraTable.TABLE_NAME, find, null);
    }

    public void removeItemsLista(int listaId) {
        String find = ItemCompraTable.LIST_ID + " = '" + listaId + "';";
        mSqlite.delete(ItemCompraTable.TABLE_NAME, find, null);
    }
}
