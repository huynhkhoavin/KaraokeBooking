package com.example.khoavin.karaokebooking.Tools;

/**
 * Created by OatOal on 6/16/2016.
 */
public class RegisterInfo {
    /*private String bussinessType;      //loai chi nhanh: nha hang, khach san, quan karaoke,...
    private String branchName;*/

    private String userName;
    private String displayName;
    private String password;
    private int status;
    private int clientType;         //quyen hang tai khoan: user hoac la admin
    private int location;
    private int regularPrice;
    private int amount;

    public RegisterInfo(String userName, String password, int clientType) {
        this.userName = userName;
        this.password = password;
        this.clientType = clientType;

        displayName = "Default";
        status = 0;
        location = 0;
        regularPrice = 0;
        amount = 0;
    }

/*
    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
*/
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(int regularPrice) {
        this.regularPrice = regularPrice;
    }

    public int getAmmount() {
        return amount;
    }

    public void setAmmount(int ammount) {
        this.amount = ammount;
    }



}
