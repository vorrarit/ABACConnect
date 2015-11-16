package com.zicure.abacconnect.news;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by DUMP129 on 11/5/2015.
 */

@DatabaseTable(tableName = "news")
public class Newses {

    public Newses() {
    }

    @DatabaseField(generatedId = true, columnName = "id")
    public Integer id;

    @DatabaseField(columnName = "news_topic")
    public String news_topic;

    @DatabaseField(columnName = "news_intro")
    public String news_intro;

    @DatabaseField(columnName = "news_body")
    public String news_body;

    @DatabaseField(columnName = "created")
    public String created;

    @DatabaseField(columnName = "modified")
    public String modified;

    @DatabaseField(columnName = "created_by")
    public String create_by;

    @DatabaseField(columnName = "modified_by")
    public String modified_by;

    @DatabaseField(columnName = "view_count")
    public String view_count;

    @DatabaseField(columnName = "is_active")
    public String is_active;

    @DatabaseField(columnName = "user_id")
    public Integer user_id;

    @DatabaseField(columnName = "notify_date")
    public String notify_date;

    @DatabaseField(columnName = "news_thumbnail")
    public String news_thumbnail;

    @DatabaseField(columnName = "news_path")
    public String news_path;

}
