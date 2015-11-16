package com.zicure.abacconnect.jobs;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by DUMP129 on 11/11/2015.
 */
@DatabaseTable(tableName = "jobs")
public class Jobss {

    public Jobss() {
    }

    @DatabaseField(generatedId = true, columnName = "id")
    public Integer id;

    @DatabaseField(columnName = "job_position")
    public String job_position;

    @DatabaseField(columnName = "job_name")
    public String job_name;

    @DatabaseField(columnName = "job_addr")
    public String job_addr;

    @DatabaseField(columnName = "created")
    public String created;

    @DatabaseField(columnName = "modified")
    public String modified;

    @DatabaseField(columnName = "job_num")
    public String job_num;

    @DatabaseField(columnName = "job_tel")
    public String job_tel;

    @DatabaseField(columnName = "job_responsibility")
    public String job_responsibility;

    @DatabaseField(columnName = "job_qualification")
    public String job_qualification;

    @DatabaseField(columnName = "job_group_id")
    public Integer job_group_id;

    @DatabaseField(columnName = "student_id")
    public Integer student_id;

    @DatabaseField(columnName = "job_expiry_date")
    public String job_expiry_date;

    @DatabaseField(columnName = "is_active")
    public String is_active;
}
