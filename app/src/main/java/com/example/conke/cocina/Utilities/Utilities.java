package com.example.conke.cocina.Utilities;





public class Utilities {

    /*Table store and fields*/
    public static final String TABLE_STORE = "stores";
    public static final String ID_STORE = "id";
    public static final String STORE_NAME= "store_name";
    public static final String DROP_STORES= "DROP TABLE IF EXISTS "+TABLE_STORE;

    public static final String CREATE_STORE_TABLE= "CREATE TABLE " +
            TABLE_STORE+"("+
            ID_STORE+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            STORE_NAME+" TEXT"+
            ")";



    /*Table users and fields*/
    public static final String TABLE_USER = "users";
    public static final String ID_USER = "id";
    public static final String USER_NAME= "user_name";
    public static final String USER_MAIL= "user_mail";
    public static final String USER_PASSWORD= "user_password";
    public static final String STORE_ID= "store_id";

    public static final String DROP_USERS= "DROP TABLE IF EXISTS "+TABLE_USER;

    public static final String CREATE_USERS_TABLE= "CREATE TABLE " +
            TABLE_USER+" ( "+
            ID_USER+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            USER_NAME+" TEXT,"+
            USER_MAIL+" TEXT,"+
            USER_PASSWORD+" TEXT,"+
            STORE_ID+" INTEGER,"+
            "FOREIGN KEY("+STORE_ID+") REFERENCES "+TABLE_STORE+"("+ID_STORE+")"+
            ")";




}
