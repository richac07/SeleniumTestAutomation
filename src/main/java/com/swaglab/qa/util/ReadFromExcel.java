package com.swaglab.qa.util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLOutput;

public class ReadFromExcel {
    public static void main(String[] args) throws Exception {


        FileInputStream fileInputStream = new FileInputStream("/Users/i331396/Documents/Automation/SeleniumTestAutomation/src/main/java/com/swaglab/qa/testdata/productNameTestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("ProductName");
        int rowNum = 1;
        String[] data = new String[sheet.getRow(rowNum).getLastCellNum()];

        int len = data.length;
        for (int colNum = 0; colNum < len; colNum++) {
            data[colNum] = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

        }
        System.out.println(data);

    }
}
