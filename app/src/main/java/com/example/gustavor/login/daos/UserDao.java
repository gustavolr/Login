package com.example.gustavor.login.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavor.login.utils.DBOpenHelper;
import com.example.gustavor.login.models.User;
import com.example.gustavor.login.tables.UserTable;

/**
 * Created by gustavor on 12/06/2017.
 */

public class UserDao {
    //os DAO são utilizados para realizar qualquer oprração no db, qualquer insert, update, select...
    //sera feito aqui.
    private Context mContext;
    private SQLiteDatabase mSqlite;

    public UserDao(Context ctx) {
        mContext = ctx;
        DBOpenHelper gamingPortalOpenHelper = new DBOpenHelper(mContext);
        mSqlite = gamingPortalOpenHelper.getWritableDatabase();
    }

    public User getUser(String select) {
        String find = UserTable.USER_USERNAME + " = '" + select + "';";
        User user = new User();
        final Cursor cursor = mSqlite.query(UserTable.TABLE_NAME, null, find, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                user.setmId(cursor.getInt(cursor.getColumnIndex(UserTable.ID)));
                user.setmUsername(cursor.getString(cursor.getColumnIndex(UserTable.USER_USERNAME)));
                user.setmEmail(cursor.getString(cursor.getColumnIndex(UserTable.USER_EMAIL)));
                user.setSenha(cursor.getString(cursor.getColumnIndex(UserTable.USER_SENHA)));
            }
            cursor.close();
        }
        return user;
    }

    public boolean insertUser(String username, String email, String senha) {
        String find = UserTable.USER_USERNAME + " = '" + username + "';";
        final Cursor cursor = mSqlite.query(UserTable.TABLE_NAME, null, find, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserTable.USER_USERNAME, username);
        contentValues.put(UserTable.USER_EMAIL, email);
        contentValues.put(UserTable.USER_SENHA, senha);
        mSqlite.insert(UserTable.TABLE_NAME, null, contentValues);
        return true;

    }

}
