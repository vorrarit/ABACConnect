package com.zicure.abacconnect.business.connect;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zicure.abacconnect.special.deals.ClaimDealTask;

/**
 * Created by DUMP129 on 11/9/2015.
 */
@DatabaseTable(tableName = "businessconnect")
public class BusinessConnections {

    public BusinessConnections() {
    }

    @DatabaseField(generatedId = true, columnName = "id")
    public Integer id;

    @DatabaseField(columnName = "company_name")
    public String company_name;

    @DatabaseField(columnName = "short_description")
    public String short_description;

    @DatabaseField(columnName = "street_address")
    public String street_address;

    @DatabaseField(columnName = "province_id")
    public Integer province_id;

    @DatabaseField(columnName = "province_name")
    public String province_name;

    @DatabaseField(columnName = "province_name_eng")
    public String province_name_eng;

    @DatabaseField(columnName = "geo_id")
    public String geo_id;

    @DatabaseField(columnName = "district_id")
    public Integer district_id;

    @DatabaseField(columnName = "district_name")
    public String district_name;

    @DatabaseField(columnName = "district_name_eng")
    public String district_name_eng;

    @DatabaseField(columnName = "locality_id")
    public Integer locality_id;

    @DatabaseField(columnName = "locality_name")
    public String locality_name;

    @DatabaseField(columnName = "locality_name_eng")
    public String locality_name_eng;

    @DatabaseField(columnName = "zipcode")
    public String zipcode;

    @DatabaseField(columnName = "long_description")
    public String long_description;

    @DatabaseField(columnName = "contact_person")
    public String contact_person;

    @DatabaseField(columnName = "contact_phone")
    public String contact_phone;

    @DatabaseField(columnName = "contact_email")
    public String contact_email;

    @DatabaseField(columnName = "contact_position")
    public String contact_position;

    @DatabaseField(columnName = "created")
    public String created;

    @DatabaseField(columnName = "modified")
    public String modified;

    @DatabaseField(columnName = "notify_date")
    public String notify_date;

    @DatabaseField(columnName = "business_thumbnail")
    public String business_thumbnail;

    @DatabaseField(columnName = "contact_thumbnail")
    public String contact_thumbnail;
}
