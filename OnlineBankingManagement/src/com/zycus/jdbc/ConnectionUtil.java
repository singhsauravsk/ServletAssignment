package com.zycus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class will establish connection with different types of databases.
 * @author saurav.kumar
 *
 */
public class ConnectionUtil {

	/**
	 * This method will establish connection with Apache Derby database.
	 * @return	Object of Connection Class (established connection).
	 */
	public static Connection getConnection() {
		String driver = "org.apache.derby.jdbc.ClientDriver";
		String url = "jdbc:derby://localhost:1527/sample";
		String user = "user";
		String pass ="pass";
		
		return getConnection(driver, url, user, pass);
	}
	
	/**
	 * This method will establish connection with different databases. 
	 * @param driver	Name of the driver class of desired database.
	 * @param url		Url of the desired database.
	 * @param user		Username of the desired database.
	 * @param pass		Passowrd for the given username.
	 * @return
	 */
	public static Connection getConnection(String driver, String url, String user, String pass) {
		
		/**
		 * Step1 : Load JDBC Driver class
		 */
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException ex) {
			System.out.println("No JDBC Driver found");
			
			return null;
		}
		
		/**
		 * Step2: Establish connection
		 */
		try {
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("Established connection with "+con.getMetaData().getDatabaseProductName());
			
			return con;
		}catch(SQLException ex) {
			System.out.println("Unable to connect! "+ex.getMessage());
			
			return null;
		}
	}
}
