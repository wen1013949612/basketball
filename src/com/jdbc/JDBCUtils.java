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
	//��̬���� 
	static {
	InputStream iStream= JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
	//����property
	Properties properties=new Properties();
	//�������ļ�
	try {
		properties.load(iStream);
		 driver= properties.getProperty("driver");
		 url=properties.getProperty("url");
		 username=properties.getProperty("username");
		 password=properties.getProperty("password");
		//��������
		Class.forName(driver);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	}
	//������Ӷ���
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//�ͷ���Դ�ķ�ʽ
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
