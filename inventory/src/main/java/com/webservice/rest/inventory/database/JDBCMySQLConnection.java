package com.webservice.rest.inventory.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCMySQLConnection {
	private static Connection connection;
	private static JDBCMySQLConnection instance=new JDBCMySQLConnection();
	private static String url;
	private static String dbName;
	private static String username;
	private static String password;
	private static String completeUrl;
	
	private JDBCMySQLConnection() {
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("config.properties");
			props.load(in);
			in.close();

			String driver = props.getProperty("jdbc.driver");
			if (driver != null) {
				Class.forName(driver);
			}

			url = props.getProperty("jdbc.url");
			dbName = props.getProperty("jdbc.dbname");
			username = props.getProperty("jdbc.username");
			password = props.getProperty("jdbc.password");
			completeUrl=url+dbName+"?autoReconnect=true&useSSL=false";
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (FileNotFoundException e){
			System.err.println(e.getMessage());
		}catch (IOException e) {
			System.err.println(e.getMessage());
		} 
	}
	
	private Connection createConnection() {
        Connection connection = null;
        try {
        	connection = DriverManager.getConnection(completeUrl, username, password);
        } catch (SQLException e) {
        	System.err.println(e.getMessage());
        }
        return connection;
    }   
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
	
	
}
