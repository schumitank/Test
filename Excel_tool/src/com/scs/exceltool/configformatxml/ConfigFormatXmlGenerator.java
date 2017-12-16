package com.scs.exceltool.configformatxml;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.scs.exceltool.comm.DBDefineReader;
import com.scs.exceltool.dbdefine.TableDef;

public class ConfigFormatXmlGenerator {
	
	public static void main(String[] args){
//		if (args == null || args.length < 2){
//			System.out.println("Paramter is wrong");
//			return;
//		}
		String fileName = "export/SCSDefinition.xls";
		String target = "export/xml/format";
		
		ConfigFormatXmlGenerator gen = new ConfigFormatXmlGenerator();
		try {
			List<TableDef> tblList = DBDefineReader.getInstance().readDefine(fileName);
			gen.generateXml(target,tblList);			
		} catch (Exception e){
			System.out.println("666");
			e.printStackTrace();
		}
	}
	
	public void generateXml(String outPath, List<TableDef> tblList) throws Exception{
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		
		Template tmpl = ve.getTemplate("templates/configformatxml.vm","UTF-8");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String sysDateStr = dateFormat.format(new Date());
		
		for (TableDef tblDef : tblList){
			VelocityContext context = new VelocityContext();
			context.put("tblDef", tblDef);
			context.put("sysDate", sysDateStr);
			
			FileOutputStream stream = new FileOutputStream(getXmlFileName(outPath,tblDef));
			OutputStreamWriter writer = new OutputStreamWriter(stream,"UTF-8");
			tmpl.merge(context, writer);
			writer.flush();
			writer.close();
		}
	}

	private String getXmlFileName(String outPath, TableDef tblDef) {
		return outPath + "/" + "config-" + tblDef.getTableEngName().toLowerCase();
	}
}
