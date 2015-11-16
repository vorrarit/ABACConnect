package com.zicure.abacconnect.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.ApiConfig;

import java.util.List;

/**
 * Created by DUMP129 on 10/27/2015.
 */
public class NewsViewFragment extends Fragment {
    private ImageView imgViewNewsView;
    private String imgUrl;
    private View v;
    private List<Newses> newsesList;
    private int position;

    public NewsViewFragment(View v, List<Newses> listNewses, int position) {
        this.v = v;
        this.newsesList = listNewses;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgViewNewsView = (ImageView) view.findViewById(R.id.imgViewNewsView);

        imgUrl = ApiConfig.IMG_URL + newsesList.get(position).news_path;

        Glide.with(getActivity())
                .load(imgUrl)
                .into(imgViewNewsView);
    }
}
