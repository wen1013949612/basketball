package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jdbc.JDBCUtils;

public class HavaSkillDao_imp {
private static final String SQL_search1="select id,skill1,skill2 from haveskills where ID=?";
	
	private static  String SQL_ALTER1;
	
	//��ѯ�Ƿ���
	public ArrayList<Integer> haveSkills(int id) {
	//�������ݿ⣬�������Ӷ���
	 Connection connection=JDBCUtils.getConnection();
	try {
		//����Ԥ���뻷��
		PreparedStatement preparedStatement=connection.prepareStatement(SQL_search1);
       //����sql����еĲ���
		preparedStatement.setInt(1, id);
		ResultSet resultSet=preparedStatement.executeQuery();
		ArrayList<Integer> rolesArrayList=new ArrayList<>();
		
		while(resultSet.next()) {
			
			int i=1;
			while(i<=2) {
			
				rolesArrayList.add(resultSet.getInt("skill"+i));
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
	//�޸Ĺ������
	public void alter_skill(int id,int a) {
		if(a==1) {
			 SQL_ALTER1="update  haveskills SET skill1=1 where ID=?  ";
		}else if(a==2){
			SQL_ALTER1="update  haveskills SET skill2=1 where ID=?  ";
		}
		
		
		
		PreparedStatement preparedStatement=null;
		//�������ݿ⣬�������Ӷ���
		 Connection connection=JDBCUtils.getConnection();
		try {
			//����Ԥ���뻷��
			 preparedStatement=connection.prepareStatement(SQL_ALTER1);
	       //����sql����еĲ���
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
