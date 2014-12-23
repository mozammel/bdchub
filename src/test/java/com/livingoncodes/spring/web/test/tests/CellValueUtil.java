package com.livingoncodes.spring.web.test.tests;

import org.apache.poi.ss.usermodel.Cell;

public class CellValueUtil {

	public static String getStringValue(Cell cell) {
		if(cell == null)
			return "NA";
		
		String returnValue = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			returnValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			returnValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			returnValue = cell.getStringCellValue();
			break;
		}
		if(returnValue.charAt(0) == '\'') {
			return returnValue.substring(1);
		}
		return returnValue;
	}
}
