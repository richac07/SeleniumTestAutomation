package com.swaglab.qa.util;

import com.swaglab.qa.base.TestBase;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends TestBase {
    public static long PAGE_LOAD_TIMEOUT =20;
    public static long IMPLICIT_WAIT = 10;

    //Access Data from the TestData

    public static String TESTDATA_EXCEL_PATH="/Users/i331396/Documents/Automation/SeleniumTestAutomation/src/main/java/com/swaglab/qa/testdata/productNameTestData.xlsx";
    static Workbook workbook;
    static Sheet sheet;
    public static Object[][] getTestData(String sheetName){
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_EXCEL_PATH);

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            workbook= WorkbookFactory.create(file);
        } catch (InvalidFormatException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        sheet=workbook.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for(int i =0;i<sheet.getLastRowNum();i++){
            for(int k=0;k<sheet.getRow(0).getLastCellNum();k++){
                data[i][k]=sheet.getRow(i+1).getCell(k).toString();
            }
        }
        return data;
    }

    public static void takeScreenshotAtEndOfTest() throws IOException{
        File scrFile= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String curDirectory = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(curDirectory+ "/screenshots/" + System.currentTimeMillis() + ".png"));
    }
    public void switchToFrame(){
        driver.switchTo().frame("mainFrame");
    }

}
