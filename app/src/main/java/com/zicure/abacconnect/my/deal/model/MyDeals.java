package com.zicure.abacconnect.my.deal.model;

import java.util.ArrayList;

/**
 * Created by DUMP129 on 9/29/2015.
 */
public class MyDeals {
    private ArrayList<MyDealsInfo> myDealData = new ArrayList<MyDealsInfo>();

    public MyDeals() {
    }

    public MyDeals(ArrayList<MyDealsInfo> myDealData) {
        this.myDealData = myDealData;
    }

    public ArrayList<MyDealsInfo> getMyDealsData() {
        return myDealData;
    }

    public void setMyDealsData(ArrayList<MyDealsInfo> myDealsData) {
        this.myDealData = myDealsData;
    }

    public void addMyDeals(MyDealsInfo myDealsInfo) {
        this.myDealData.add(myDealsInfo);
    }
}
