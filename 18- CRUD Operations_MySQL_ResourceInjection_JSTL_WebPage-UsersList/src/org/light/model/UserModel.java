package org.light.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.light.entity.User;


public class UserModel {

	public List<User> listUsers(DataSource dataSource)  {
		List<User> listUser = new ArrayList<User>();
		// 1-->Initialize connection objects!
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			connection = dataSource.getConnection();
			// 2- Create a SQL statemnt
			String query = "Select * from users";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				listUser.add(new User(resultSet.getInt("users_id"), resultSet.getString("username"),
						resultSet.getString("email")));
			}

		} catch (Exception e) {
		e.printStackTrace();
		
		}
		return listUser;

	}

	public void addUser(User newUser, DataSource datasource) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = datasource.getConnection();
			String username = newUser.getUsername();
			String email = newUser.getEmail();
			String query = "Insert into users (username,email) values (?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				connection.close();//this is no longer used in java 7 above!
				statement.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			
			}
		}

	}

	public void updateUser(User updateUser, DataSource dataSource) {
			Connection connection=null;
			PreparedStatement statement = null;
			
			try {
				connection=dataSource.getConnection();
				String query = "update users set username=? , email=? where users_id= ?";
				statement = connection.prepareStatement(query);
				statement.setString(1, updateUser.getUsername());
				statement.setString(2, updateUser.getEmail());
				statement.setInt(3, updateUser.getUsers_id());
			    statement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			
			}

		
		
	}

	public void deleteUser(int usersId,DataSource datasource) {
		Connection connection=null;
		PreparedStatement statement=null;
		
		try {
			connection = datasource.getConnection();
			String query="delete from users where users_id=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1,usersId);
			statement.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
