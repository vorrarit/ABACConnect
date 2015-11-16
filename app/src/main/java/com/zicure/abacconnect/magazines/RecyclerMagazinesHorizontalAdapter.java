package com.zicure.abacconnect.magazines;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
 * Created by DUMP129 on 10/2/2015.
 */
public class RecyclerMagazinesHorizontalAdapter extends RecyclerView.Adapter<RecyclerMagazinesHorizontalAdapter.ViewHolder> implements DataLayerListener, RequestInterface {
    private String yearStr = null;
    private Context context;
    private List<Magazine> magazineList = null;
    private DataLayer dataLayer = null;
    private RecyclerView horizontalRV = null;
    private RequestInterface requestInterface = null;

    public void setFirstDataListener(RequestInterface requestInterface) {
        this.requestInterface = requestInterface;
    }

    public void setOnImageClickListener(RequestInterface requestInterface) {
        this.requestInterface = requestInterface;
    }

    public RecyclerMagazinesHorizontalAdapter(Context context, String yearStr) {
        this.context = context;
        this.yearStr = yearStr;
    }

    public RecyclerMagazinesHorizontalAdapter(Context context1, List<Magazine> magazineList) {
        this.context = context1;
        this.magazineList = magazineList;
    }

    public RecyclerMagazinesHorizontalAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.magazine_info_recycler_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.magazine = magazineList.get(position);

        // Set Magazine Monthly
        holder.tvMagazineIntro.setText(magazineList.get(position).magazine_intro);

        // Set Cover Magazine Monthly
        Glide.with(ApplicationContext.getInstance().getContext())
                .load(magazineList.get(position).magazine_thumbnail)
                .centerCrop()
                .into(holder.imgViewCoverMagazine);

        holder.imgViewCoverMagazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestInterface != null) {
                    requestInterface.onClickInMagazineListener(v, position, magazineList);
                }
            }
        });

        holder.tvMagazineIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestInterface != null) {
                    requestInterface.onClickInMagazineListener(v, position, magazineList);
                }
            }
        });

        if (magazineList != null) {
            requestInterface.firstDataListener(position, magazineList);
        }
    }


    @Override
    public int getItemCount() {
        return magazineList.size();
    }

    @Override
    public void addMagazine(List<Magazine> magazineList) {
        RecyclerMagazinesHorizontalAdapter recyclerMagazinesHorizontalAdapter = new RecyclerMagazinesHorizontalAdapter(ApplicationContext.getInstance().getContext(), magazineList);
        recyclerMagazinesHorizontalAdapter.setOnImageClickListener(this);
        horizontalRV.setLayoutManager(new LinearLayoutManager(ApplicationContext.getInstance().getContext(), LinearLayoutManager.HORIZONTAL, false));
        horizontalRV.setAdapter(recyclerMagazinesHorizontalAdapter);
    }

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
    public void onJobsClickListener(View v, List<Jobss> listJobs, int position) {}

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgViewCoverMagazine = null;
        public TextView tvMagazineIntro = null;
        public Magazine magazine = null;

        public ViewHolder(View itemView) {
            super(itemView);

            tvMagazineIntro = (TextView) itemView.findViewById(R.id.tvMagazineIntro);
            imgViewCoverMagazine = (ImageView) itemView.findViewById(R.id.imgViewCoverMagazine);
        }
    }

    public void loadMagazine() {
        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        dataLayer.setDataLayerListener(this);
        dataLayer.loadMagazines(yearStr);
    }

    public void sendHorizontalRV(RecyclerView horizontalRV) {
        this.horizontalRV = horizontalRV;
    }
}