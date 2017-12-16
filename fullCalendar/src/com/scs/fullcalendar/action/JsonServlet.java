package com.scs.fullcalendar.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import com.scs.fullcalendar.dao.CalendarDao;
import com.scs.fullcalendar.domain.Calendar;

@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JsonServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<Calendar> list = new CalendarDao().find();
		String json = JSONSerializer.toJSON(list).toString();
		out.print(json);
		//值得一提的是，在返回的json数据中，每个字段如id，title..对应着FullCalendar的Event Object事件对象中的属性id，title..。
	}

}
