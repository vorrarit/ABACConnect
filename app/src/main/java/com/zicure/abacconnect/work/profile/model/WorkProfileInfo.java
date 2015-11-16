package com.zicure.abacconnect.work.profile.model;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class WorkProfileInfo {

    private String companyName, startJob, finishJob, position;


    public WorkProfileInfo() {
    }


    public WorkProfileInfo(String companyName, String startJob, String finishJob, String position) {
        this.companyName = companyName;
        this.startJob = startJob;
        this.finishJob = finishJob;
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStartJob() {
        return startJob;
    }

    public void setStartJob(String startJob) {
        this.startJob = startJob;
    }

    public String getFinishJob() {
        return finishJob;
    }

    public void setFinishJob(String finishJob) {
        this.finishJob = finishJob;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}