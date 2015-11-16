package com.zicure.abacconnect.jobs.jobs_function.model;

import android.widget.CheckBox;

/**
 * Created by DUMP129 on 11/2/2015.
 */
public class Child {
    private String name;
    private CheckBox checkBox;

    public Child(String name, CheckBox checkBox) {
        this.name = name;
        this.checkBox = checkBox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
