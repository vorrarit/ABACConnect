package com.zicure.abacconnect.transcript;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.transcript.model.Transcript;
import com.zicure.abacconnect.transcript.model.TranscriptInfo;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class TranscriptFragment extends Fragment {
    private RecyclerView transcriptRecyclerView;
    Transcript transcript = new Transcript();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transcript, container, false);

        transcriptRecyclerView = (RecyclerView) v.findViewById(R.id.transcript_recycler_view);

        transcript.addTranscript(new TranscriptInfo(R.mipmap.ic_launcher, "Unofficial Transcript", "Master Degree", "2/09/2015"));

        MyTranscriptRecyclerAdapter myTranscriptRecyclerAdapter = new MyTranscriptRecyclerAdapter(transcript, null);
        transcriptRecyclerView.setLayoutManager(new LinearLayoutManager(ApplicationContext.getInstance().getContext()));
        transcriptRecyclerView.setAdapter(myTranscriptRecyclerAdapter);

        return v;
    }
}
