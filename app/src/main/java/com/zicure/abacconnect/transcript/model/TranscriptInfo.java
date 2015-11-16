package com.zicure.abacconnect.transcript.model;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class TranscriptInfo {
    private int imgResource;
    private String transcriptName, degree, releaseDate;

    public TranscriptInfo() {
    }

    public TranscriptInfo(int imgResource, String transcriptName, String degree, String releaseDate) {
        this.imgResource = imgResource;
        this.transcriptName = transcriptName;
        this.degree = degree;
        this.releaseDate = releaseDate;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTranscriptName() {
        return transcriptName;
    }

    public void setTranscriptName(String transcriptName) {
        this.transcriptName = transcriptName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
