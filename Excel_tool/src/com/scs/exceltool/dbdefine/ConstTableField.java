package com.scs.exceltool.dbdefine;

import java.util.Arrays;
import java.util.List;

public interface ConstTableField {
	final static int TABLE_DEFINE_ROW = 2;
	
	final static int TABLE_JPN_NAME_COL = 0;
	
	final static int TABLE_ENG_NAME_COL = 3;
	
	final static int FIELD_DEFINE_START_ROW = 5;
	
	final static int FIELD_JPN_NAME_COL = 1;
	
	final static int FIELD_ENG_NAME_COL = 2;
	
	final static int FIELD_TYPE_COL = 3;
	
	final static int FIELD_LENGTH_COL = 4;
	
	final static int FIELD_PKFLG_COL = 5;
	
	final static int FIELD_REMARK_COL = 6;
	
	final static int FIELD_JAVATYPE_COL = 7;
	
	
	final static String TBL_NAME_SEPARATOR = "_";
	
	final static String TBL_NAME_POSTFIX = "TBL";
	
	final static String FLD_TYPE_DATE = "date";
	
	final static String FLD_TYPE_TIME = "datetime";
	
	final static String FLD_PK_FLG = "Y";
	
	final static String IDENTITY_FLAG = "Identity";
	
	final static String FLD_NUMBER_TYPE_INT = "INT";
	
	final static List<String> FLD_STRING_TYPE_LIST = Arrays.asList("CHAR","VARCHAR");

}
