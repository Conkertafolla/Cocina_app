package com.example.conke.cocina.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.conke.cocina.Entities.Order;
import com.example.conke.cocina.Entities.Product;
import com.example.conke.cocina.Entities.Store;
import com.example.conke.cocina.Entities.Supplier;
import com.example.conke.cocina.Entities.usuario;
import com.example.conke.cocina.Utilities.Utilities;

import java.util.ArrayList;

public class SQLQueries {
    private Context queryContext;



    public SQLQueries(Context queryContext){
        this.queryContext = queryContext;

    }

    public SQLHelper getConection(){
         SQLHelper connection = new SQLHelper(queryContext,"db_order",null,1);
         return connection;

    }

    public Store userStore(String userMail){
        SQLHelper connection=getConection();
        Store storeName = new Store();
        SQLiteDatabase db= connection.getReadableDatabase();
        String [] parameters={userMail};
        String query = "SELECT "+Utilities.STORE_NAME+","+Utilities.STORE_ID+
                " FROM "+Utilities.TABLE_STORE+" s"+
                " JOIN "+Utilities.TABLE_USER+" u ON u."+Utilities.STORE_ID+" = s."+Utilities.ID_STORE+
                " WHERE u."+Utilities.USER_MAIL+" = ?";
        Cursor cursor= db.rawQuery(query,parameters);
        try{
            if(cursor.moveToFirst()){
                storeName.setStoreName(cursor.getString(0));
                storeName.setStoreId(cursor.getInt(1));
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
        usuario user = new usuario(((int) fristStore),"Mariana Chaparro","mariana.chaparro3@gmail.com","Avaya2019 ");
        insertUser(user);
        Supplier supplier = new Supplier("Costco","costco");
        insertSupplier(supplier);
        supplier = new Supplier("Central de Abastos","central");
        insertSupplier(supplier);
        Product product = new Product(1,"Aceite(Galon)",100.0f);
        insertProduct(product);
        product = new Product(1,"Mayonesa(Galon)",125.0f);
        insertProduct(product);
        product = new Product(1,"Chipotle (Paq. 6)",100.0f);
        insertProduct(product);
        product = new Product(1,"Mostaza(Bote)",67.5f);
        insertProduct(product);
        product = new Product(1,"Catsup (Paq. 2pzas)",70.0f);
        insertProduct(product);
        product = new Product(1,"Tenedores",149.0f);
        insertProduct(product);
        product = new Product(1,"Desengrasante",270.0f);
        insertProduct(product);
        product = new Product(1,"Guantes (Paq. 2 cajas)",500.0F);
        insertProduct(product);
        product = new Product(2,"Caja Jitomate",220f);
        insertProduct(product);
        product = new Product(2,"Cebolla Blanca (Kg)",25f);
        insertProduct(product);
        product = new Product(2,"Cebolla Morada (Kg)",19f);
        insertProduct(product);
        product = new Product(2,"Limon (Caja)",550f);
        insertProduct(product);
        product = new Product(2,"Jalapeño (Caja)",29f);
        insertProduct(product);
        product = new Product(2,"Cilantro",5f);
        insertProduct(product);
        product = new Product(2,"Chile de arbol(Kg)",100f);
        insertProduct(product);
        product = new Product(2,"Harina (Paq. 10 pzas.)",200f);
        insertProduct(product);
        product = new Product(2,"Chile Habanero(Kg)",60f);
        insertProduct(product);
        product = new Product(2,"Queso",200f);
        insertProduct(product);


        isFrist();

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

    public long insertProduct(Product product){
        long producId;
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getWritableDatabase();
        ContentValues register= new ContentValues();
        register.put(Utilities.PRODUCT_NAME,product.getProductName());
        register.put(Utilities.PRODUCT_COST,product.getProductCost());
        register.put(Utilities.SUPPLIER_ID,product.getSupplierId());
        producId=db.insert(Utilities.TABLE_PRODUCT,Utilities.ID_PRODUCT,register);

        if (producId == -1){
            Toast.makeText(queryContext,"Registro Erroneo Producto", Toast.LENGTH_LONG);
        }
        else {
            Toast.makeText(queryContext, "Registro Exitoso", Toast.LENGTH_LONG);
            Log.d("SQL","Insert Exitoso");
        }
        db.close();
        return producId;

    }

    public long insertSupplier(Supplier supplier){

        long supplierID;
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getWritableDatabase();
        ContentValues register= new ContentValues();
        register.put(Utilities.SUPPLIER_NAME,supplier.getSupplierName().toString());
        register.put(Utilities.SUPPLIER_ICON,supplier.getSupplierIcon().toString());

        supplierID=db.insert(Utilities.TABLE_SUPPLIER,Utilities.ID_SUPPLIER,register);

        if (supplierID == -1){
            Toast.makeText(queryContext,"Registro Erroneo usuario", Toast.LENGTH_LONG);
        }
        else {
            Toast.makeText(queryContext, "Registro Exitoso", Toast.LENGTH_LONG);
            Log.d("SQL","Insert Exitoso");
        }
        db.close();
        return supplierID;


    }

    public long insertOrder(ArrayList <Product> products, int storeId, int supplierId, float total){

        long orderId;
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getWritableDatabase();
        ContentValues register= new ContentValues();
        register.put(Utilities.ORDER_TOTAL,total);
        register.put(Utilities.STORE_ID,storeId);
        register.put(Utilities.SUPPLIER_ID,supplierId) ;
        orderId=db.insert(Utilities.TABLE_ORDER,Utilities.ID_ORDER,register);

        if (orderId == -1){
            Toast.makeText(queryContext,"Registro Erroneo usuario", Toast.LENGTH_LONG);
        }
        else {
            Toast.makeText(queryContext, "Registro Exitoso", Toast.LENGTH_LONG);
            Log.d("SQL","Insert Exitoso");
            for (int i=0 ; i<products.size(); i ++){
                Product product = new Product();
                product = products.get(i);
                if(product.getProductQTY()!=0){
                    register.clear();
                    register.put(Utilities.ORDER_ID,orderId);
                    register.put(Utilities.PRODUCT_ID,product.getProductId());
                    register.put(Utilities.PRODUCT_QUANTITY,product.getProductQTY());
                    register.put(Utilities.SUPPLIER_ID,supplierId) ;
                    orderId=db.insert(Utilities.TABLE_ORDER_PRODUCT,Utilities.ORDER_ID,register);
                }
            }
        }
        db.close();
        return orderId;


    }



    public void isFrist() {

        SQLHelper connection = getConection();
        SQLiteDatabase db = connection.getWritableDatabase();
        ContentValues register = new ContentValues();
        register.put(Utilities.FEATURE_NAME,"Loaded");
        register.put(Utilities.FEATURE_STATUS, 1);
        db.insert(Utilities.TABLE_SYSTEM, Utilities.ID_SYSTEM, register);
        db.close();
    }

    public boolean loadDefault (){
        boolean load =false;
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getReadableDatabase();
        String [] parameters={"Loaded"};
        String [] fields={Utilities.FEATURE_NAME,Utilities.FEATURE_STATUS};
        String query = Utilities.FEATURE_NAME+" = ? ";


        Cursor cursor =db.query(Utilities.TABLE_SYSTEM,
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
                load= true;

            }else {
                cursor.close();
                db.close();
                Log.d("SQL","No existe el registro");
                load= false;
            }

        }catch (Exception ex){
            Log.d("SQL","ERROR"+ex);

        }

        return load;
    }

    public ArrayList<Supplier> loadSuppliers (){
        ArrayList <Supplier> suppliers = new ArrayList<>();
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getReadableDatabase();
        String query = "SELECT * FROM "+Utilities.TABLE_SUPPLIER;
        Cursor cursor= db.rawQuery(query,null);
        try{
            if(cursor.moveToFirst()){
                do {
                    Supplier supplier = new Supplier();
                    supplier.setSupplierId(cursor.getInt(0));
                    supplier.setSupplierName(cursor.getString(1));
                    supplier.setSupplierIcon(cursor.getString(2));
                    suppliers.add(supplier);
                }while (cursor.moveToNext());
            }else {
                Log.d("SQL","No existe el registro");
            }

        }catch (Exception ex){
            Log.d("SQL","ERROR"+ex);

        }
        cursor.close();
        db.close();

        return suppliers;

    }


    public ArrayList<Product> loadProductsBySupplier(int supplierId) {

        ArrayList <Product> products = new ArrayList<>();
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getReadableDatabase();
        String [] parameters={String.valueOf(supplierId)};
        String [] fields={Utilities.ID_PRODUCT,Utilities.PRODUCT_NAME,Utilities.PRODUCT_COST};
        String query = Utilities.SUPPLIER_ID+" = ? ";
        Cursor cursor =db.query(Utilities.TABLE_PRODUCT,
                fields,
                query,
                parameters,
                null,
                null,
                null);


        try{
            if(cursor.moveToFirst()){
                do {
                    Product product = new Product();
                    product.setProductId(cursor.getInt(0));
                    product.setProductName(cursor.getString(1));
                    product.setProductCost(cursor.getFloat(2));
                    product.setProductQTY(0);
                    products.add(product);
                }while (cursor.moveToNext());
            }else {
                Log.d("SQL","No existe el registro");
            }

        }catch (Exception ex){
            Log.d("SQL","ERROR"+ex);

        }
        cursor.close();
        db.close();

        return products;

    }

    public ArrayList<Order> loadOrderByStoreId(int storeId) {
        ArrayList<Order> orders = new ArrayList<>();
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getReadableDatabase();
        String [] parameters={String.valueOf(storeId)};
        String query = "SELECT s."+Utilities.SUPPLIER_NAME+",o."+Utilities.ID_ORDER+","+Utilities.ORDER_TOTAL+
                " FROM "+Utilities.TABLE_ORDER+" o"+
                " JOIN "+Utilities.TABLE_SUPPLIER+" s ON o."+Utilities.SUPPLIER_ID+" = s."+Utilities.ID_SUPPLIER+
                " WHERE o."+Utilities.STORE_ID+" = ?";
        Cursor cursor= db.rawQuery(query,parameters);


        try{
            if(cursor.moveToFirst()){
                do {
                    Order order= new Order();
                    order.setSupplierName(cursor.getString(0));
                    order.setOrderId(cursor.getInt(1));
                    order.setTotal(cursor.getFloat(2));
                    orders.add(order);
                }while (cursor.moveToNext());
            }else {
                Log.d("SQL","No existe el registro");
            }

        }catch (Exception ex){
            Log.d("SQL","ERROR"+ex);

        }
        cursor.close();
        db.close();

        return  orders;

    }

    public ArrayList<Product> loadProductsByOrder(int orderId) {
        ArrayList<Product> products = new ArrayList<>();
        SQLHelper connection=getConection();
        SQLiteDatabase db= connection.getReadableDatabase();
        String [] parameters={String.valueOf(orderId)};
        String query = "SELECT p."+Utilities.PRODUCT_NAME+",o."+Utilities.PRODUCT_QUANTITY+",p."+Utilities.PRODUCT_COST+
                " FROM "+Utilities.TABLE_ORDER_PRODUCT+" o"+
                " JOIN "+Utilities.TABLE_PRODUCT+" p ON o."+Utilities.PRODUCT_ID+" = p."+Utilities.ID_PRODUCT+
                " WHERE o."+Utilities.ORDER_ID+" = ?";
        Cursor cursor= db.rawQuery(query,parameters);


        try{
            if(cursor.moveToFirst()){
                do {
                    Product product= new Product();
                    product.setProductName(cursor.getString(0));
                    product.setProductQTY(cursor.getInt(1));
                    product.setProductCost(cursor.getFloat(2));
                    products.add(product);
                }while (cursor.moveToNext());
            }else {
                Log.d("SQL","No existe el registro");
            }

        }catch (Exception ex){
            Log.d("SQL","ERROR"+ex);

        }
        cursor.close();
        db.close();

        return  products;
    }
}
