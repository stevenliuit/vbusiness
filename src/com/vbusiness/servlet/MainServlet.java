package com.vbusiness.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vbusiness.entity.User;
import com.vbusiness.service.IUserService;
import com.vbusiness.service.impl.UserServiceImpl;

public class MainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入MainServlet...");
		HttpSession session = req.getSession();
		IUserService userSerive = new UserServiceImpl();
		String user_id = req.getParameter("user");
		if (user_id == null) {
			user_id = session.getAttribute("user").toString();
		}
		User user = userSerive.getUserByUserId(user_id);
		if (user != null) {
			List<User> userList = userSerive.getList(user.getLeft_num(), user.getRight_num(), user.getUser_level());
			int i = 0;
			for (User user2 : userList) {
				if (user2.getUser_level() == user.getUser_level() + 1) {
					// 第二层
					switch (i) {
					case 0:
						req.setAttribute("user_1", user2);
						break;
					case 1:
						req.setAttribute("user_2", user2);
						break;
					case 2:
						req.setAttribute("user_3", user2);
						break;
					default:
						req.setAttribute("user_4", user2);
						break;
					}
					i++;
				}
			}
			req.setAttribute("current_user", user);
			req.getRequestDispatcher("main.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("error.jsp");
		}

		// 第三层 ：第一组
		User next = (User) req.getAttribute("user_1");
		System.out.println("user_1======"+next.getUser_id());
		if (next != null) {
			List<User> userList_1 = userSerive.getList(next.getLeft_num(), next.getRight_num(), next.getUser_level());
			int j = 0;
			for (User user_1 : userList_1) {
				if (user_1.getUser_level() == next.getUser_level() + 1) {
					switch (j) {
					case 0:
						req.setAttribute("user_1_1", user_1);
						break;
					case 1:
						req.setAttribute("user_1_2", user_1);
						break;
					default:
						req.setAttribute("user_1_3", user_1);
						break;
					}
					System.out.println(next.getUser_id()+"---("+j+")-->"+user_1.getUser_id());
					j++;
				}
			}
		}

		// 第三层 ：第二组
		next = (User) req.getAttribute("user_2");
		System.out.println("user_2======"+next.getUser_id());
		if (next != null) {
			List<User> userList_2 = userSerive.getList(next.getLeft_num(), next.getRight_num(), next.getUser_level());
			int j = 0;
			for (User user_2 : userList_2) {
				if (user_2.getUser_level() == next.getUser_level() + 1) {
					switch (j) {
					case 0:
						req.setAttribute("user_2_1", user_2);
						break;
					case 1:
						req.setAttribute("user_2_2", user_2);
						break;
					default:
						req.setAttribute("user_2_3", user_2);
						break;
					}
					System.out.println(next.getUser_id()+"---("+j+")-->"+user_2.getUser_id());
					j++;
				}
			}
		}
		// 第三层 ：第三组
		next = (User) req.getAttribute("user_3");
		System.out.println("user_3======"+next.getUser_id());
		if (next != null) {
			List<User> userList_3 = userSerive.getList(next.getLeft_num(), next.getRight_num(), next.getUser_level());
			int j = 0;
			for (User user_3 : userList_3) {
				if (user_3.getUser_level() == next.getUser_level() + 1) {
					switch (j) {
					case 0:
						req.setAttribute("user_3_1", user_3);
						break;
					case 1:
						req.setAttribute("user_3_2", user_3);
						break;
					default:
						req.setAttribute("user_3_3", user_3);
						break;
					}
					System.out.println(next.getUser_id()+"---("+j+")-->"+user_3.getUser_id());
					j++;
				}
			}
		}

	}
}
