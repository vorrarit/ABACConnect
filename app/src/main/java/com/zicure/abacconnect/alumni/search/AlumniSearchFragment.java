package com.zicure.abacconnect.alumni.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.alumni.advanced.search.AlumniAdvancedSearchFragment;


/**
 * Created by DUMP129 on 9/28/2015.
 */
public class AlumniSearchFragment extends Fragment {
    private RecyclerView alumniSearchRecyclerView;
    private MyAlumniSearchRecyclerAdapter myAlumniSearchRecyclerAdapter;
    private SearchView searchAlumni;
    private TextView tvAdvSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alumni_search, container, false);
        alumniSearchRecyclerView = (RecyclerView) v.findViewById(R.id.alumni_search_recycler_view);
        searchAlumni = (SearchView) v.findViewById(R.id.searchAlumni);
        tvAdvSearch = (TextView) v.findViewById(R.id.tvAdvSearch);

        searchAlumni.setQueryHint("Search First Name");

        searchAlumni.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        myAlumniSearchRecyclerAdapter = new MyAlumniSearchRecyclerAdapter(ApplicationContext.getInstance().getContext(), null);
        myAlumniSearchRecyclerAdapter.sendRecyclerView(alumniSearchRecyclerView);
        myAlumniSearchRecyclerAdapter.loadAlumni();

        return v;
    }

    private void search(String searchText) {
        myAlumniSearchRecyclerAdapter = new MyAlumniSearchRecyclerAdapter(ApplicationContext.getInstance().getContext(), null);
        myAlumniSearchRecyclerAdapter.sendRecyclerView(alumniSearchRecyclerView);
        myAlumniSearchRecyclerAdapter.loadSearch(searchText);
    }
}
