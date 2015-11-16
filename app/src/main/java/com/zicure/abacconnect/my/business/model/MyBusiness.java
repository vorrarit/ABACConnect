package com.zicure.abacconnect.my.business.model;

import java.util.ArrayList;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class MyBusiness {
    private ArrayList<MyBusinessInfo> businessData = new ArrayList<>();

    public MyBusiness() {
    }

    public MyBusiness(ArrayList<MyBusinessInfo> businessData) {
        this.businessData = businessData;
    }

    public ArrayList<MyBusinessInfo> getBusinessData() {
        return businessData;
    }

    public void setBusinessData(ArrayList<MyBusinessInfo> businessData) {
        this.businessData = businessData;
    }

    public void addMyBusiness(MyBusinessInfo myBusinessInfo) {
        this.businessData.add(myBusinessInfo);
    }
}
