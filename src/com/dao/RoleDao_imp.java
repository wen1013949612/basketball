package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.Role;
import com.bean.User;
import com.jdbc.JDBCUtils;

public class RoleDao_imp {
	
	 private static final String SQL_search="select ����,�ٶ�,��� from roles where id=?";
	
	public Role role_value(int id) {
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
				int strength=resultSet.getInt("����");
				int speed=resultSet.getInt("�ٶ�");
				int gold=resultSet.getInt("���");
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
