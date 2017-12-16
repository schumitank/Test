package com.scs.exceltool.dbdefine;

public class AbastractBaseDef implements ConstTableField{
	protected String convertCapitalUppercase(String orginal){
		return orginal.substring(0,1).toUpperCase() + orginal.substring(1);
	}
	
	protected String covertCapitalCase(String orginal,boolean flg){
		char temp;
		String newstring = new String();
		
		for (int i=0;i < orginal.length();i++){
			temp = orginal.charAt(i);
			if (i == 0){
				if (flg){
					newstring += Character.toUpperCase(temp);
				}else{
					newstring += Character.toLowerCase(temp);
				}
			}else{
				newstring += orginal.charAt(i);
			}
		}
		return newstring;
	}
}
