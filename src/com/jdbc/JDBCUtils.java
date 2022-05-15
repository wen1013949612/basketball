package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;



public class JDBCUtils {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	//静态语句块 
	static {
	InputStream iStream= JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
	//创建property
	Properties properties=new Properties();
	//加载流文件
	try {
		properties.load(iStream);
		 driver= properties.getProperty("driver");
		 url=properties.getProperty("url");
		 username=properties.getProperty("username");
		 password=properties.getProperty("password");
		//加载驱动
		Class.forName(driver);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	}
	//获得连接对象
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//释放资源的方式
	public static void close(Connection connection,Statement statement,ResultSet resultSet) {
		
			try {
				if(resultSet!=null) {
				resultSet.close();
				resultSet=null;
				}
				if(statement!=null) {
					statement.close();
					statement=null;
				}if(connection!=null) {
					connection.close();
					connection=null;
				}
				
			} catch (SQLException e) {   
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}
	
	
}
