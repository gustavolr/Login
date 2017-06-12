package com.example.gustavor.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gustavor on 12/06/2017.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    //Classe responsavel pela criação do banco de dados, assim como seu update.
    public DBOpenHelper(Context ctx) {
        super(ctx, "MyDatabase.db", null, 1); //o 1 nesse caso se refere a versão do banco
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.SQL_CREATE); //utilizando a string para criar o banco
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //aqui deve-se verificar se a verção do banco atual é dierente da versão mais nova
        //isso pode acontecer quando o usuario faz update da app e alguma tabela é alterada, deve-se
        //lidar com o banco de dados novamente (não lembro exatamente o que se faz nesse caso, mas
        //tenho quase certeza q se apaga a tabela e a cria novamente)
    }
}
