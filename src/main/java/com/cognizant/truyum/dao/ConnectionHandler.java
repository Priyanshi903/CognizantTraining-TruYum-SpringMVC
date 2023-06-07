package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
	private static Connection con = null;
    private static Properties props = new Properties();
	public static  Connection getConnection() throws IOException {
		
		    FileInputStream fis = new FileInputStream("connection.properties");
		    props.load(fis);
//		    System.out.println(props.getProperty("driver"));
		    try {
				Class.forName(props.getProperty("driver"));
				con=DriverManager.getConnection(props.getProperty("connection-url"),
						props.getProperty("user"),props.getProperty("password"));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return con;
	}


}
