package com.zicure.abacconnect.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zicure.abacconnect.R;
import com.zicure.abacconnect.alumni.search.AlumniSearchFragment;
import com.zicure.abacconnect.business.connect.BusinessConnectFragment;
import com.zicure.abacconnect.jobs.JobsClassifiedFragment;
import com.zicure.abacconnect.magazines.MagazineFragment;
import com.zicure.abacconnect.news.NewsFragment;
import com.zicure.abacconnect.special.deals.SpecialDealsFragment;

public class MainPage extends Fragment {
    Button btnMagazine, btnNews, btnJobs, btnSpecialDeals, btnBusiness, btnAlumniSearch;

    private CoordinatorLayout rootLayout;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootLayout = (CoordinatorLayout) view.findViewById(R.id.rootLayout);
        btnBusiness = (Button) view.findViewById(R.id.btnBusiness);
        btnBusiness.setOnClickListener(new onClick());
        btnJobs = (Button) view.findViewById(R.id.btnJobs);
        btnJobs.setOnClickListener(new onClick());
        btnMagazine = (Button) view.findViewById(R.id.btnMagazine);
        btnMagazine.setOnClickListener(new onClick());
        btnNews = (Button) view.findViewById(R.id.btnNews);
        btnNews.setOnClickListener(new onClick());
        btnSpecialDeals = (Button) view.findViewById(R.id.btnSpecialDeals);
        btnSpecialDeals.setOnClickListener(new onClick());
        btnAlumniSearch = (Button) view.findViewById(R.id.btnAlumniSearch);
        btnAlumniSearch.setOnClickListener(new onClick());
    }

    public class onClick implements View.OnClickListener {
        Fragment fragment = null;
        private Intent intent = null;

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnNews:
                    fragment = new NewsFragment();
                    break;

                case R.id.btnMagazine:
                    fragment = new MagazineFragment();
                    break;

                case R.id.btnJobs:
                    fragment = new JobsClassifiedFragment();
                    break;

                case R.id.btnSpecialDeals:
                    fragment = new SpecialDealsFragment();
                    break;

                case R.id.btnBusiness:
                    fragment = new BusinessConnectFragment();
                    break;

                case R.id.btnAlumniSearch:
                    fragment = new AlumniSearchFragment();
                    break;

                default:
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }

            if (fragment != null) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            } else {}
        }
    }
}