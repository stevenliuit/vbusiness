package com.vbusiness.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vbusiness.service.IUserService;
import com.vbusiness.service.impl.UserServiceImpl;
import com.vbusiness.util.StringHelper;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入到Servlet中...");
		HttpSession session = req.getSession();
		String account = req.getParameter("account"); // 获取到用户名
		System.out.println("account=" + account);
		String password = req.getParameter("password"); // 获取到密码
		System.out.println("password =" + password);
		// 调用业务逻辑处理类
		IUserService userService = new UserServiceImpl();
		// 接收返回的结果
		String result = userService.login(account, StringHelper.MD5(password));
		if (result.equals("3")) {
			session.setAttribute("user", account);
			session.setMaxInactiveInterval(3600);
		}
		PrintWriter out = resp.getWriter();
		//把结果传回给Ajax请求
		out.println(result);
	}
}
