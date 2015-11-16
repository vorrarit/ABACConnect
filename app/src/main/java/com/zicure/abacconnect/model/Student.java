package com.zicure.abacconnect.model;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("id")
    public Integer id;

    @SerializedName("student_id")
    public String student_id;

    @SerializedName("student_faculty")
    public String student_faculty;

    @SerializedName("student_id_expried")
    public String student_id_expried;

    @SerializedName("is_active")
    public String is_active;

    @SerializedName("user_id")
    public Integer user_id;
}
