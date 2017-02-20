package com.vbusiness.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vbusiness.entity.User;
import com.vbusiness.service.IUserService;
import com.vbusiness.service.impl.UserServiceImpl;

public class GetUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user_id = req.getParameter("user_id");
		if (user_id != "") {
			IUserService userService = new UserServiceImpl();
			User user = userService.getUserByUserId(user_id);
			req.setAttribute("userInfo", user);
			req.getRequestDispatcher("user.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("error.jsp");
		}
	}
}
