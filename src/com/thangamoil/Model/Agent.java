package com.thangamoil.Model;

public class Agent {

	private int Row_Id;
	private String Username;	
	private String Password;
	private String Role;
	private String User_Id;
	
	public int getRow_Id() {
		return Row_Id;
	}
	
	public String getUsername() {
		return Username;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public String getRole() {
		return Role;
	}
	
	public String getUser_Id() {
		return User_Id;
	}
	
	public void setRow_Id(int row_Id) {
		Row_Id = row_Id;
	}
	
	public void setUsername(String username) {
		Username = username;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
	
	public void setRole(String role) {
		Role = role;
	}
	
	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}
}
