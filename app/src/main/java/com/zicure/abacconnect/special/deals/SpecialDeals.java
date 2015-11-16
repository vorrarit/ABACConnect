package com.zicure.abacconnect.special.deals;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by DUMP129 on 11/5/2015.
 */

@DatabaseTable(tableName = "deals")
public class SpecialDeals {

    public SpecialDeals() {
    }

    @DatabaseField(generatedId = true, columnName = "id")
    public Integer id;

    @DatabaseField(columnName = "deal_pic")
    public String deal_pic;

    @DatabaseField(columnName = "deal_name")
    public String deal_name;

    @DatabaseField(columnName = "deal_discount")
    public String deal_discount;

    @DatabaseField(columnName = "deal_expiry_date")
    public String deal_expiry_date;

    @DatabaseField(columnName = "deal_condition")
    public String deal_condition;

    @DatabaseField(columnName = "deal_detail")
    public String deal_detail;

    @DatabaseField(columnName = "created")
    public String created;

    @DatabaseField(columnName = "modified")
    public String modified;

    @DatabaseField(columnName = "user_id")
    public Integer user_id;

    @DatabaseField(columnName = "deal_expiry_time")
    public String deal_expiry_time;

    @DatabaseField(columnName = "deal_thumbnail")
    public String deal_thumbnail;

    @DatabaseField(columnName = "deal_path")
    public String deal_path;

    @DatabaseField(columnName = "is_active")
    public String is_active;
}
