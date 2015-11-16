package com.zicure.abacconnect.alumni.search;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by DUMP129 on 11/11/2015.
 */

@DatabaseTable(tableName = "works")
public class Works {

    public Works() {
    }

    @DatabaseField(generatedId = true, columnName = "id")
    public Integer id;

    @DatabaseField(columnName = "work_from")
    public String work_from;

    @DatabaseField(columnName = "work_to")
    public String work_to;

    @DatabaseField(columnName = "created")
    public String created;

    @DatabaseField(columnName = "modified")
    public String modified;

    @DatabaseField(columnName = "company_name")
    public String company_name;

    @DatabaseField(columnName = "work_position")
    public String work_position;
}
