package com.scs.fullcalendar.action;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scs.fullcalendar.dao.UserDao;
import com.scs.fullcalendar.domain.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1359683184348570612L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		
		HttpSession session = request.getSession();
		
		User searchUser = new UserDao().getUserInfo(userName);
		
		if (searchUser.getUserId() != null )
		{
			session.setAttribute("userName",searchUser.getUserId());
			session.setAttribute("userColor",searchUser.getColor());
			response.sendRedirect("index.jsp");
		}else
		{
			response.sendRedirect("login.jsp?error="+userName);
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doGet(request,response);
	}
}