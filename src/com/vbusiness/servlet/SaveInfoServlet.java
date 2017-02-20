package com.vbusiness.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vbusiness.entity.User;
import com.vbusiness.service.IUserService;
import com.vbusiness.service.impl.UserServiceImpl;
import com.vbusiness.util.StringHelper;

public class SaveInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 修改的保存方法
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
			IUserService userService = new UserServiceImpl();
			User user = userService.getUserByUserId(session
					.getAttribute("user").toString());
			user.setUser_name(req.getParameter("user_name"));
			user.setUser_birth(req.getParameter("user_birth"));
			user.setUser_city(req.getParameter("user_city"));
			// 修改密码，不填不修改
			String user_pass = req.getParameter("user_pass");
			if (user_pass != "") {
				// 设置新密码
				user.setUser_pass(StringHelper.MD5(user_pass));
			} else {
				// 保持原来的密码
				user.setUser_pass(req.getParameter("re_pass"));
			}
			user.setUser_secq(Integer.parseInt(req.getParameter("user_secq")));
			user.setUser_answer(req.getParameter("user_answer"));
			boolean bool = userService.saveUserInfo(user);
			if (bool) {
				req.setAttribute("msg", "信息修改成功");
			} else {
				req.setAttribute("msg", "信息修改失败");
			}
			req.getRequestDispatcher("success.jsp").forward(req, resp);
	}
}
