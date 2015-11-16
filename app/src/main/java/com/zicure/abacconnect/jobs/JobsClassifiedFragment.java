package com.zicure.abacconnect.jobs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.RequestInterface;
import com.zicure.abacconnect.business.connect.BusinessConnections;
import com.zicure.abacconnect.magazines.Magazine;
import com.zicure.abacconnect.news.Newses;
import com.zicure.abacconnect.special.deals.SpecialDeals;

import java.util.List;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class JobsClassifiedFragment extends Fragment implements RequestInterface {
    private RecyclerView jobsClassifiedRecyclerView;
    private SearchView searchJob;
    private TextView tvJobsFunction;
    private MyJobsClassifiedRecyclerAdapter myJobsClassifiedRecyclerAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs_classified, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jobsClassifiedRecyclerView = (RecyclerView) view.findViewById(R.id.jobs_classified_recycler_view);
        searchJob = (SearchView) view.findViewById(R.id.searchJob);
        searchJob.setQueryHint("Search Job");
        searchJob.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        tvJobsFunction = (TextView) view.findViewById(R.id.tvJobsFunction);

        myJobsClassifiedRecyclerAdapter = new MyJobsClassifiedRecyclerAdapter(ApplicationContext.getInstance().getContext(), null);
        myJobsClassifiedRecyclerAdapter.setMyJobsClassifiedListener(this);
        myJobsClassifiedRecyclerAdapter.sendRecyclerView(jobsClassifiedRecyclerView);
        myJobsClassifiedRecyclerAdapter.loadJobs();

        // tvJobsFunction.setOnClickListener(this);

    }

    private void search(String searchText) {
        myJobsClassifiedRecyclerAdapter = new MyJobsClassifiedRecyclerAdapter(ApplicationContext.getInstance().getContext(), null);
        myJobsClassifiedRecyclerAdapter.setMyJobsClassifiedListener(this);
        myJobsClassifiedRecyclerAdapter.sendRecyclerView(jobsClassifiedRecyclerView);
        myJobsClassifiedRecyclerAdapter.loadSearch(searchText);
    }

    @Override
    public void onClickInMagazineListener(View v, int position, List<Magazine> listMagazines) {}

    @Override
    public void onNewsClickListener(View v, List<Newses> listNewses, int position) {}

    @Override
    public void onSpecialDealsClickListener(View v, List<SpecialDeals> listDeals, int position) {}

    @Override
    public void firstDataListener(int position, List<Magazine> magazineList) {}

    @Override
    public void onBusinessClickListener(View v, List<BusinessConnections> listBusiness, int position) {}

    @Override
    public void onJobsClickListener(View v, List<Jobss> listJobs, int position) {
        Fragment fragment = new JobsViewFragment(v, listJobs, position);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
        transaction.replace(R.id.main, fragment).commit();
    }
}
