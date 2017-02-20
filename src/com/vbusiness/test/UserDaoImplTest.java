package com.vbusiness.test;

import com.vbusiness.dao.impl.UserDaoImpl;
import com.vbusiness.entity.User;
import com.vbusiness.util.StringHelper;

public class UserDaoImplTest {

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();
		User user = new User();
		user.setUser_id("小猛");
		user.setUser_name("孟婷");
		user.setUser_pass(StringHelper.MD5("123456"));
		user.setUser_referee("无");
		user.setUser_level(1);
		user.setLeft_num(1);
		user.setRight_num(2);
		user.setUser_city("南京");
		user.setUser_birth("1989-02-14");
		user.setCreated_time(StringHelper.getCurrentTimeStamp());
		System.out.println(userDao.addUser(user));
	}
}
