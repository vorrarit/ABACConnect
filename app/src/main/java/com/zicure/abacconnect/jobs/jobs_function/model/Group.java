package com.zicure.abacconnect.jobs.jobs_function.model;

import java.util.ArrayList;

/**
 * Created by DUMP129 on 11/2/2015.
 */
public class Group {
    private String grpName;
    private ArrayList<Child> items;

    public Group(String grpName, ArrayList<Child> items) {
        this.grpName = grpName;
        this.items = items;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public ArrayList<Child> getItems() {
        return items;
    }

    public void setItems(ArrayList<Child> items) {
        this.items = items;
    }
}
