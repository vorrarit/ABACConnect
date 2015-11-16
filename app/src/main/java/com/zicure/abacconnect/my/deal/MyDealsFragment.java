package com.zicure.abacconnect.my.deal;

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
import com.zicure.abacconnect.api.RequestInterface;
import com.zicure.abacconnect.business.connect.BusinessConnections;
import com.zicure.abacconnect.jobs.Jobss;
import com.zicure.abacconnect.magazines.Magazine;
import com.zicure.abacconnect.my.deal.model.MyDeals;
import com.zicure.abacconnect.my.deal.model.MyDealsInfo;
import com.zicure.abacconnect.news.Newses;
import com.zicure.abacconnect.special.deals.SpecialDeals;

import java.util.List;

/**
 * Created by DUMP129 on 9/29/2015.
 */
public class MyDealsFragment extends Fragment {
    private RecyclerView myDealsRecyclerView = null;
    private MyDealsRecyclerAdapter myDealsRecyclerAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_deals, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDealsRecyclerView = (RecyclerView) view.findViewById(R.id.my_deals_recycler_view);

        //myDealsRecyclerAdapter = new MyDealsRecyclerAdapter(ApplicationContext.getInstance().getContext(), null);
        myDealsRecyclerView.setLayoutManager(new LinearLayoutManager(ApplicationContext.getInstance().getContext()));
        myDealsRecyclerAdapter.sendRecyclerView(myDealsRecyclerView);
        myDealsRecyclerView.setAdapter(myDealsRecyclerAdapter);

    }
}
