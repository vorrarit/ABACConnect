package com.zicure.abacconnect.graduation.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.zicure.abacconnect.R;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.graduation.photo.model.GraduatePhoto;
import com.zicure.abacconnect.graduation.photo.model.GraduatePhotoInfo;

/**
 * Created by DUMP129 on 9/24/2015.
 */
public class GraduatePhotoGalleryFragment extends Fragment implements MyGridViewAdapter.MyGridViewAdapterOnClickListener {
    private GridView gridViewGraduatePhoto;
    private MyGridViewAdapter myGridViewAdapter;

    // GraduatePhotos graduatePhotos = new GraduatePhotos();
    GraduatePhoto graduatePhoto = new GraduatePhoto();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_graduate_photo, container, false);

        graduatePhoto.addGraduatePhoto(new GraduatePhotoInfo(R.drawable.graduate_a_small, "Graduate Photo A"));
        graduatePhoto.addGraduatePhoto(new GraduatePhotoInfo(R.drawable.graduate_b_amall, "Graduate Photo B"));
        graduatePhoto.addGraduatePhoto(new GraduatePhotoInfo(R.drawable.graduate_c_small, "Graduate Photo C"));
        graduatePhoto.addGraduatePhoto(new GraduatePhotoInfo(R.drawable.graduate_d_small, "Graduate Photo D"));
        graduatePhoto.addGraduatePhoto(new GraduatePhotoInfo(R.drawable.graduate_e_small, "Graduate Photo E"));
        graduatePhoto.addGraduatePhoto(new GraduatePhotoInfo(R.drawable.graduate_f_small, "Graduate Photo F"));

        //graduatePhotos.add(graduatePhoto);

        gridViewGraduatePhoto = (GridView) v.findViewById(R.id.grid_view_graduate_photo);
        myGridViewAdapter = new MyGridViewAdapter(graduatePhoto, ApplicationContext.getInstance().getContext());
        myGridViewAdapter.setMyGridViewListener(this);
        gridViewGraduatePhoto.setAdapter(myGridViewAdapter);

        return v;
    }

    @Override
    public void rowViewOnClick(View v, int position) {
        Fragment fragment = new GraduatePhotoViewFragment(position);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
        transaction.replace(R.id.main, fragment).commit();
    }
}