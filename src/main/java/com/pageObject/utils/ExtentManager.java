package com.pageObject.utils;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getReportInstance() {
		if(extent==null) {
			
			extent = new ExtentReports(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\extentReport.html", true, DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\pageObject\\extentconfig\\ReportsConfig.xml"));
		}
		return extent;
	}
}
