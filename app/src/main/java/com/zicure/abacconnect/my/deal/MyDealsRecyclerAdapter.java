package com.zicure.abacconnect.my.deal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.DataLayer;
import com.zicure.abacconnect.my.deal.model.MyDeals;

/**
 * Created by DUMP129 on 9/29/2015.
 */
public class MyDealsRecyclerAdapter extends RecyclerView.Adapter<MyDealsRecyclerAdapter.ViewHolder> {
    private MyDeals myDeals;
    private Context mContext;
    private RecyclerView recyclerViewDeals;
    private MyDealsRecyclerAdapter myDealsRecyclerAdapter;

    public MyDealsRecyclerAdapter(MyDeals myDeals, Context context) {
        this.myDeals = myDeals;
        this.mContext = context;
    }

    /*public MyDealsRecyclerAdapter(Context context, Li context1) {

    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_deals_recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.tvMyDealStoreName.setText(myDeals.getMyDealsData().get(position).getStoreName());
        holder.tvMyDealDiscount.setText(myDeals.getMyDealsData().get(position).getDiscount());
        holder.tvMyDealUsedOn.setText(myDeals.getMyDealsData().get(position).getUsedOn());
        Bitmap bmp = BitmapFactory.decodeResource(ApplicationContext.getInstance().getContext().getResources(), myDeals.getMyDealsData().get(position).getImgResource());
        holder.imgViewMyDealPromotion.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return myDeals.getMyDealsData().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgViewMyDealPromotion;
        public TextView tvMyDealStoreName, tvMyDealDiscount, tvMyDealUsedOn;

        public ViewHolder(View itemView) {
            super(itemView);

            imgViewMyDealPromotion = (ImageView) itemView.findViewById(R.id.imgViewMyDealPromotion);
            tvMyDealStoreName = (TextView) itemView.findViewById(R.id.tvMyDealStoreName);
            tvMyDealDiscount = (TextView) itemView.findViewById(R.id.tvMyDealDiscount);
            tvMyDealUsedOn = (TextView) itemView.findViewById(R.id.tvMyDealUsedOn);
        }
    }

    /*public void loadDeals(int userId) {
        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        dataLayer.setDataLayerListener(this);
        dataLayer.getDeals(userId);
    }*/

    public void sendRecyclerView(RecyclerView recyclerViewDeals) {
        this.recyclerViewDeals = recyclerViewDeals;
    }
}