package se.kyh.wiki;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	public User() {
		
	}
	
	public User(int id) {
		
		
		//super(id, firstName, lastName, email, password);
	}
	
	public User(String firstName, String lastName, String email, String password) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
	}
	
	public User(int id, String firstName, String lastName, String email, String password) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
	}
	
	public boolean register() {
		boolean result = false;
		
		DbConnection connection = new DbConnection();
				
		try {
			Statement query = connection.connect().createStatement();
			
			int registeredId = query.executeUpdate("INSERT INTO user (first_name, last_name, email, password)" +
				"VALUES('"+ this.getFirstName() +"', '"+ this.getLastName() +"', '"+ this.getEmail() +"', '"+ this.getPassword() +"')"
			);
			
			this.setId(registeredId);
			
			result = true;
			
			connection.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean isLoggedIn() {
		return true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
