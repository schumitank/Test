package com.scs.fullcalendar.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scs.fullcalendar.dao.UserDao;
import com.scs.fullcalendar.domain.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet  extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7900060531473301184L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String userColor = request.getParameter("userColor");
		
		User newUser = new User();
		newUser.setUserId(userName);
		newUser.setColor(userColor);
		
		boolean flg = new UserDao().insertUser(newUser);
		
		if (flg)
		{
			response.sendRedirect("login.jsp");
		}else
		{
			response.sendRedirect("register.jsp?error="+userName);
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doGet(request,response);
	}
}
