package com.qa.ExtentReportListener;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExtentReportListener implements IReporter {
    private ExtentReports extentReport;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory){
        extentReport = new ExtentReports(outputDirectory + File.separator + "FinalReportExtent.html",true);

        for(ISuite suite:suites){
            Map<String, ISuiteResult> resultMap = suite.getResults();

            for(ISuiteResult r: resultMap.values()){
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);

            }
        }
        extentReport.flush();
        extentReport.close();
    }

    public void buildTestNodes(IResultMap tests, LogStatus status){
        ExtentTest test;

        if(tests.size() >0){
            for(ITestResult result: tests.getAllResults()){
                test = extentReport.startTest(result.getMethod().getMethodName());
                test.setStartedTime(getTime(result.getStartMillis()));
                test.setEndedTime(getTime(result.getEndMillis()));

                for(String group:result.getMethod().getGroups())
                    test.assignCategory(group);

                if(result.getThrowable()!= null){
                    test.log(status, result.getThrowable());
                }
                else{
                    test.log(status, "Test" + status.toString().toLowerCase() + "ed");
                }
                extentReport.endTest(test);
            }
        }
    }

    private Date getTime(long millis){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
