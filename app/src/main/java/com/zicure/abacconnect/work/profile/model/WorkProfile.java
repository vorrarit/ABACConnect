package com.zicure.abacconnect.work.profile.model;

import java.util.ArrayList;

/**
 * Created by DUMP129 on 9/28/2015.
 */
public class WorkProfile {

    ArrayList<WorkProfileInfo> workProfileData = new ArrayList<WorkProfileInfo>();

    public WorkProfile(ArrayList<WorkProfileInfo> workProfileData) {
        this.workProfileData = workProfileData;
    }

    public WorkProfile() {
    }

    public ArrayList<WorkProfileInfo> getWorkProfileData() {
        return workProfileData;
    }

    public void setWorkProfileData(ArrayList<WorkProfileInfo> workProfileData) {
        this.workProfileData = workProfileData;
    }

    public void addWorkProfile(WorkProfileInfo workProfileInfo) {
        this.workProfileData.add(workProfileInfo);
    }
}
