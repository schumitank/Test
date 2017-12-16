package com.scs.fullcalendar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.scs.fullcalendar.domain.Calendar;
import com.scs.fullcalendar.util.DbUtil;

public class CalendarDao {
	
	//添加日历事件
	public boolean add(Calendar calendar) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into calendar(title, start, end, allday, color, userId) values(?, ?, ?, ?, ?, ?)";
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, calendar.getTitle());
			pstmt.setString(2, calendar.getStart());
			pstmt.setString(3, calendar.getEnd());
			pstmt.setInt(4, calendar.getAllDay());
			pstmt.setString(5, calendar.getColor());
			pstmt.setString(6, calendar.getUserId());
			int t = pstmt.executeUpdate();
			if (t == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return flag;
	}
	
	//根据编号删除日历事件
	public boolean del(Integer id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from calendar where id = " + id;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			int t = pstmt.executeUpdate();
			if (t == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return flag;
	}
	
	//修改
	public boolean modify(Calendar calendar) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update calendar set title = ?, start = ?, end = ?, allDay = ? where id = ?";
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, calendar.getTitle());
			pstmt.setString(2, calendar.getStart());
			pstmt.setString(3, calendar.getEnd());
			pstmt.setInt(4, calendar.getAllDay());
			//pstmt.setString(5, calendar.getColor());
			pstmt.setInt(5, calendar.getId());
			int t = pstmt.executeUpdate();
			if (t == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return flag;
	}
	
	//根据sql语句修改日历事件
	public boolean modify(String sql) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			int t = pstmt.executeUpdate();
			if (t == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return flag;
	}
	
	//查询所有日历事件
	public List<Calendar> find() {
		List<Calendar> list = new ArrayList<Calendar>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id, title, start, end, allday, color, userId from calendar";
		try {
			conn = DbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Calendar calendar = new Calendar();
				calendar.setId(rs.getInt(1));
				calendar.setTitle(rs.getString(2));
				calendar.setStart(rs.getString(3));
				calendar.setEnd(rs.getString(4));
				calendar.setAllDay(rs.getInt(5));
				calendar.setColor(rs.getString(6));
				calendar.setUserId(rs.getString(7));
				list.add(calendar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(stmt);
			DbUtil.close(conn);
		}
		return list;
	}
	
	//根据编号查询单个日历事件
	public Calendar findById(Integer id) {
		Calendar calendar = new Calendar();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id, title, start, end, allday, color, userId from calendar where id = " + id;
		try {
			conn = DbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				calendar.setId(rs.getInt(1));
				calendar.setTitle(rs.getString(2));
				calendar.setStart(rs.getString(3));
				calendar.setEnd(rs.getString(4));
				calendar.setAllDay(rs.getInt(5));
				calendar.setColor(rs.getString(6));
				calendar.setUserId(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(stmt);
			DbUtil.close(conn);
		}
		return calendar;
	}
}
