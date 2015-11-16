package com.zicure.abacconnect.alumni.search;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.DataLayer;
import com.zicure.abacconnect.api.DataLayerListener;
import com.zicure.abacconnect.business.connect.BusinessConnections;
import com.zicure.abacconnect.jobs.Jobss;
import com.zicure.abacconnect.magazines.Magazine;
import com.zicure.abacconnect.news.Newses;
import com.zicure.abacconnect.special.deals.SpecialDeals;

import java.util.List;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class MyAlumniSearchRecyclerAdapter extends RecyclerView.Adapter<MyAlumniSearchRecyclerAdapter.ViewHolder> implements DataLayerListener {
    private Context mContext;
    private RecyclerView recyclerViewAlumni;
    private DataLayer dataLayer;
    private MyAlumniSearchRecyclerAdapter alumniSearchRecyclerAdapter;
    private List<Alumni> alumniList;

    public MyAlumniSearchRecyclerAdapter(Context context, List<Alumni> alumniList) {
        this.alumniList = alumniList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumni_search_recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.alumni = alumniList.get(position);

        holder.tvAlumniCompanyName.setText(alumniList.get(position).company_name);
        holder.tvAlumniWorkForm.setText(alumniList.get(position).work_from);
        holder.tvAlumniWorkTo.setText(alumniList.get(position).work_to);
        holder.tvAlumniWorkPosition.setText(alumniList.get(position).work_position);
        holder.tvAlumniFirstName.setText(alumniList.get(position).user_firstname);
        holder.tvAlumniLastName.setText(alumniList.get(position).user_lastname);
        holder.tvAlumniStdId.setText(alumniList.get(position).user_username);
        holder.tvAlumniContact.setText(alumniList.get(position).user_email);
    }

    @Override
    public int getItemCount() {
        return alumniList.size();
    }

    @Override
    public void addMagazine(List<Magazine> magazineList) {}

    @Override
    public void addYears(List<String> years) {}

    @Override
    public void addViewCounts(String viewCount) {}

    @Override
    public void fetchNews(List<Newses> newsesList) {}

    @Override
    public void fetchSpecialDeals(List<SpecialDeals> specialDealList) {}

    @Override
    public void fetchBusiness(List<BusinessConnections> businessConnectionsList) {}

    @Override
    public void fetchAlumni(List<Alumni> alumniList) {
        alumniSearchRecyclerAdapter = new MyAlumniSearchRecyclerAdapter(ApplicationContext.getInstance().getContext(), alumniList);
        recyclerViewAlumni.setLayoutManager(new LinearLayoutManager(ApplicationContext.getInstance().getContext()));
        recyclerViewAlumni.setAdapter(alumniSearchRecyclerAdapter);
    }

    @Override
    public void fetchJobs(List<Jobss> jobssList) {}


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAlumniCompanyName, tvAlumniWorkForm, tvAlumniWorkTo, tvAlumniWorkPosition, tvAlumniFirstName, tvAlumniStdId, tvAlumniContact, tvAlumniLastName;
        public Alumni alumni;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAlumniCompanyName = (TextView) itemView.findViewById(R.id.tvAlumniCompanyName);
            tvAlumniWorkForm = (TextView) itemView.findViewById(R.id.tvAlumniWorkForm);
            tvAlumniWorkTo = (TextView) itemView.findViewById(R.id.tvAlumniWorkTo);
            tvAlumniWorkPosition = (TextView) itemView.findViewById(R.id.tvAlumniWorkPosition);
            tvAlumniFirstName = (TextView) itemView.findViewById(R.id.tvAlumniFirstName);
            tvAlumniStdId = (TextView) itemView.findViewById(R.id.tvAlumniStdId);
            tvAlumniContact = (TextView) itemView.findViewById(R.id.tvAlumniContact);
            tvAlumniLastName = (TextView) itemView.findViewById(R.id.tvAlumniLastName);
        }
    }

    public void loadAlumni() {
        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        dataLayer.setDataLayerListener(this);
        dataLayer.getAlumni();
    }

    public void loadSearch(String searchText) {
        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        dataLayer.setDataLayerListener(this);
        dataLayer.searchAlumni(searchText);
    }

    public void sendRecyclerView(RecyclerView recyclerViewAlumni) {
        this.recyclerViewAlumni = recyclerViewAlumni;
    }
}