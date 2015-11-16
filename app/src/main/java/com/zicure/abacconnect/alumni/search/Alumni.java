package com.zicure.abacconnect.alumni.search;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by DUMP129 on 11/11/2015.
 */
@DatabaseTable(tableName = "alumni")
public class Alumni {


    public Alumni() {
    }

    @DatabaseField(columnName = "id")
    public Integer id;

    @DatabaseField(columnName = "user_username") // student id
    public String user_username;

    @DatabaseField(columnName = "user_firstname")
    public String user_firstname;

    @DatabaseField(columnName = "user_lastname")
    public String user_lastname;

    @DatabaseField(columnName = "user_pic")
    public String user_pic;

    @DatabaseField(columnName = "user_email")
    public String user_email;

    @DatabaseField(columnName = "work_from")
    public String work_from;

    @DatabaseField(columnName = "work_to")
    public String work_to;

    @DatabaseField(columnName = "company_name")
    public String company_name;

    @DatabaseField(columnName = "work_position")
    public String work_position;

    @DatabaseField(columnName = "short_description")
    public String short_description;

    @DatabaseField(columnName = "contact_person")
    public String contact_person;

    @DatabaseField(columnName = "contact_phone")
    public String contact_phone;

    @DatabaseField(columnName = "contact_email")
    public String contact_email;

    @DatabaseField(columnName = "contact_position")
    public String contact_position;


}
