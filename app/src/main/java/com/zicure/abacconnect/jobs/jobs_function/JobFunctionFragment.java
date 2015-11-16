package com.zicure.abacconnect.jobs.jobs_function;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.zicure.abacconnect.R;
import com.zicure.abacconnect.jobs.JobsClassifiedFragment;
import com.zicure.abacconnect.jobs.jobs_function.model.Group;

import java.util.ArrayList;

/**
 * Created by DUMP129 on 10/30/2015.
 */
public class JobFunctionFragment extends Fragment implements View.OnClickListener {
    private ExpandableListView expandableListView;
    private Button btnJobFunctionCancel, btnJobFunctionOK;
    private JobFunctionExpanListAdapter expadListAdapter;
    private ArrayList<Group> expListItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_job_function, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        btnJobFunctionCancel = (Button) view.findViewById(R.id.btnJobFunctionCancel);
        btnJobFunctionOK = (Button) view.findViewById(R.id.btnJobFunctionOK);

        btnJobFunctionCancel.setOnClickListener(this);
        btnJobFunctionOK.setOnClickListener(this);

        //expListItems = SetS
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if (v == btnJobFunctionCancel) {
            fragment = new JobsClassifiedFragment();
        } else {
            fragment = new JobsClassifiedFragment();
        }

        if (fragment != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
            transaction.replace(R.id.main, fragment).commit();
        }
    }
}
