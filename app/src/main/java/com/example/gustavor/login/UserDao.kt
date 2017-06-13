package com.example.gustavor.login

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 * Created by gustavor on 12/06/2017.
 */

class UserDao(private val mContext: Context) {
    //os DAO são utilizados para realizar qualquer oprração no db, qualquer insert, update, select...
    //sera feito aqui.
    private val mSqlite: SQLiteDatabase

    init {
        val gamingPortalOpenHelper = DBOpenHelper(mContext)
        mSqlite = gamingPortalOpenHelper.writableDatabase
    }

    fun getUser(select: String): User {
        val find = UserTable.USER_USERNAME + " = '" + select + "';"
        val user = User()
        val cursor = mSqlite.query(UserTable.TABLE_NAME, null, find, null, null, null, null)
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                user.setmId(cursor.getInt(cursor.getColumnIndex(UserTable.ID)))
                user.setmUsername(cursor.getString(cursor.getColumnIndex(UserTable.USER_USERNAME)))
                user.setmEmail(cursor.getString(cursor.getColumnIndex(UserTable.USER_EMAIL)))
                user.senha = cursor.getString(cursor.getColumnIndex(UserTable.USER_SENHA))
            }
            cursor.close()
        }
        return user
    }

    fun insertUser(username: String, email: String, senha: String) {
        val contentValues = ContentValues()
        contentValues.put(UserTable.USER_USERNAME, username)
        contentValues.put(UserTable.USER_EMAIL, email)
        contentValues.put(UserTable.USER_SENHA, senha)
        mSqlite.insert(UserTable.TABLE_NAME, null, contentValues)
    }

}
