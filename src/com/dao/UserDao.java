package com.dao;

import javax.security.auth.login.LoginContext;

import com.bean.User;

public interface UserDao {

	//��¼�˺�
	boolean login(User user);
	
	//ע���˺�
	boolean insert(User user);
	
	
	
	
}
