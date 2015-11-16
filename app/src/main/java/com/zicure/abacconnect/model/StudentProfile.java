package com.zicure.abacconnect.model;

/**
 * Created by DUMP129 on 11/6/2015.
 */
import com.google.gson.annotations.SerializedName;

/**
 * Created by SoIaM on 11/5/2015.
 */
public class     StudentProfile {
    @SerializedName("id")
    public Integer id;

    @SerializedName("student_id")
    public String student_id;

    @SerializedName("first_name")
    public String first_name;

    @SerializedName("last_name")
    public String last_name;

    @SerializedName("birth_date")
    public String birth_date;

    @SerializedName("faculty")
    public String faculty;

    @SerializedName("major")
    public String major;

    @SerializedName("citizen_passport_no")
    public String citizen_passport_no;


}

