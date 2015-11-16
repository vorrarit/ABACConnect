package com.zicure.abacconnect.model;

import com.google.gson.annotations.SerializedName;

public class Users {
	@SerializedName("id")
	public Integer id;
	
	@SerializedName("Student_id")
	public Student Student;
		
	@SerializedName("user_username")
	public String user_username;
	
	@SerializedName("user_firstname")
	public String user_firstname;
 	
	@SerializedName("user_lastname")
	public String user_lastname;

	@SerializedName("user_pic")
	public String user_pic;

	@SerializedName("citizen")
	public String citizen;
	
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

	@SerializedName("work_position")
	public String work_position;

	@SerializedName("company_name")
	public String company_name;

	/*@SerializedName("role_id")
	public Role role;*/
}
