package com.zicure.abacconnect.my.deal.model;

/**
 * Created by DUMP129 on 9/29/2015.
 */
public class MyDealsInfo {
    private int imgResource;
    private String storeName, discount, usedOn;

    public MyDealsInfo() {
    }

    public MyDealsInfo(int imgResource, String storeName, String discount, String usedOn) {
        this.imgResource = imgResource;
        this.storeName = storeName;
        this.discount = discount;
        this.usedOn = usedOn;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getUsedOn() {
        return usedOn;
    }

    public void setUsedOn(String usedOn) {
        this.usedOn = usedOn;
    }
}