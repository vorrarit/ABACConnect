package com.zicure.abacconnect.graduation.photo.model;

import java.util.ArrayList;

/**
 * Created by DUMP129 on 9/24/2015.
 */
public class GraduatePhoto {

    private ArrayList<GraduatePhotoInfo> graduatePhotoData = new ArrayList<GraduatePhotoInfo>();

    public GraduatePhoto(){}

    public GraduatePhoto(ArrayList<GraduatePhotoInfo> graduatePhotoData) {
        this.graduatePhotoData = graduatePhotoData;
    }

    public ArrayList<GraduatePhotoInfo> getGraduatePhotoData() {
        return graduatePhotoData;
    }

    public void setGraduatePhotoData(ArrayList<GraduatePhotoInfo> graduatePhotoData) {
        this.graduatePhotoData = graduatePhotoData;
    }

    public void addGraduatePhoto(GraduatePhotoInfo graduatePhotoInfo){
        this.graduatePhotoData.add(graduatePhotoInfo);
    }
}