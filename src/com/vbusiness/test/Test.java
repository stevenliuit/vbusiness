package com.vbusiness.test;

import com.vbusiness.db.DBUtil;
import com.vbusiness.util.StringHelper;

public class Test {
public static void main(String[] args) {
	DBUtil db = new DBUtil();
	//测试能否成功建立连接
	System.out.println(db.getConnection());
	//测试查询记录功能
	System.out.println(DBUtil
			.executeQuery("select user_city from user where id=1"));
	//测试更新记录功能
//	String sql ="update user set user_city = ? where id = ?";
//	Object obj = DBUtil.executeQuery(sql, "广州",1);
//	System.out.println(DBUtil
//			.executeQuery("select user_city from user where id=1"));
	// 测试字符工具类
	System.out.println(StringHelper.getCurrentFormatDate());
	System.out.println(StringHelper.getCurrentTimeStamp());
}
}
