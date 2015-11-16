package com.zicure.abacconnect.work.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zicure.abacconnect.R;
import com.zicure.abacconnect.work.profile.model.WorkProfile;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class MyWorkProfileRecyclerAdapter extends RecyclerView.Adapter<MyWorkProfileRecyclerAdapter.ViewHolder> {
    private WorkProfile workProfile;
    private Context mContext;

    public MyWorkProfileRecyclerAdapter(WorkProfile workProfile, Context context) {
        this.workProfile = workProfile;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_profile_recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.tvWorkProfileCompanyName.setText(workProfile.getWorkProfileData().get(position).getCompanyName());
        holder.tvWorkProfileStartJob.setText(workProfile.getWorkProfileData().get(position).getStartJob());
        holder.tvWorkProfileFinishJob.setText(workProfile.getWorkProfileData().get(position).getFinishJob());
        holder.tvWorkProfilePosition.setText(workProfile.getWorkProfileData().get(position).getPosition());
    }

    @Override
    public int getItemCount() {
        return workProfile.getWorkProfileData().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvWorkProfileCompanyName, tvWorkProfileStartJob, tvWorkProfileFinishJob, tvWorkProfilePosition;

        public ViewHolder(View itemView) {
            super(itemView);
            tvWorkProfileCompanyName = (TextView) itemView.findViewById(R.id.tvWorkProfileCompanyName);
            tvWorkProfileStartJob = (TextView) itemView.findViewById(R.id.tvWorkProfileStartJob);
            tvWorkProfileFinishJob = (TextView) itemView.findViewById(R.id.tvWorkProfileFinishJob);
            tvWorkProfilePosition = (TextView) itemView.findViewById(R.id.tvWorkProfilePosition);
        }
    }
}
