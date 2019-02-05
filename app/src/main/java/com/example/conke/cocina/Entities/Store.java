package com.example.conke.cocina.Entities;

public class Store {

    private Integer storeId;
    private String storeName;

    public Store(){

    }

    public Store(Integer storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

}
