package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.Role;
import com.bean.skill;
import com.jdbc.JDBCUtils;

public class SkillDao {
private static final String SQL_search="select ��������,���ܼ۸� from skill where id=?";
	
	public skill skill_value(int id) {
		//�������ݿ⣬�������Ӷ���
		 Connection connection=JDBCUtils.getConnection();
		 PreparedStatement preparedStatement=null;
		try {
			//����Ԥ���뻷��
			preparedStatement=connection.prepareStatement(SQL_search);
	       //����sql����еĲ���
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				String name=resultSet.getString("��������");
				
				int price=resultSet.getInt("���ܼ۸�");
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
