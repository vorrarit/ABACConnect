package com.zicure.abacconnect.graduation.photo.model;

/**
 * Created by DUMP129 on 9/24/2015.
 */
public class GraduatePhotoInfo {
    private int imageResource;
    private String gradPhotoName;

    public GraduatePhotoInfo() {
    }

    public GraduatePhotoInfo(int imageResource, String gradPhotoName) {
        this.imageResource = imageResource;
        this.gradPhotoName = gradPhotoName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getGradPhotoName() {
        return gradPhotoName;
    }

    public void setGradPhotoName(String gradPhotoName) {
        this.gradPhotoName = gradPhotoName;
    }
}
