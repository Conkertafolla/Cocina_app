package com.example.conke.cocina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.conke.cocina.Entities.Store;
import com.example.conke.cocina.Entities.usuario;
import com.example.conke.cocina.Utilities.Utilities;

public class SQLQueries {
    private Context queryContext;



    public SQLQueries(Context queryContext){
        this.queryContext = queryContext;

    }

    public SQLHelper getConection(){
         SQLHelper connection = new SQLHelper(queryContext,"db_order",null,1);
         return connection;

    }

    public String userStore(String userMail){
        SQLHelper connection=getConection();
        String storeName = "";
        SQLiteDatabase db= connection.getReadableDatabase();
        String [] parameters={userMail};
        String query = "SELECT "+Utilities.STORE_NAME+
                " FROM "+Utilities.TABLE_STORE+" s"+
                " JOIN "+Utilities.TABLE_USER+" u ON u."+Utilities.STORE_ID+" = s."+Utilities.ID_STORE+
                " WHERE u."+Utilities.USER_MAIL+" = ?";
        Cursor cursor= db.rawQuery(query,parameters);
        try{
            if(cursor.moveToFirst()){
                storeName =cursor.getString(0);
            }else {
                Log.d("SQL","No existe el registro");
            }

        }catch (Exception ex){
            Log.d("SQL","ERROR"+ex);

        }
        cursor.close();
        db.close();
        return storeName;
    }


    public boolean validUser(String email, String password){
        SQLHelper connection=getConection();
        boolean isUser=false;
        SQLiteDatabase db= connection.getReadableDatabase();
        String [] parameters={email,password};
        String [] fields={Utilities.ID_USER,Utilities.USER_NAME,Utilities.STORE_ID};
        String query = Utilities.USER_MAIL+" = ? AND "+ Utilities.USER_PASSWORD + " = ? ";


        Cursor cursor =db.query(Utilities.TABLE_USER,
                                fields,
                               query,
                                parameters,
                               null,
                               null,
                               null);

        try{
            if(cursor.moveToFirst()){
                cursor.close();
                db.close();
                isUser= true;

            }else {
                cursor.close();
                db.close();
                Log.d("SQL","No existe el registro");
                isUser= false;
            }

        }catch (Exception ex){
            Log.d("SQL","ERROR"+ex);

        }

        return isUser;
    }

    public void  setDefaultValues(){
        long fristStore=1;
        Store store= new Store("Polanco");
        insertStore(store);
        usuario user = new usuario(((int) fristStore),"Gerado Tafolla","conkertafolla2.0@gmail.com","Password123");
        insertUser(user);

    }

    public long insertStore(Store store){
        long storeID;
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getWritableDatabase();
        ContentValues register= new ContentValues();
        register.put(Utilities.STORE_NAME,store.getStoreName().toString());
        storeID=db.insert(Utilities.TABLE_STORE,Utilities.ID_STORE,register);

        if (storeID == -1){
            Toast.makeText(queryContext,"Registro Erroneo tienda", Toast.LENGTH_LONG);
        }
        else {
            Toast.makeText(queryContext, "Registro Exitoso", Toast.LENGTH_LONG);
            Log.d("SQL","Insert Exitoso");
        }
        db.close();
        return storeID;
    }

    

    public long insertUser(usuario user){
        long userID;
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getWritableDatabase();
        ContentValues register= new ContentValues();
        register.put(Utilities.USER_NAME,user.getUserName().toString());
        register.put(Utilities.USER_MAIL,user.getUserMail().toString());
        register.put(Utilities.USER_PASSWORD,user.getUserPassword().toString());
        register.put(Utilities.STORE_ID,user.getStoreId());
        userID=db.insert(Utilities.TABLE_USER,Utilities.ID_USER,register);

        if (userID == -1){
            Toast.makeText(queryContext,"Registro Erroneo usuario", Toast.LENGTH_LONG);
        }
        else {
            Toast.makeText(queryContext, "Registro Exitoso", Toast.LENGTH_LONG);
            Log.d("SQL","Insert Exitoso");
        }
        db.close();
        return userID;

    }
}
