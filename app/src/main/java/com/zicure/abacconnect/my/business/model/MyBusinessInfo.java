package com.zicure.abacconnect.my.business.model;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class MyBusinessInfo {

    private String myBusinessCompany, myBusinessAddress, myBusinessProduct, myBusinessDate;

    public MyBusinessInfo() {
    }

    public MyBusinessInfo(String myBusinessCompany, String myBusinessAddress, String myBusinessProduct, String myBusinessDate) {
        this.myBusinessCompany = myBusinessCompany;
        this.myBusinessAddress = myBusinessAddress;
        this.myBusinessProduct = myBusinessProduct;
        this.myBusinessDate = myBusinessDate;
    }

    public String getMyBusinessDate() {
        return myBusinessDate;
    }

    public void setMyBusinessDate(String myBusinessDate) {
        this.myBusinessDate = myBusinessDate;
    }

    public String getMyBusinessCompany() {
        return myBusinessCompany;
    }

    public void setMyBusinessCompany(String myBusinessCompany) {
        this.myBusinessCompany = myBusinessCompany;
    }

    public String getMyBusinessAddress() {
        return myBusinessAddress;
    }

    public void setMyBusinessAddress(String myBusinessAddress) {
        this.myBusinessAddress = myBusinessAddress;
    }

    public String getMyBusinessProduct() {
        return myBusinessProduct;
    }

    public void setMyBusinessProduct(String myBusinessProduct) {
        this.myBusinessProduct = myBusinessProduct;
    }
}