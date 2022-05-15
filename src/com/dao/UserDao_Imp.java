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
   private static final String  sql_Uservalue="select 累计得分 ,金币 ,姓名 from playevalue where ID=?";
   private static final String SQL_ALTERGOLD="update  playevalue SET 金币=? where ID=?  ";
   private static final String SQL_ALTERSCORE="update  playevalue SET 累计得分=? where ID=?  ";
   private static final String SQL_createroles="insert into haveroles values(?,0,0,0) ";
   //登录账号
 //登录账号
   @Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			PreparedStatement preparedStatement=connection.prepareStatement(SQL_LOGIN);
	       //设置sql语句中的参数
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setInt(2, user.getPassword());
		   //执行语句
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

 //注册账号
//注册账号
 //注册账号
	@Override
	public boolean insert(User user) {
		// TODO Auto-generated method stub
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			PreparedStatement preparedStatement=connection.prepareStatement(SQL_REGISTER);
	       //设置sql语句中的参数
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setInt(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
		   //执行语句
			
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
	
	//初始化角色
	public boolean sql_i(User user) {
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			PreparedStatement preparedStatement=connection.prepareStatement(SQL_I);
	       //设置sql语句中的参数
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getName());
		
		   //执行语句
			
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
	//初始化是否拥有角色
		public void sql_create_haverole(int id) {
			//连接数据库，创建连接对像
			 Connection connection=JDBCUtils.getConnection();
			try {
				//创建预编译环境
				PreparedStatement preparedStatement=connection.prepareStatement(SQL_createroles);
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
	
	//查找角色的信息
	public User user_value(int id) {
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			PreparedStatement preparedStatement=connection.prepareStatement(sql_Uservalue);
	       //设置sql语句中的参数
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int score=resultSet.getInt("累计得分");
				int gold=resultSet.getInt("金币");
				String name=resultSet.getString("姓名");
				return  new User(id, name, score, gold);
			}
		

		 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//改变角色金币
	public void alter_gold(int id,int a) {

		PreparedStatement preparedStatement=null;
		//连接数据库，创建连接对像
		 Connection connection=JDBCUtils.getConnection();
		try {
			//创建预编译环境
			 preparedStatement=connection.prepareStatement(SQL_ALTERGOLD);
	       //设置sql语句中的参数
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
	//改变角色得分
		public void alter_score(int id,int a) {

			PreparedStatement preparedStatement=null;
			//连接数据库，创建连接对像
			 Connection connection=JDBCUtils.getConnection();
			try {
				//创建预编译环境
				 preparedStatement=connection.prepareStatement(SQL_ALTERSCORE);
		       //设置sql语句中的参数
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
