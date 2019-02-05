package com.example.conke.cocina;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.conke.cocina.Utilities.Utilities;

/**
 Helper this is used to create and manage the Database
 */

public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       dropTables(db);
       onCreate(db);
    }

    private void dropTables(SQLiteDatabase db) {
        db.execSQL(Utilities.DROP_USERS);
        db.execSQL(Utilities.DROP_STORES);
    }

    public void createTables(SQLiteDatabase db){
        db.execSQL(Utilities.CREATE_STORE_TABLE);
        db.execSQL(Utilities.CREATE_USERS_TABLE);
    }

}
