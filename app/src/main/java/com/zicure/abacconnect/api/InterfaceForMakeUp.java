package com.zicure.abacconnect.api;

import android.view.View;

/**
 * Created by DUMP129 on 10/27/2015.
 */
public interface InterfaceForMakeUp {
    public void onJobClassifiedClickListener(View v, String posName, String company, String address, String position, String date);
    public void onSpecialDealClickListener(View v, String specialDealStoreName, int imgSpecialDealPromotion,
                                           String specialDealPromotionDetail, String specialDealExp);
    public void onBusinessConnClickListener(View v, String businessCompanyName, String businessPositionName,
                                            String businessAddress, String businessDate);
    public void onNewsClickListener(View v, String newsTitle, String newsDetail, String newsView);
}
