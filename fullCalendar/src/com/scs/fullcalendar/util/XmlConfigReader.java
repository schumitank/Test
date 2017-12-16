package com.scs.fullcalendar.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.scs.fullcalendar.domain.JdbcConfig;

/**
 * 采用单例模式解析sys-config.xml文件 
 * 
 * @author Pan Yecheng(Roman)
 *
 */
public class XmlConfigReader {
	
	private static XmlConfigReader instance = new XmlConfigReader();
	private JdbcConfig jdbcConfig = new JdbcConfig();//保存jdbc相关配置信息
	
	private XmlConfigReader(){
		SAXReader reader = new SAXReader();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("sys-config.xml");
		try {
			Document document = reader.read(is);
			
			//取得jdbc相关配置信息
			Element driverNameElt = (Element)document.selectObject("/config/db-info/drivername");
			Element urlElt = (Element)document.selectObject("/config/db-info/url");
			Element usernameElt = (Element)document.selectObject("/config/db-info/username");
			Element passwordElt = (Element)document.selectObject("/config/db-info/password");
			
			//设置jdbc相关的配置
			jdbcConfig.setDrivername(driverNameElt.getStringValue());
			jdbcConfig.setUrl(urlElt.getStringValue());
			jdbcConfig.setUsername(usernameElt.getStringValue());
			jdbcConfig.setPassword(passwordElt.getStringValue());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回jdbc相关配置
	 * @return
	 */
	public static XmlConfigReader getInstance(){
		return instance;
	}
	
	public JdbcConfig getJdbcConfig(){
		return jdbcConfig;
	}
	
}
