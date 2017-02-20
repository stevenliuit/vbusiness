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

public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入AddUserServlet中...");
		HttpSession session = req.getSession();
		String user_id = req.getParameter("user_id");
		String user_name = req.getParameter("user_name");
		String user_birth = req.getParameter("user_birth");
		String user_phone = req.getParameter("user_phone");
		String user_city = req.getParameter("user_city");
		String user_pass = req.getParameter("user_pass");
		String user_referee = session.getAttribute("user").toString();

		IUserService userSerive = new UserServiceImpl();

		User user = new User();
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setUser_birth(user_birth);
		user.setUser_city(user_city);
		user.setUser_pass(StringHelper.MD5(user_pass));
		user.setUser_referee(user_referee);
		user.setCreated_time(StringHelper.getCurrentTimeStamp());

		// 查询父节点的实体对象
		User parent_user = userSerive.getUserByUserId(user_referee);
		System.out.println("左："+parent_user.getLeft_num()+"   右："+parent_user.getRight_num());
		/**
		 * (1) 获取新节点的左右下标值：
		 * 
		 * 左下标值 = 父节点的右下标值； 右下标值 = 父节点的右下标值+1;
		 * 
		 * (2) 更新： 大于新增节点的左下标值的右下标值，都加2；
		 * 
		 * (3) 更新：大于新增节点的右下标值的左下标值，都加2；
		 * 
		 * 
		 *  查询机制：
		 *  
		 *   （1） 获取到子节点的数目：（右下标 - 左下标 - 1 ）/2 
		 *  
		 *   (2) 根据当前节点的层级数来获取它下面的两层的子节点（12个子节点）,根据左下标值进行升序排列
		 *  
		 * 
		 */
		// 设置新节点左下标
		user.setLeft_num(parent_user.getRight_num());
		// 设置新节点右下标
		user.setRight_num(parent_user.getRight_num() + 1);

		System.out.println(user.getRight_num());
		
		//新增用户成功前提
		if (userSerive.addUser(user)) {

			 User  current =userSerive.getUserByUserId(user_id); 
			// 更新： 大于新增节点的左下标值的右下标值，都加2；
			userSerive.updateRight_num(user.getLeft_num(),current.getId());
			// 更新：大于新增节点的右下标值的左下标值，都加2；
			userSerive.updateLeft_num(user.getRight_num(),current.getId());
			// 获取该账号所在的树形结构的层级数
			int user_level = userSerive.getUserLevelByUserId(user_id);
			//更新该账号所在的树形结构的层级数
			userSerive.updateUser_level(user_level, user_id);
			
			req.setAttribute("msg", "保存成功!");
		} else {
			req.setAttribute("msg", "保存失败!");
		}
		req.getRequestDispatcher("add.jsp").forward(req, resp);

	}

}
