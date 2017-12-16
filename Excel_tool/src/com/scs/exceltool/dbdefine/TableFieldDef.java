package com.scs.exceltool.dbdefine;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

public class TableFieldDef extends AbastractBaseDef{
	
	private String fieldJPName;
	
	private String fieldEngName;
	
	private String fieldType;
	
	private String fieldLength;
	
	private String fieldPKFlg;
	
	private String remarkes;
	
	private String fieldJavaType;
	
	/** java type*/
	final static HashMap<String,String> JAVA_TYPE_MAP = new HashMap<String,String>();
	static{
		JAVA_TYPE_MAP.put("STRING", "java.lang.String");
		JAVA_TYPE_MAP.put("LONG", "java.lang.Long");
		JAVA_TYPE_MAP.put("DOUBLE", "java.lang.Double");
		JAVA_TYPE_MAP.put("DATE", "java.lang.Date");
		JAVA_TYPE_MAP.put("BOOLEAN", "java.lang.Boolean");
		JAVA_TYPE_MAP.put("BIGDECIMAL", "java.lang.BigDecimal");
	}
	
	public String getRemarkes(){
		return remarkes;
	}
	
	public void setRemarkes(String remarkes){
		this.remarkes = remarkes;
	}

	public String getFieldJPName() {
		return fieldJPName;
	}

	public void setFieldJPName(String fieldJPName) {
		this.fieldJPName = fieldJPName;
	}

	public String getFieldEngName() {
		return fieldEngName;
	}

	public void setFieldEngName(String fieldEngName) {
		this.fieldEngName = fieldEngName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldLength() {
		if (this.fieldType.equals(FLD_TYPE_DATE)
				|| this.fieldType.equals(FLD_TYPE_TIME)){
			return "";
		}
		return "{" + fieldLength + "}";
	}

	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getFieldPKFlg() {
		return fieldPKFlg;
	}

	public void setFieldPKFlg(String fieldPKFlg) {
		this.fieldPKFlg = fieldPKFlg;
	}
	
	
	
	public String getBeanProperty(){
		return fieldEngName.toLowerCase();
	}
	
	public String getMethodPart(){
		return convertCapitalUppercase(getBeanProperty());
	}
	
	public String getNullableAnnot(){
		if (fieldPKFlg.trim().equalsIgnoreCase(FLD_PK_FLG)){
			return "false";
		}
		return "true";
	}
	
	public String getNullableDDL(){
		if (StringUtils.contains(StringUtils.trim(remarkes), IDENTITY_FLAG)){
			return "identity";
		}
		if (fieldPKFlg.trim().equalsIgnoreCase(FLD_PK_FLG)){
			return "NOT NULL";
		}
		return "null";
	}
	
	public String getIdAnnot(){
		if (fieldPKFlg.trim().equalsIgnoreCase(FLD_PK_FLG)){
			return "@Id";
		}
		return "";
	}
	
	public String getJavaType(){
		if (FLD_NUMBER_TYPE_INT.equalsIgnoreCase(fieldType)){
			return "int";
		}
		return "String";
	}
	
	public String getFieldJavaType(){
		String returnJavaType = "";
		returnJavaType = JAVA_TYPE_MAP.get(fieldJavaType.trim().toUpperCase());
		if (returnJavaType == null || "".equals(returnJavaType)){
			returnJavaType = "java.lang.String";
		}
		return returnJavaType;
	}
	
	public void setFieldJavaType(String fieldJavaType){
		this.fieldJavaType = fieldJavaType;
	}
	
	public String getXmlName(){
		String xmlName = covertCapitalCase(fieldEngName,false);
		return xmlName;
	}
	
	public String getSimpleJavaType(){
		String simpleJavaType = covertCapitalCase(fieldJavaType,true);
		return simpleJavaType;
	}
}
