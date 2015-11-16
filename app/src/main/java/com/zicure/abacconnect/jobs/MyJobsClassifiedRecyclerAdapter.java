package com.zicure.abacconnect.jobs;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.alumni.search.Alumni;
import com.zicure.abacconnect.alumni.search.Users;
import com.zicure.abacconnect.alumni.search.Works;
import com.zicure.abacconnect.api.DataLayer;
import com.zicure.abacconnect.api.DataLayerListener;
import com.zicure.abacconnect.api.RequestInterface;
import com.zicure.abacconnect.business.connect.BusinessConnections;
import com.zicure.abacconnect.magazines.Magazine;
import com.zicure.abacconnect.news.Newses;
import com.zicure.abacconnect.special.deals.SpecialDeals;

import java.util.List;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class MyJobsClassifiedRecyclerAdapter extends RecyclerView.Adapter<MyJobsClassifiedRecyclerAdapter.ViewHolder> implements DataLayerListener, RequestInterface {
    private DataLayer dataLayer = null;
    private RecyclerView recyclerViewJobs = null;
    private RequestInterface requestInterface = null;
    private int position;
    private Context mContext;
    private List<Jobss> jobssList = null;
    private MyJobsClassifiedRecyclerAdapter myJobsClassifiedRecyclerAdapter = null;

    public void setMyJobsClassifiedListener(RequestInterface requestInterface) {
        this.requestInterface = requestInterface;
    }

    public MyJobsClassifiedRecyclerAdapter(Context context, List<Jobss> jobssList) {
        this.jobssList = jobssList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs_classified_recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.jobss = jobssList.get(position);

        holder.tvJobsPositionName.setText(jobssList.get(position).job_position);
        holder.tvJobsCompany.setText(jobssList.get(position).job_name);
        holder.tvJobsAddress.setText(jobssList.get(position).job_addr);
        holder.tvJobsPosition.setText(jobssList.get(position).job_num);
        holder.tvJobsDate.setText(jobssList.get(position).job_expiry_date);
    }

    @Override
    public int getItemCount() {
        return jobssList.size();
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
        if (requestInterface != null) {
            requestInterface.onJobsClickListener(v, listJobs, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvJobsPositionName, tvJobsCompany, tvJobsAddress, tvJobsPosition, tvJobsDate;
        public Jobss jobss;

        public ViewHolder(View itemView) {
            super(itemView);

            tvJobsPositionName = (TextView) itemView.findViewById(R.id.tvJobsName);
            tvJobsCompany = (TextView) itemView.findViewById(R.id.tvJobsCompany);
            tvJobsAddress = (TextView) itemView.findViewById(R.id.tvJobsAddress);
            tvJobsPosition = (TextView) itemView.findViewById(R.id.tvJobsPosition);
            tvJobsDate = (TextView) itemView.findViewById(R.id.tvJobsDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (requestInterface != null) {
                        position = getAdapterPosition();
                        requestInterface.onJobsClickListener(v, jobssList, position);
                    }
                }
            });
        }
    }

    public void loadSearch(String searchText) {
        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        dataLayer.setDataLayerListener(this);
        dataLayer.searchJobs(searchText);
    }

    public void loadJobs() {
        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        dataLayer.setDataLayerListener(this);
        dataLayer.getJobs();
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
    public void fetchAlumni(List<Alumni> usersList) {}

    @Override
    public void fetchJobs(List<Jobss> jobssList) {
        myJobsClassifiedRecyclerAdapter = new MyJobsClassifiedRecyclerAdapter(ApplicationContext.getInstance().getContext(), jobssList);
        myJobsClassifiedRecyclerAdapter.setMyJobsClassifiedListener(this);
        recyclerViewJobs.setLayoutManager(new LinearLayoutManager(ApplicationContext.getInstance().getContext()));
        recyclerViewJobs.setAdapter(myJobsClassifiedRecyclerAdapter);
    }

    public void sendRecyclerView(RecyclerView recyclerViewJobs) {
        this.recyclerViewJobs = recyclerViewJobs;
    }
}