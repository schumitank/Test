package com.scs.fullcalendar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.scs.fullcalendar.domain.User;
import com.scs.fullcalendar.util.DbUtil;

public class UserDao {
	public User getUserInfo(String userId){
		
		User resultUser = new User();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select userId, color from userinfo where userId = '" + userId + "'";
		try {
			conn = DbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				resultUser.setUserId(rs.getString(1));
				resultUser.setColor(rs.getString(2));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(stmt);
			DbUtil.close(conn);
		}
		return resultUser;
	
		
	}
	
	public boolean insertUser (User inUser){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into userinfo (userId,color) values(?,?)";
		try {
			conn = DbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, inUser.getUserId());
			stmt.setString(2, inUser.getColor());
			stmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DbUtil.close(rs);
			DbUtil.close(stmt);
			DbUtil.close(conn);
		}
		
		return true;
	}
}
