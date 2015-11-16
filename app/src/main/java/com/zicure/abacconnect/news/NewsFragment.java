package com.zicure.abacconnect.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.DataLayer;
import com.zicure.abacconnect.api.DataLayerListener;
import com.zicure.abacconnect.api.RequestInterface;
import com.zicure.abacconnect.business.connect.BusinessConnections;
import com.zicure.abacconnect.jobs.Jobss;
import com.zicure.abacconnect.magazines.Magazine;
import com.zicure.abacconnect.special.deals.SpecialDeals;

import java.util.List;

/**
 * Created by DUMP129 on 10/27/2015.
 */
public class NewsFragment extends Fragment implements RequestInterface {
    private RecyclerView newsRecyclerView;
    private MyNewsRecyclerAdapter myNewsRecyclerAdapter = null;
    private SearchView searchNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);
        searchNews = (SearchView) view.findViewById(R.id.searchNews);
        searchNews.setQueryHint("Search News Topic");

        searchNews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        myNewsRecyclerAdapter = new MyNewsRecyclerAdapter(ApplicationContext.getInstance().getContext(), null);
        myNewsRecyclerAdapter.setMyNewsListener(this);
        myNewsRecyclerAdapter.sendRecyclerView(newsRecyclerView);
        myNewsRecyclerAdapter.loadNews();
    }

    private void search(String searchText) {
        myNewsRecyclerAdapter = new MyNewsRecyclerAdapter(ApplicationContext.getInstance().getContext(), null);
        myNewsRecyclerAdapter.setMyNewsListener(this);
        myNewsRecyclerAdapter.sendRecyclerView(newsRecyclerView);
        myNewsRecyclerAdapter.loadSearch(searchText);
    }

    @Override
    public void onClickInMagazineListener(View v, int position, List<Magazine> listMagazines) {
    }

    @Override
    public void onNewsClickListener(View v, List<Newses> listNewses, int position) {
        Fragment fragment = new NewsViewFragment(v, listNewses, position);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
        transaction.replace(R.id.main, fragment).commit();
    }

    @Override
    public void onSpecialDealsClickListener(View v, List<SpecialDeals> listDeals, int position) {
    }

    @Override
    public void firstDataListener(int position, List<Magazine> magazineList) {
    }

    @Override
    public void onBusinessClickListener(View v, List<BusinessConnections> listBusiness, int position) {}

    @Override
    public void onJobsClickListener(View v, List<Jobss> listJobs, int position) {}
}