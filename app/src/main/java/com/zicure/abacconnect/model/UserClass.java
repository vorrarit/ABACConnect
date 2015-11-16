package com.zicure.abacconnect.model;

import com.google.gson.annotations.SerializedName;

public class UserClass {
	@SerializedName("id")
	public Integer id;
	
	@SerializedName("Student_id")
	public String Student_id;

	@SerializedName("user_username")
	public String user_username;
	
	@SerializedName("user_firstname")
	public String user_firstname;
 	
	@SerializedName("user_lastname")
	public String user_lastname;
	
	@SerializedName("citizen_id")
	public String citizen_id;
	
	@SerializedName("passport")
	public String passport;

	@SerializedName("password")
	public String password;
	
	@SerializedName("user_email")
	public String user_email;
	
	@SerializedName("birth_date")
	public String birth_date;
	
	@SerializedName("role_id")
	public Integer role_id;
}
