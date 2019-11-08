package org.light.entity;
//this is an entity class-----> used to represent the dataBase table !
public class User {
	private Integer users_id;
	private String username;
	private String email; 
	//Whenever we deal with Entity make sure that the name of the column and the name of the name of the variables are exact the
	//same
	
	public Integer getUsers_id() {
		return users_id;
	}
	public User(Integer users_id, String username, String email) {//needed to get info from database
		super();
		this.users_id = users_id;
		this.username = username;
		this.email = email;
	}
	
	
	public User(String username, String email) {//because users_id is primary key with Autoincremented column  checked on
		//mysql workbench
		super();
		this.username = username;
		this.email = email;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() { 
		return "User [users_id=" + users_id + ", username=" + username + ", email=" + email + "]";
	}
	
	
	
}
