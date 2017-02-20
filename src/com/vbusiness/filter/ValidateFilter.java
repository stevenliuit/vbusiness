package com.vbusiness.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用来验证用户是否登录的Filter
 * 
 * @author Auger
 * 
 */
public class ValidateFilter implements Filter {

	private ServletContext servletContext;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		// 1.可以进行配置(用户访问的页面,都可以在web.xml中进行配置)

		// 获取配置的登录页面地址
		String login_page = servletContext.getInitParameter("login_page");
		// 获取我们需要登录才能访问的页面地址
		String validate_page = servletContext.getInitParameter("validate_page");

		// 获取共用的页面(不需要可以就访问的页面地址)
		String common_page = servletContext.getInitParameter("common_page");

		// 获取当前用户访问的路径地址
		String current_url = req.getServletPath();

		System.out.println("当前访问页面:" + current_url);
		System.out.println("公共的:" + common_page);
		if (common_page.indexOf(current_url) != -1) {
			System.out.println("你是公共页面,直接访问");
			chain.doFilter(request, response);
		} else if (validate_page.indexOf(current_url) != -1
				&& session.getAttribute("user") != null) {
			System.out.println("你是需要登录才能访问的,并且你也已经登录了,可以访问.");
			chain.doFilter(request, response);
		} else {
			// 跳转到登录页面
			rep.sendRedirect(req.getContextPath() + login_page);
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		servletContext = config.getServletContext();
	}

}
