package com.scs.exceltool.dbdefine;

import java.util.ArrayList;
import java.util.List;

public class TableDef extends AbastractBaseDef{
	private String tableJPName;
	
	private String tableEngName;
	
	private List<TableFieldDef> fieldList;
	
	private String keyType;
	
	public String getTableJPName() {
		return tableJPName;
	}

	public void setTableJPName(String tableJPName) {
		this.tableJPName = tableJPName;
	}

	public String getTableEngName() {
		return tableEngName;
	}

	public void setTableEngName(String tableEngName) {
		this.tableEngName = tableEngName;
	}

	public List<TableFieldDef> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<TableFieldDef> fieldList) {
		this.fieldList = fieldList;
	}

	
	
	public String getEntityBeanName(){
		String[] valArr = tableEngName.split(TBL_NAME_SEPARATOR);
		
		String beanName = "";
		for (String str : valArr){
			if (!TBL_NAME_POSTFIX.equalsIgnoreCase(str)){
				beanName += convertCapitalUppercase(str.toLowerCase());
			}
		}
		
		return beanName + "EntityBean";
	}
	
	public boolean hasMultiKey(){
		int pkCnt = 0;
		for (TableFieldDef field : fieldList){
			if (field.getFieldPKFlg().trim().equalsIgnoreCase(FLD_PK_FLG)){
				pkCnt++;
			}
			if (pkCnt > 1){
				return true;
			}
		}
		return false;
	}
	public String getMultikeyBeanName(){
		String[] valArr = tableEngName.split(TBL_NAME_SEPARATOR);
		String beanName = "";
		for (String str : valArr){
			if (!TBL_NAME_POSTFIX.equalsIgnoreCase(str)){
				beanName += convertCapitalUppercase(str.toLowerCase());
			}
		}
		
		return beanName + "MultiKeyBean";
	}
	
	public boolean hasUniqueKey(){
		return false;
	}
	
	public String getUniqueKeyForEntity(){
		return "";
	}
	
	public String getUniqueKeyForSql(){
		return "";
	}
	
	public String getPrimaryKeyForSql(){
		List<String> pkList = getPrimaryKeyList();
		
		String rsltStr = "PRIMARY KEY (";
		for (int i = 0; i < pkList.size(); i++){
			rsltStr += pkList.get(i);
			if (i < pkList.size() - 1){
				rsltStr += ",";
			}
		}
		rsltStr += " )";
		
		return rsltStr;
	}
	
	private List<String> getPrimaryKeyList(){
		List<String> pkList = new ArrayList<String>();
		
		for (TableFieldDef field : fieldList){
			if (!field.getFieldPKFlg().isEmpty()
					&& FLD_PK_FLG.equals(field.getFieldPKFlg())){
				pkList.add(field.getFieldEngName());
			}
		}
		
		return pkList;
	}
	
	public String getKeyType(){
		return keyType;
	}
	
	public void setKeyType(String keyType){
		this.keyType = keyType;
	}
	
}
