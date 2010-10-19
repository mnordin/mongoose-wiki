package se.kyh.wiki.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	Connection connection;

	
	public Connection connect() throws SQLException {
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection  = DriverManager.getConnection(DbSettings.URL, DbSettings.USERNAME, DbSettings.PASSWORD);
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
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
