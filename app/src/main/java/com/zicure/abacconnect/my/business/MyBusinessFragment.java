package com.zicure.abacconnect.my.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.my.business.model.MyBusiness;
import com.zicure.abacconnect.my.business.model.MyBusinessInfo;

/**
 * Created by DUMP129 on 9/29/2015.
 */
public class MyBusinessFragment extends Fragment {
    private RecyclerView businessConnectRecyclerView;
    MyBusiness myBusiness = new MyBusiness();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_business, container, false);

        businessConnectRecyclerView = (RecyclerView) v.findViewById(R.id.my_business_recycler_view);

        myBusiness.addMyBusiness(new MyBusinessInfo("Dental Vision Co., Ltd.", "42 Soi Hussadisevee, Sutthisarn Road, Huaykwang, Huaykwang, Bangkok 10320", "Dental Equipment & Supplies", "09/09/2015"));

        MyBusinessRecyclerAdapter myBusinessRecyclerAdapter = new MyBusinessRecyclerAdapter(myBusiness, null);
        businessConnectRecyclerView.setLayoutManager(new LinearLayoutManager(ApplicationContext.getInstance().getContext()));
        businessConnectRecyclerView.setAdapter(myBusinessRecyclerAdapter);

        return v;
    }
}
