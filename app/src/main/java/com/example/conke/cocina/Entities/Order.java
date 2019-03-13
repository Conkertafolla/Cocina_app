
package com.example.conke.cocina.Entities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer storeId;
    private String supplierName;
    private ArrayList<Product>products;
    private HashMap<Integer,Integer> costPer; // Key is the product id and value is quantity of the product
    private Float total;//Calculate the sum of cost

    public Order() {
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public HashMap<Integer, Integer> getCostPer() {
        return costPer;
    }

    public void setCostPer(HashMap<Integer, Integer> costPer) {
        this.costPer = costPer;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}

