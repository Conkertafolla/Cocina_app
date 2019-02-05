package com.example.conke.cocina.Entities;


public class usuario {

    private Integer userID;
    private Integer storeId;
    private String userName;
    private String userMail;
    private String userPassword;

    public usuario() {

    }

    public usuario(Integer userID, Integer storeId, String userName, String userMail) {
        this.userID = userID;
        this.storeId = storeId;
        this.userName = userName;
        this.userMail = userMail;
    }

    public usuario(Integer storeId, String userName, String userMail, String userPassword) {
        this.storeId = storeId;
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
