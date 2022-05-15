package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.Role;
import com.bean.computer;
import com.jdbc.JDBCUtils;

public class ComputerDao_imp {
	 private static final String sql_blue="select ����,�ٶ� from computer_blue where id=?";
	 private static final String sql_red="select ����,�ٶ� from computer_red where id=?";
		public computer computer_value(int id) {
			//�������ݿ⣬�������Ӷ���
			 Connection connection=JDBCUtils.getConnection();
			 PreparedStatement preparedStatement=null;
			try {
				//����Ԥ���뻷��
				preparedStatement=connection.prepareStatement(sql_blue);
		       //����sql����еĲ���
				preparedStatement.setInt(1, id);
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					int strength=resultSet.getInt("����");
					int speed=resultSet.getInt("�ٶ�");
					
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
			//�������ݿ⣬�������Ӷ���
			 Connection connection=JDBCUtils.getConnection();
			 PreparedStatement preparedStatement=null;
			try {
				//����Ԥ���뻷��
				preparedStatement=connection.prepareStatement(sql_red);
		       //����sql����еĲ���
				preparedStatement.setInt(1, id);
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					int strength=resultSet.getInt("����");
					int speed=resultSet.getInt("�ٶ�");
					
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
