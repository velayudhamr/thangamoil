package com.thangamoil;

import android.app.Application;

public class GlobalClass extends Application {

	
	private String Role;
	private String Username;
	private String User_Id;
	
	public String getRole() {
		return Role;
	}
	public String getUsername() {
		return Username;
	}
	public String getUser_Id() {
		return User_Id;
	}
	public void setRole(String role) {
		Role = role;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}
}
