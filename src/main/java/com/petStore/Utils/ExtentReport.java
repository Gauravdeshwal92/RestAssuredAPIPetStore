package com.petStore.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentReport {

    public static ExtentReports extentreport = null;

    public static ExtentTest extentlog;

    public static ExtentSparkReporter spark ;

    public static void initialize(String reportPath) throws IOException {
        try {
            if (extentreport == null) {
                extentreport = new ExtentReports();

                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                spark.loadXMLConfig(new File(System.getProperty("user.dir") + "/resources/extent-config.xml"));


                extentreport.attachReporter(spark);
                extentreport.setSystemInfo("Host Name", System.getProperty("user.name"));
                extentreport.setSystemInfo("Environment", "QA");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

