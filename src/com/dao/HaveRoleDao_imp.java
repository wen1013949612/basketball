package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.HaveRoles;
import com.bean.Role;
import com.jdbc.JDBCUtils;

public class HaveRoleDao_imp {
	private static final String SQL_search1="select role1,role2,role3 from haveroles where ID=?";
	
	private static  String SQL_ALTER1;
	private static  String SQL_ALTER2;
	private static  String SQL_ALTER3;
	//查询是否购买
	public ArrayList<Integer> haveRoles(int id) {
	//连接数据库，创建连接对像
	 Connection connection=JDBCUtils.getConnection();
	try {
		//创建预编译环境
		PreparedStatement preparedStatement=connection.prepareStatement(SQL_search1);
       //设置sql语句中的参数
		preparedStatement.setInt(1, id);
		ResultSet resultSet=preparedStatement.executeQuery();
		ArrayList<Integer> rolesArrayList=new ArrayList<>();
		
		while(resultSet.next()) {
			
			int i=1;
			while(i<=3) {
			
				rolesArrayList.add(resultSet.getInt("role"+i));
				i++;
			}
		}
		
	  return rolesArrayList;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return null;
}
	//修改购买情况
	public void alter_role(int id,int a) {
		if(a==1) {
			 SQL_ALTER1="update  haveroles SET role1=1 where ID=?  ";
		}else if(a==2){
			SQL_ALTER1="update  haveroles SET role2=1 where ID=?  ";
		}else if(a==3){
			SQL_ALTER1="update  haveroles SET role3=1 where ID=?  ";
		}
		
		
		
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
}
