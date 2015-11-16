package com.zicure.abacconnect.magazines;

/**
 * Created by DUMP129 on 9/30/2015.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Database model using ORMLite
 */

@DatabaseTable(tableName = "magazine")
public class Magazine {

    public Magazine() {
    }

    @DatabaseField(generatedId = true, columnName = "id")
    public Integer id;

    @DatabaseField(columnName = "magazine_intro")
    public String magazine_intro;

    @DatabaseField(columnName = "magazine_thumbnail")
    public String magazine_thumbnail;

    @DatabaseField(columnName = "magazine_name")
    public String magazine_name;

    @DatabaseField(columnName = "magazine_path")
    public String magazine_path;

    @DatabaseField(columnName = "view_count")
    public String view_count;

    @DatabaseField(columnName = "magazine_date")
    public String magazine_date;

    @DatabaseField(columnName = "magazine_year")
    public String magazine_year;

    @DatabaseField(columnName = "is_active")
    public String is_active;
}