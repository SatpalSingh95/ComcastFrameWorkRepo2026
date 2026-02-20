package com.comcast.crm.generic.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public static ExtentReports report;

	public static ExtentTest test; // this test i want to execute my test script class but i cannot extends so we make it static. // but this wil not participating in parallel execution

	@Override
	public void onStart(ISuite suite) {

		System.out.println("Report configuration");

		String time = new Date().toString().replace(" ", "_").replace(":", " ");

		// SPARK REPORT CONFIG
		spark = new ExtentSparkReporter("./AdvanceReport/report_" + time + ".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Env information and create test

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window-11");
		report.setSystemInfo("OS", "Chrome-100");

	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");

		// saving the report
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("======" + result.getMethod().getMethodName() + "=========Start===========");

		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "====> Started <=====");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("======" + result.getMethod().getMethodName() + "===========END=========");

		test.log(Status.PASS, result.getMethod().getMethodName() + "====> Completed <=====");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		// TakesScreenshot eDriver =(TakesScreenshot) UtilityClassObject.getDriver();
		TakesScreenshot eDriver = (TakesScreenshot) BaseClass.sdriver;
		String filePath = eDriver.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", " ");

		test.addScreenCaptureFromBase64String(filePath, testName + "_" + time);

		test.log(Status.FAIL, result.getMethod().getMethodName() + "====> Failed <=====");

		/*
		 * // step 1 create an object to EventFiring WebDriver EventFiringWebDriver
		 * edriver=new EventFiringWebDriver(BaseClass.sdriver);
		 * 
		 * // step 2 use getScreenshotAs method to get file type of screenshot File
		 * srcFile = edriver.getScreenshotAs(OutputType.FILE); // step 3 store
		 * screenshot on local drive try { // it will be only for one test case
		 * execution FileUtils.copyFile(srcFile, new File("./screenshot/test.png"));
		 * 
		 * 
		 * // we want multiple or different screenshot names FileUtils.copyFile(srcFile,
		 * new File("./screenshot/"+testName+".png")); String time = new
		 * Date().toString().replace(" " ,"_").replace(":"," "); //
		 * FileUtils.copyFile(srcFile, new File("./screenshot/"+testName+
		 * "+"+time+".png")); } catch (IOException e) { // TODO Auto-generated catch
		 * e.printStackTrace(); }
		 */

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		// test.log(Status.SKIP,result.getMethod().getMethodName()+"====> Skipped
		// <=====");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
