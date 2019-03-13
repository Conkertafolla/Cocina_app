package com.example.conke.cocina.SQL;

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
        db.execSQL(Utilities.DROP_SYSTEM);
        db.execSQL(Utilities.DROP_SUPPLIER);
        db.execSQL(Utilities.DROP_PRODUCT);
        db.execSQL(Utilities.DROP_ORDER);
        db.execSQL(Utilities.DROP_ORDER_PRODUCT);
    }

    public void createTables(SQLiteDatabase db){
        db.execSQL(Utilities.CREATE_STORE_TABLE);
        db.execSQL(Utilities.CREATE_USERS_TABLE);
        db.execSQL(Utilities.CREATE_SYSTEM_TABLE);
        db.execSQL(Utilities.CREATE_SUPPLIER_TABLE);
        db.execSQL(Utilities.CREATE_PRODUCT_TABLE);
        db.execSQL(Utilities.CREATE_ORDER_TABLE);
        db.execSQL(Utilities.CREATE_ORDER_PRODUCT_TABLE);
    }

}
