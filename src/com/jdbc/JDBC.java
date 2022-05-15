package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JDBC {
	//连接信息	
	private String userName = "root";//这里是你最开始刚下载mysql是输入的账号
		private String password = "123456";//这里是你最开始mysql设置的密码，
		private String url = "jdbc:mysql://localhost:3306/basketball";
	//数据库连接信息，
	//第一个是java连接数据库协议，中间的是要连接的ip地址和端口号，localhost是本地ip，后面的是你要连接的数据库的名字，我要连接数据库的名字叫testdatabase
		public Connection connection;
		


	{
			try {
	    	//这里不用更改，如果你要连的不是mysql的话再换这个括号内的代码
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("驱动加载成功！");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("加载失败！");
				e.printStackTrace();
			}

	}
	public JDBC() throws SQLException  {
		// TODO Auto-generated constructor stub
		try {
			this.connection =  DriverManager.getConnection(url, userName, password);//把上面的三个属性传过来，应该就连接成功了，不成功的话应该就是你的账号密码不正确
			System.out.println("数据库连接成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			System.out.println("连接失败！");
		}
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
