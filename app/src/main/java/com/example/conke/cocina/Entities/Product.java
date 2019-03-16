package com.example.conke.cocina.Entities;



public class Product {
    private Integer productId;
    private Integer supplierId;
    private String productName;
    private String productDescription;
    private Float productCost;
    private int ProductQTY;

    public Product() {

    }


    public Product(Integer supplierId, String productName, Float productCost) {
        this.supplierId = supplierId;
        this.productName = productName;
        this.productCost = productCost;
    }

    public Product(Integer productId, Integer supplierId, String productName, Float productCost) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.productName = productName;
        this.productCost = productCost;
    }

    public Product(Integer productId, Integer supplierId, String productName, String productDescription, Float productCost) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCost = productCost;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getProductCost() {
        return productCost;
    }

    public void setProductCost(Float productCost) {
        this.productCost = productCost;
    }

    public int getProductQTY() {
        return ProductQTY;
    }

    public void setProductQTY(int productQTY) {
        ProductQTY = productQTY;
    }
}
