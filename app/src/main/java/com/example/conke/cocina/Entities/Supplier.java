package com.example.conke.cocina.Entities;


public class Supplier {
    private Integer supplierId;
    private String supplierName;
    private String supplierIcon;

    public Supplier(Integer supplierId, String supplierName, String supplierIcon) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierIcon = supplierIcon;
    }

    public Supplier( String supplierName, String supplierIcon) {

        this.supplierName = supplierName;
        this.supplierIcon = supplierIcon;
    }

    public Supplier(Integer supplierId, String supplierName) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public Supplier() {

    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierIcon() {
        return supplierIcon;
    }

    public void setSupplierIcon(String supplierIcon) {
        this.supplierIcon = supplierIcon;
    }
}
