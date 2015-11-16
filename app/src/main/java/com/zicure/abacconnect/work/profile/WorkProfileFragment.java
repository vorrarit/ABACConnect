package com.zicure.abacconnect.work.profile;

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
import com.zicure.abacconnect.work.profile.model.WorkProfile;
import com.zicure.abacconnect.work.profile.model.WorkProfileInfo;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class WorkProfileFragment extends Fragment {
    RecyclerView workProfileRecyclerView = null;

    WorkProfile workProfile = new WorkProfile();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_work_profile, container, false);

        workProfile.addWorkProfile(new WorkProfileInfo("RONGCHEN. CO., LTD", "2012", "Present", "Marketing Manager"));
        workProfile.addWorkProfile(new WorkProfileInfo("IDEAGRAM. CO., LTD", "2011", "2012", "Marketing Executive"));

        MyWorkProfileRecyclerAdapter myWorkProfileRecyclerAdapter = new MyWorkProfileRecyclerAdapter(workProfile, null);
        workProfileRecyclerView = (RecyclerView) v.findViewById(R.id.work_profile_recycler_view);
        workProfileRecyclerView.setLayoutManager(new LinearLayoutManager(ApplicationContext.getInstance().getContext()));
        workProfileRecyclerView.setAdapter(myWorkProfileRecyclerAdapter);

        return v;
    }
}
