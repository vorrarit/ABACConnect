package com.zicure.abacconnect.news;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.alumni.search.Alumni;
import com.zicure.abacconnect.alumni.search.Users;
import com.zicure.abacconnect.alumni.search.Works;
import com.zicure.abacconnect.api.ApiConfig;
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
public class MyNewsRecyclerAdapter extends RecyclerView.Adapter<MyNewsRecyclerAdapter.ViewHolder> implements DataLayerListener, RequestInterface {
    private Context mContext;
    private List<Newses> newsesList = null;
    private DataLayer dataLayer = null;
    private RecyclerView recyclerViewNews = null;
    private RequestInterface requestInterface = null;
    private int position;
    private MyNewsRecyclerAdapter myNewsRecyclerAdapter;

    public void setMyNewsListener(RequestInterface requestInterface) {
        this.requestInterface = requestInterface;
    }

    public MyNewsRecyclerAdapter(Context mContext, List<Newses> newsesList) {
        this.newsesList = newsesList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.newses = newsesList.get(position);

        holder.tvNewsTitle.setText(newsesList.get(position).news_topic);
        holder.tvNewsDetail.setText(newsesList.get(position).news_intro);
        holder.tvNewsView.setText(newsesList.get(position).view_count);

        String imgUrl = ApiConfig.IMG_URL + newsesList.get(position).news_thumbnail;
        Glide.with(ApplicationContext.getInstance().getContext())
                .load(imgUrl)
                .into(holder.imgViewNews);
    }

    @Override
    public int getItemCount() {
        return newsesList.size();
    }

    @Override
    public void addMagazine(List<Magazine> magazineList) {}

    @Override
    public void addYears(List<String> years) {}

    @Override
    public void addViewCounts(String viewCount) {}

    @Override
    public void fetchNews(List<Newses> newsesList) {
        myNewsRecyclerAdapter = new MyNewsRecyclerAdapter(ApplicationContext.getInstance().getContext(), newsesList);
        myNewsRecyclerAdapter.setMyNewsListener(this);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(ApplicationContext.getInstance().getContext()));
        recyclerViewNews.setAdapter(myNewsRecyclerAdapter);
    }

    @Override
    public void fetchSpecialDeals(List<SpecialDeals> specialDealList) {}

    @Override
    public void fetchBusiness(List<BusinessConnections> businessConnectionsList) {}

    @Override
    public void fetchAlumni(List<Alumni> usersList) {}

    @Override
    public void fetchJobs(List<Jobss> jobssList) {}

    @Override
    public void onClickInMagazineListener(View v, int position, List<Magazine> listMagazines) {}

    @Override
    public void onNewsClickListener(View v, List<Newses> listNewses, int position) {
        if (requestInterface != null) {
            requestInterface.onNewsClickListener(v, listNewses, position);
        }
    }

    @Override
    public void onSpecialDealsClickListener(View v, List<SpecialDeals> listDeals, int position) {}

    @Override
    public void firstDataListener(int position, List<Magazine> magazineList) {}

    @Override
    public void onBusinessClickListener(View v, List<BusinessConnections> listBusiness, int position) {}

    @Override
    public void onJobsClickListener(View v, List<Jobss> listJobs, int position) {}

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgViewNews;
        public TextView tvNewsTitle, tvNewsDetail, tvNewsView;
        public Newses newses;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgViewNews = (ImageView) itemView.findViewById(R.id.imgViewNews);
            tvNewsTitle = (TextView) itemView.findViewById(R.id.tvNewsTitle);
            tvNewsDetail = (TextView) itemView.findViewById(R.id.tvNewsDetail);
            tvNewsView = (TextView) itemView.findViewById(R.id.tvNewsView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (requestInterface != null) {
                        position = getAdapterPosition();
                        requestInterface.onNewsClickListener(v, newsesList, position);
                    }
                }
            });
        }
    }

    public void loadNews() {
        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        dataLayer.setDataLayerListener(this);
        dataLayer.getNews();
    }

    public void loadSearch(String searchText) {
        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        dataLayer.setDataLayerListener(this);
        dataLayer.searchNews(searchText);
    }

    public void sendRecyclerView(RecyclerView recyclerViewNews) {
        this.recyclerViewNews = recyclerViewNews;
    }
}