package com.zicure.abacconnect.graduation.photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;

/**
 * Created by DUMP129 on 9/25/2015.
 */
public class GraduatePhotoViewFragment extends Fragment {
    private ImageView imgViewGraduatePhotoShowPhotoSelected;
    private int position;

    public GraduatePhotoViewFragment(){}

    public GraduatePhotoViewFragment(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graduate_photo_show_photo_selected, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        imgViewGraduatePhotoShowPhotoSelected = (ImageView) view.findViewById(R.id.imgViewGraduatePhotoShowPhotoSelected);

        Bitmap bmp = BitmapFactory.decodeResource(ApplicationContext.getInstance().getContext().getResources(), R.drawable.graduate_e_large);
        imgViewGraduatePhotoShowPhotoSelected.setImageBitmap(bmp);
    }
}