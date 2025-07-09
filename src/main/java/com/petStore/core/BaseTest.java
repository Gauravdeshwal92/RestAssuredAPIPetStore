package com.petStore.core;

import com.aventstack.extentreports.Status;
import com.petStore.Helper.BaseTestHelper;
import com.petStore.Utils.ExtentReport;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import java.io.IOException;


public class BaseTest extends BaseTestHelper{

    @BeforeSuite(alwaysRun = true)
    public void config() throws IOException {
        //Create the path in which we will create folder to keep html reports
        String subfolderpath = System.getProperty("user.dir") + "/reports/" + BaseTestHelper.Timestamp();
        //create sub folder
        CreateFolder(subfolderpath);
        ExtentReport.initialize(subfolderpath + "/" + "API_Execution_Automation.html");

    }
    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReport.extentlog.log(Status.PASS, "Test Case : "+ result.getName()+" is passed ");

        } else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReport.extentlog.log(Status.FAIL, "Test case : "+ result.getName()+" is failed ");
            ExtentReport.extentlog.log(Status.FAIL, "Test case is failed due to:  " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReport.extentlog.log(Status.SKIP, "Test case is Skiped " + result.getName());
        }
    }

    @AfterSuite(alwaysRun = true)
    public void endReport() {
        ExtentReport.extentreport.flush();
    }
}
