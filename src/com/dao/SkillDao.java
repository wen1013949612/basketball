package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.Role;
import com.bean.skill;
import com.jdbc.JDBCUtils;

public class SkillDao {
private static final String SQL_search="select 技能名称,技能价格 from skill where id=?";
	
	public skill skill_value(int id) {
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
				String name=resultSet.getString("技能名称");
				
				int price=resultSet.getInt("技能价格");
				return  new skill( name,price);
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
