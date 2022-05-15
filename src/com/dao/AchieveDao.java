package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jdbc.JDBCUtils;

public class AchieveDao {
	private static  String SQL_ALTER1="update  achievement SET achieve1=1 where ID=?  ";
	private static  String SQL_ALTER2="update  achievement SET achieve2=1 where ID=?  ";
	private static  String SQL_ALTER3="update  achievement SET achieve3=1 where ID=?  ";
	private static  String SQL_ALTER4="update  achievement SET achieve4=1 where ID=?  ";
	private static  String sql_initianize=" insert into achievement values(?,0,0,0,0)";
	public void alter_achieve1(int id) {
	
		PreparedStatement preparedStatement=null;
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			 preparedStatement=connection.prepareStatement(SQL_ALTER1);
	       //设置sql语句中的参数
			//preparedStatement.setString(1, a1);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection, preparedStatement, null);
		}
		
		return;
	}
	public void alter_achieve2(int id) {
		
		PreparedStatement preparedStatement=null;
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			 preparedStatement=connection.prepareStatement(SQL_ALTER2);
	       //设置sql语句中的参数
			//preparedStatement.setString(1, a1);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection, preparedStatement, null);
		}
		
		return;
	}
	public void alter_achieve3(int id) {
		
		PreparedStatement preparedStatement=null;
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			 preparedStatement=connection.prepareStatement(SQL_ALTER3);
	       //设置sql语句中的参数
			//preparedStatement.setString(1, a1);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection, preparedStatement, null);
		}
		
		return;
	}
	public void alter_achieve4(int id) {
		
		PreparedStatement preparedStatement=null;
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			 preparedStatement=connection.prepareStatement(SQL_ALTER4);
	       //设置sql语句中的参数
			//preparedStatement.setString(1, a1);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection, preparedStatement, null);
		}
		
		return;
	}
	public void sql_create_achieve(int id) {
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			PreparedStatement preparedStatement=connection.prepareStatement(sql_initianize);
	       //设置sql语句中的参数
			preparedStatement.setInt(1, id);

		
		   //执行语句
			
			int line=preparedStatement.executeUpdate();
			
		  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return ;
	}
	
}
