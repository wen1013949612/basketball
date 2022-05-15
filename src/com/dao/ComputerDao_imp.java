package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.Role;
import com.bean.computer;
import com.jdbc.JDBCUtils;

public class ComputerDao_imp {
	 private static final String sql_blue="select 力量,速度 from computer_blue where id=?";
	 private static final String sql_red="select 力量,速度 from computer_red where id=?";
		public computer computer_value(int id) {
			//连接数据库，创建连接对像
			 Connection connection=JDBCUtils.getConnection();
			 PreparedStatement preparedStatement=null;
			try {
				//创建预编译环境
				preparedStatement=connection.prepareStatement(sql_blue);
		       //设置sql语句中的参数
				preparedStatement.setInt(1, id);
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					int strength=resultSet.getInt("力量");
					int speed=resultSet.getInt("速度");
					
					return  new computer(speed, strength);
				}
			

			 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				JDBCUtils.close(connection, preparedStatement, null);
			}
			
			
			return null;
		}
		public computer computer_value2(int id) {
			//连接数据库，创建连接对像
			 Connection connection=JDBCUtils.getConnection();
			 PreparedStatement preparedStatement=null;
			try {
				//创建预编译环境
				preparedStatement=connection.prepareStatement(sql_red);
		       //设置sql语句中的参数
				preparedStatement.setInt(1, id);
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					int strength=resultSet.getInt("力量");
					int speed=resultSet.getInt("速度");
					
					return  new computer(speed, strength);
				}
			

			 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				JDBCUtils.close(connection, preparedStatement, null);
			}
			
			
			return null;
		}
	
}
