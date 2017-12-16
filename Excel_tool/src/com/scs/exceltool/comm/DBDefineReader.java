package com.scs.exceltool.comm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.scs.exceltool.dbdefine.ConstTableField;
import com.scs.exceltool.dbdefine.TableDef;
import com.scs.exceltool.dbdefine.TableFieldDef;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DBDefineReader implements ConstTableField {
	private static List<String> targetList = null;
	
	private static DBDefineReader _self;
	
	private void DBDefinReader(){
		
	}
	
	public static DBDefineReader getInstance(){
		if (_self == null){
			_self = new DBDefineReader();
		}
		return _self;
	}
	
	public List<TableDef> readDefine(String defFileName) throws IOException{
		FileInputStream input = new FileInputStream(defFileName);
		HSSFWorkbook workBook = new HSSFWorkbook(input);
		
		List<TableDef> tblList = new ArrayList<TableDef>();

		for (int i = 0;i < workBook.getNumberOfSheets();i++){
			HSSFSheet sheet = workBook.getSheetAt(i);
			if (isTableDefSheet(sheet)){
				TableDef curTblDef = getTableDefine(sheet);
				tblList.add(curTblDef);
			}
			
		}
		input.close();
		return tblList;
	}
	
	private TableDef getTableDefine(HSSFSheet sheet){
		TableDef tblDef = new TableDef();
		tblDef.setTableJPName(getCellValue(sheet,TABLE_DEFINE_ROW,TABLE_JPN_NAME_COL));
		tblDef.setTableEngName(getCellValue(sheet,TABLE_DEFINE_ROW,TABLE_ENG_NAME_COL));
		
		int curRow = FIELD_DEFINE_START_ROW;
		int lastRow = sheet.getLastRowNum();
		int keyCount = 0;
		
		List<TableFieldDef> fieldList = new ArrayList<TableFieldDef>();
		while (curRow <= lastRow){
			if (isValidRow(sheet,curRow)){
				TableFieldDef fieldDef = new TableFieldDef();
				
				fieldDef.setFieldJPName(getCellValue(sheet,curRow,FIELD_JPN_NAME_COL));
				fieldDef.setFieldEngName(getCellValue(sheet,curRow,FIELD_ENG_NAME_COL));
				fieldDef.setFieldType(getCellValue(sheet,curRow,FIELD_TYPE_COL));
				fieldDef.setFieldLength(getCellValue(sheet,curRow,FIELD_LENGTH_COL));
				fieldDef.setFieldPKFlg(getCellValue(sheet,curRow,FIELD_PKFLG_COL));
				fieldDef.setRemarkes(getCellValue(sheet,curRow,FIELD_REMARK_COL));
				fieldDef.setFieldJavaType(getCellValue(sheet,curRow,FIELD_JAVATYPE_COL));
				
				if ("y".equals(fieldDef.getFieldPKFlg().toLowerCase().trim())){
					keyCount++;
				}
				fieldList.add(fieldDef);
			}
			curRow++;
		}
		tblDef.setFieldList(fieldList);
		if (keyCount == 1){
			//singel
			tblDef.setKeyType("S");
		}else{
			//composite
			tblDef.setKeyType("C");
		}
		
		return tblDef;
	}
	
	private boolean isValidRow(HSSFSheet sheet, int row){
		if (sheet.getRow(row) == null
				|| getCellValue(sheet, row, FIELD_ENG_NAME_COL).isEmpty()){
			return false;		
		}
		return true;
	}
	
	private boolean isTableDefSheet(HSSFSheet sheet){
		String tblName = getCellValue(sheet, TABLE_DEFINE_ROW, TABLE_ENG_NAME_COL);
		
		if (tblName != null && !"".equals(tblName.trim())){
			return true;
		}
		return false;
	}
	
	private String getCellValue(HSSFSheet sheet, int row, int col){
		if (sheet.getRow(row) == null){
			return "";
		}
		
		HSSFCell cell = sheet.getRow(row).getCell(col);
		String cellVal = "";
		
		if (cell != null){
			if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
				cellVal = cell.getStringCellValue();
			}else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				cellVal = Integer.toString((int) cell.getNumericCellValue());
			}else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
				if (cell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_STRING){
					cellVal = cell.getStringCellValue();
				}else if(cell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_NUMERIC){
					cellVal = Integer.toString((int) cell.getNumericCellValue());
				}
			}
		}
		
		return cellVal;
	}
}
