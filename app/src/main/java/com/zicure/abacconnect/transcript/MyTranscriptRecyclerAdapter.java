package com.zicure.abacconnect.transcript;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zicure.abacconnect.R;
import com.zicure.abacconnect.transcript.model.Transcript;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class MyTranscriptRecyclerAdapter extends RecyclerView.Adapter<MyTranscriptRecyclerAdapter.ViewHolder> {
    private Transcript transcript;
    private Context mContext;

    public MyTranscriptRecyclerAdapter(Transcript transcript, Context context) {
        this.transcript = transcript;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transcript_recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.tvTranscriptName.setText(transcript.getTranscriptData().get(position).getTranscriptName());
        holder.tvTranscriptDegree.setText(transcript.getTranscriptData().get(position).getDegree());
        holder.tvTranscriptReleaseDate.setText(transcript.getTranscriptData().get(position).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return transcript.getTranscriptData().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgViewTranscriptDownload;
        public TextView tvTranscriptName, tvTranscriptDegree, tvTranscriptReleaseDate;

        public ViewHolder(View itemView) {
            super(itemView);

            imgViewTranscriptDownload = (ImageView) itemView.findViewById(R.id.imgViewTranscriptDownload);
            tvTranscriptName = (TextView) itemView.findViewById(R.id.tvTranscriptName);
            tvTranscriptDegree = (TextView) itemView.findViewById(R.id.tvTranscriptDegree);
            tvTranscriptReleaseDate = (TextView) itemView.findViewById(R.id.tvTranscriptReleaseDate);
        }
    }
}
