package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JDBC {
	//������Ϣ	
	private String userName = "root";//���������ʼ������mysql��������˺�
		private String password = "123456";//���������ʼmysql���õ����룬
		private String url = "jdbc:mysql://localhost:3306/basketball";
	//���ݿ�������Ϣ��
	//��һ����java�������ݿ�Э�飬�м����Ҫ���ӵ�ip��ַ�Ͷ˿ںţ�localhost�Ǳ���ip�����������Ҫ���ӵ����ݿ�����֣���Ҫ�������ݿ�����ֽ�testdatabase
		public Connection connection;
		


	{
			try {
	    	//���ﲻ�ø��ģ������Ҫ���Ĳ���mysql�Ļ��ٻ���������ڵĴ���
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("�������سɹ���");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("����ʧ�ܣ�");
				e.printStackTrace();
			}

	}
	public JDBC() throws SQLException  {
		// TODO Auto-generated constructor stub
		try {
			this.connection =  DriverManager.getConnection(url, userName, password);//��������������Դ�������Ӧ�þ����ӳɹ��ˣ����ɹ��Ļ�Ӧ�þ�������˺����벻��ȷ
			System.out.println("���ݿ����ӳɹ���");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			System.out.println("����ʧ�ܣ�");
		}
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
