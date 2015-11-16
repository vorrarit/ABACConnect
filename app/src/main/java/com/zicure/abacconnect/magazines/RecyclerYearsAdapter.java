package com.zicure.abacconnect.magazines;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zicure.abacconnect.alumni.search.Alumni;
import com.zicure.abacconnect.alumni.search.Users;
import com.zicure.abacconnect.alumni.search.Works;
import com.zicure.abacconnect.api.RequestInterface;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.DataLayerListener;
import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.api.DataLayer;
import com.zicure.abacconnect.business.connect.BusinessConnections;
import com.zicure.abacconnect.jobs.Jobss;
import com.zicure.abacconnect.news.Newses;
import com.zicure.abacconnect.special.deals.SpecialDeals;

import java.util.List;

/**
 * Created by DUMP129 on 10/1/2015.
 */
public class RecyclerYearsAdapter extends RecyclerView.Adapter<RecyclerYearsAdapter.ViewHolder> implements DataLayerListener, RequestInterface {
    private List<String> yearStr = null;
    private Context context = null;
    private DataLayer dataLayer = null;
    private RecyclerMagazinesHorizontalAdapter recyclerMagazinesHorizontalAdapter = null;
    private RecyclerView recyclerViewVerticalYears = null;
    private RequestInterface requestInterface = null;

    public RecyclerYearsAdapter(Context context, List<String> yearStr) {
        this.context = context;
        this.yearStr = yearStr;
    }

    public void setRecyclerYearsVerticalListener(RequestInterface requestInterface) {
        this.requestInterface = requestInterface;
    }

    public void setFirstDataVerticalListener(RequestInterface requestInterface) {
        this.requestInterface = requestInterface;
    }

    public List<String> getYearStr() {
        return yearStr;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.magazines_rv_horizontal, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayout);

        return viewHolder;
    }

    // Bind Data and Set Horizontal RecyclerView.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvYear.setText(getYearStr().get(position));

        recyclerMagazinesHorizontalAdapter = new RecyclerMagazinesHorizontalAdapter(ApplicationContext.getInstance().getContext(), getYearStr().get(position));
        recyclerMagazinesHorizontalAdapter.setOnImageClickListener(this);
        recyclerMagazinesHorizontalAdapter.setFirstDataListener(this);
        recyclerMagazinesHorizontalAdapter.sendHorizontalRV(holder.horizontalRV);
        recyclerMagazinesHorizontalAdapter.loadMagazine();
    }

    @Override
    public int getItemCount() {
        return yearStr.size();
    }

    @Override
    public void addMagazine(List<Magazine> magazineList) {}

    @Override
    public void addYears(List<String> years) {
        RecyclerYearsAdapter recyclerYearsAdapter = new RecyclerYearsAdapter(ApplicationContext.getInstance().getContext(), years);
        recyclerViewVerticalYears.setLayoutManager(new LinearLayoutManager(ApplicationContext.getInstance().getContext()));
        recyclerYearsAdapter.setRecyclerYearsVerticalListener(this);
        recyclerYearsAdapter.setFirstDataVerticalListener(this);
        recyclerViewVerticalYears.setAdapter(recyclerYearsAdapter);
    }

    @Override
    public void addViewCounts(String magazineOrmLiteList) {}

    @Override
    public void fetchNews(List<Newses> newsesList) {}

    @Override
    public void fetchSpecialDeals(List<SpecialDeals> specialDealList) {}

    @Override
    public void fetchBusiness(List<BusinessConnections> businessConnectionsList) {}

    @Override
    public void fetchAlumni(List<Alumni> usersList) {}

    @Override
    public void fetchJobs(List<Jobss> jobssList) {}

    @Override
    public void onClickInMagazineListener(View v, int position, List<Magazine> listMagazines) {
        if (requestInterface != null) {
            requestInterface.onClickInMagazineListener(v, position, listMagazines);
        }
    }

    @Override
    public void onNewsClickListener(View v, List<Newses> listNewses, int position) {}

    @Override
    public void onSpecialDealsClickListener(View v, List<SpecialDeals> listDeals, int position) {}

    @Override
    public void firstDataListener(int position, List<Magazine> magazineList) {
        if (requestInterface != null) {
            requestInterface.firstDataListener(position, magazineList);
        }
    }

    @Override
    public void onBusinessClickListener(View v, List<BusinessConnections> listBusiness, int position) {}

    @Override
    public void onJobsClickListener(View v, List<Jobss> listJobs, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView horizontalRV;
        public TextView tvYear;

        public ViewHolder(View itemView) {
            super(itemView);

            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            horizontalRV = (RecyclerView) itemView.findViewById(R.id.magazine_rv_horizontal);
        }
    }

    public void loadYear() {
        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        dataLayer.setDataLayerListener(this);
        dataLayer.loadYears();
    }

    public void sendRecycler(RecyclerView recyclerViewVerticalYears) {
        this.recyclerViewVerticalYears = recyclerViewVerticalYears;
    }
}