package com.example.conke.cocina.Utilities;

public class Utilities {


    /*Table system and fields*/
    public static final String TABLE_SYSTEM = "system";
    public static final String ID_SYSTEM= "id";
    public static final String FEATURE_NAME= "feature_name";
    public static final String FEATURE_STATUS = "feature_status";
    public static final String DROP_SYSTEM= "DROP TABLE IF EXISTS "+TABLE_SYSTEM;

    public static final String CREATE_SYSTEM_TABLE= "CREATE TABLE " +
            TABLE_SYSTEM+"("+
            ID_SYSTEM+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            FEATURE_NAME+" TEXT,"+
            FEATURE_STATUS+" INTEGER "+
            ")";

    /*Table supplier and fields*/
    public static final String TABLE_SUPPLIER = "supplier";
    public static final String ID_SUPPLIER= "id";
    public static final String SUPPLIER_NAME= "supplier_name";
    public static final String SUPPLIER_ICON = "supplier_icon";
    public static final String DROP_SUPPLIER= "DROP TABLE IF EXISTS "+TABLE_SUPPLIER;

    public static final String CREATE_SUPPLIER_TABLE= "CREATE TABLE " +
            TABLE_SUPPLIER+"("+
            ID_SUPPLIER+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            SUPPLIER_NAME+" TEXT,"+
            SUPPLIER_ICON+" INTEGER "+
            ")";

    /*Table product and fields*/
    public static final String TABLE_PRODUCT = "product";
    public static final String ID_PRODUCT= "id";
    public static final String SUPPLIER_ID= "supplier_id";
    public static final String PRODUCT_NAME= "product_name";
    public static final String PRODUCT_COST = "product_cost";
    public static final String DROP_PRODUCT= "DROP TABLE IF EXISTS "+TABLE_PRODUCT;

    public static final String CREATE_PRODUCT_TABLE= "CREATE TABLE " +
            TABLE_PRODUCT+" ( "+
            ID_PRODUCT+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            PRODUCT_NAME+" TEXT,"+
            PRODUCT_COST+" REAL,"+
            SUPPLIER_ID+" INTEGER,"+
            "FOREIGN KEY("+SUPPLIER_ID+") REFERENCES "+TABLE_SUPPLIER+"("+ID_SUPPLIER+")"+
            ")";



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



    /*Table order and fields*/
    public static final String TABLE_ORDER = "orders";
    public static final String ID_ORDER= "id";
    public static final String ORDER_TOTAL= "order_total";
    public static final String DROP_ORDER= "DROP TABLE IF EXISTS "+TABLE_ORDER;

    public static final String CREATE_ORDER_TABLE= "CREATE TABLE " +
            TABLE_ORDER+" ( "+
            ID_ORDER+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            ORDER_TOTAL+" REAL,"+
            STORE_ID+" INTEGER,"+
            SUPPLIER_ID+" INTEGER,"+
            "FOREIGN KEY("+STORE_ID+") REFERENCES "+TABLE_STORE+"("+ID_STORE+")"+
            ")";

    /*Table order_product and fields*/
    public static final String TABLE_ORDER_PRODUCT = "order_product";
    public static final String ORDER_ID= "order_id";
    public static final String PRODUCT_ID= "product_id";
    public static final String PRODUCT_QUANTITY= "product_quantity";
    public static final String DROP_ORDER_PRODUCT= "DROP TABLE IF EXISTS "+TABLE_ORDER_PRODUCT;

    public static final String CREATE_ORDER_PRODUCT_TABLE= "CREATE TABLE " +
            TABLE_ORDER_PRODUCT +" ( "+
            ORDER_ID+" INTEGER  NOT NULL,"+
            PRODUCT_ID+" INTEGER  NOT NULL,"+
            PRODUCT_QUANTITY+" INTEGER,"+
            SUPPLIER_ID+" INTEGER,"+
            "PRIMARY KEY("+ORDER_ID+","+PRODUCT_ID+"),"+
            "FOREIGN KEY("+PRODUCT_ID+") REFERENCES "+TABLE_PRODUCT+"("+ID_PRODUCT+"),"+
            "FOREIGN KEY("+ORDER_ID+") REFERENCES "+TABLE_ORDER+"("+ID_ORDER+")"+
            ")";

}
