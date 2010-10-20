package se.kyh.wiki.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import se.kyh.wiki.Article;
import se.kyh.wiki.User;

public class UserDAO {
	
	
	public static final UserDAO INSTANCE = new UserDAO();
	
	private UserDAO() {
		
	}
	
	private List<User> getAllUsers() {
		
		
		return null;
	}
	
	public boolean register(String firstName, String lastName, String email, String password) {
		boolean result = false;
		
		DbConnection connection = new DbConnection();
				
		try {
			Statement query = connection.connect().createStatement();
			
			query.executeUpdate("INSERT INTO user (first_name, last_name, email, password)" +
				"VALUES('"+ firstName +"', '"+ lastName +"', '"+ email +"', '"+ password +"')");
			
			
			result = true;
			
			connection.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean logIn(String email, String password) {
		DbConnection connection = new DbConnection();
		boolean login = false;
		
		try {
			Statement query = connection.connect().createStatement();
			ResultSet result = query.executeQuery("SELECT email,password FROM user WHERE email = '"+email+"' AND password = '"+password+"'");
			while (result.next()) {
				if (result.getString("email") != null) {
					login = true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return login;
	}
	
	
}
