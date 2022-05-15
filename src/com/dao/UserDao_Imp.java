package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.User;
import com.jdbc.JDBCUtils;

public class UserDao_Imp implements UserDao{
   private static final String SQL_LOGIN="select name from player where ID=? AND password=?";
   private static final String SQL_REGISTER="insert into player values(?,?,?) ";
   private static final String SQL_I="insert into playevalue values(?,0,100,?)";
   private static final String  sql_Uservalue="select �ۼƵ÷� ,��� ,���� from playevalue where ID=?";
   private static final String SQL_ALTERGOLD="update  playevalue SET ���=? where ID=?  ";
   private static final String SQL_ALTERSCORE="update  playevalue SET �ۼƵ÷�=? where ID=?  ";
   private static final String SQL_createroles="insert into haveroles values(?,0,0,0) ";
   //��¼�˺�
 //��¼�˺�
   @Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		//�������ݿ⣬�������Ӷ���
		 Connection connection=JDBCUtils.getConnection();
		try {
			//����Ԥ���뻷��
			PreparedStatement preparedStatement=connection.prepareStatement(SQL_LOGIN);
	       //����sql����еĲ���
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setInt(2, user.getPassword());
		   //ִ�����
			ResultSet resultSet=preparedStatement.executeQuery();
		   while(resultSet.next()) {
			   return true;
		   }
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

 //ע���˺�
//ע���˺�
 //ע���˺�
	@Override
	public boolean insert(User user) {
		// TODO Auto-generated method stub
		//�������ݿ⣬�������Ӷ���
		 Connection connection=JDBCUtils.getConnection();
		try {
			//����Ԥ���뻷��
			PreparedStatement preparedStatement=connection.prepareStatement(SQL_REGISTER);
	       //����sql����еĲ���
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setInt(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
		   //ִ�����
			
			int line=preparedStatement.executeUpdate();
			
		   while(line>0) {
			   return true;
		   }
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	
	//��ʼ����ɫ
	public boolean sql_i(User user) {
		//�������ݿ⣬�������Ӷ���
		 Connection connection=JDBCUtils.getConnection();
		try {
			//����Ԥ���뻷��
			PreparedStatement preparedStatement=connection.prepareStatement(SQL_I);
	       //����sql����еĲ���
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getName());
		
		   //ִ�����
			
			int line=preparedStatement.executeUpdate();
			
		   while(line>0) {
			   return true;
		   }
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	//��ʼ���Ƿ�ӵ�н�ɫ
		public void sql_create_haverole(int id) {
			//�������ݿ⣬�������Ӷ���
			 Connection connection=JDBCUtils.getConnection();
			try {
				//����Ԥ���뻷��
				PreparedStatement preparedStatement=connection.prepareStatement(SQL_createroles);
		       //����sql����еĲ���
				preparedStatement.setInt(1, id);

			
			   //ִ�����
				
				int line=preparedStatement.executeUpdate();
				
			  
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			
			return ;
		}
	
	//���ҽ�ɫ����Ϣ
	public User user_value(int id) {
		//�������ݿ⣬�������Ӷ���
		 Connection connection=JDBCUtils.getConnection();
		try {
			//����Ԥ���뻷��
			PreparedStatement preparedStatement=connection.prepareStatement(sql_Uservalue);
	       //����sql����еĲ���
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int score=resultSet.getInt("�ۼƵ÷�");
				int gold=resultSet.getInt("���");
				String name=resultSet.getString("����");
				return  new User(id, name, score, gold);
			}
		

		 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//�ı��ɫ���
	public void alter_gold(int id,int a) {

		PreparedStatement preparedStatement=null;
		//�������ݿ⣬�������Ӷ���
		 Connection connection=JDBCUtils.getConnection();
		try {
			//����Ԥ���뻷��
			 preparedStatement=connection.prepareStatement(SQL_ALTERGOLD);
	       //����sql����еĲ���
			preparedStatement.setInt(1, a);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection, preparedStatement, null);
		}
		
		return;
	}
	//�ı��ɫ�÷�
		public void alter_score(int id,int a) {

			PreparedStatement preparedStatement=null;
			//�������ݿ⣬�������Ӷ���
			 Connection connection=JDBCUtils.getConnection();
			try {
				//����Ԥ���뻷��
				 preparedStatement=connection.prepareStatement(SQL_ALTERSCORE);
		       //����sql����еĲ���
				preparedStatement.setInt(1, a);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				JDBCUtils.close(connection, preparedStatement, null);
			}
			
			return;
		}
	
	
	

}
