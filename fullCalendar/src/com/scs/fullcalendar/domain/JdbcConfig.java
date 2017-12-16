package com.scs.fullcalendar.domain;

/**
 * jdbc配置信息
 * 
 * @author Pan Yecheng(Roman)
 * 
 */
public class JdbcConfig {
	private String drivername;
	private String url;
	private String username;
	private String password;

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
