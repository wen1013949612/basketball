package com.dao;

import javax.security.auth.login.LoginContext;

import com.bean.User;

public interface UserDao {

	//µÇÂ¼ÕËºÅ
	boolean login(User user);
	
	//×¢²áÕËºÅ
	boolean insert(User user);
	
	
	
	
}
