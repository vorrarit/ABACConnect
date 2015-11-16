package com.zicure.abacconnect.my.business;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zicure.abacconnect.R;
import com.zicure.abacconnect.my.business.model.MyBusiness;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class MyBusinessRecyclerAdapter extends RecyclerView.Adapter<MyBusinessRecyclerAdapter.ViewHolder>{
    private MyBusiness myBusiness;
    private Context mContext;

    public MyBusinessRecyclerAdapter(MyBusiness myBusiness, Context context) {
        this.myBusiness = myBusiness;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_business_recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.tvMyBusinessCompany.setText(myBusiness.getBusinessData().get(position).getMyBusinessCompany());
        holder.tvMyBusinessAddress.setText(myBusiness.getBusinessData().get(position).getMyBusinessAddress());
        holder.tvMyBusinessProduct.setText(myBusiness.getBusinessData().get(position).getMyBusinessProduct());
        holder.tvMyBusinessDate.setText(myBusiness.getBusinessData().get(position).getMyBusinessDate());
    }

    @Override
    public int getItemCount() {
        return myBusiness.getBusinessData().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvMyBusinessCompany, tvMyBusinessAddress, tvMyBusinessProduct, tvMyBusinessDate;

        public ViewHolder(View itemView) {
            super(itemView);

            tvMyBusinessCompany = (TextView) itemView.findViewById(R.id.tvMyBusinessCompany);
            tvMyBusinessAddress = (TextView) itemView.findViewById(R.id.tvMyBusinessAddress);
            tvMyBusinessProduct = (TextView) itemView.findViewById(R.id.tvMyBusinessProduct);
            tvMyBusinessDate = (TextView) itemView.findViewById(R.id.tvMyBusinessDate);
        }
    }
}