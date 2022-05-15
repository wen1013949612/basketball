package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.Role;
import com.bean.User;
import com.jdbc.JDBCUtils;

public class RoleDao_imp {
	
	 private static final String SQL_search="select 力量,速度,金币 from roles where id=?";
	
	public Role role_value(int id) {
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		 PreparedStatement preparedStatement=null;
		try {
			//创建预编译环境
			preparedStatement=connection.prepareStatement(SQL_search);
	       //设置sql语句中的参数
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int strength=resultSet.getInt("力量");
				int speed=resultSet.getInt("速度");
				int gold=resultSet.getInt("金币");
				return  new Role(strength, speed, gold);
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
