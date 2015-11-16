package com.zicure.abacconnect.alumni.search;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by DUMP129 on 11/9/2015.
 */
@DatabaseTable(tableName = "users")
public class Users {

    public Users() {
    }

    @DatabaseField(generatedId = true, columnName = "id")
    public Integer id;

    @DatabaseField(columnName = "user_username") // student id
    public String user_username;

    @DatabaseField(columnName = "user_firstname")
    public String user_firstname;

    @DatabaseField(columnName = "user_lastname")
    public String user_lastname;

    @DatabaseField(columnName = "user_pic")
    public String user_pic;

    @DatabaseField(columnName = "citizen_id")
    public String citizen_id;

    @DatabaseField(columnName = "passport_id")
    public String passport_id;

    @DatabaseField(columnName = "user_email")
    public String user_email;

    @DatabaseField(columnName = "birth_date")
    public String birth_date;
}
