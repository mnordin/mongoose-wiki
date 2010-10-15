package se.kyh.wiki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	Connection connection;
	
	public static void main(String[] args) throws Exception {
		
	}
	
	public Connection connect() throws SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DbSettings.URL, DbSettings.USERNAME, DbSettings.PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		return connection;
		
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
