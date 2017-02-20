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
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
			IUserService userService = new UserServiceImpl();
			User user = userService.getUserByUserId(session
					.getAttribute("user").toString());
			req.setAttribute("user", user);
			req.getRequestDispatcher("info.jsp").forward(req, resp);
	}
}
