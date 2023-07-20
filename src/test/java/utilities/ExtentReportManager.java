package utilities;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

//####################################################################################################
	
	// If test is started then following method will be executed

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reportsTestng\\" + repName);// specify location of the report

		sparkReporter.config().setDocumentTitle("Cognizant Automation Report"); // Title of report
		sparkReporter.config().setReportName("MakeMyTrip Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Hackathon Case Study");
		extent.setSystemInfo("Module", "MakeMyTrip");
		extent.setSystemInfo("Sub Module", "Automation");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("Browser", "Chrome/Edge");
	}

//-----------------------------------------------------------------------------------------------------
	
	// If test is success then following method will be executed
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
//------------------------------------------------------------------------------------------------------
	
	// If test case is failed then following method will be executed

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
//----------------------------------------------------------------------------------------------------
	
	// If Test is skipped then following method will be executed

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
//----------------------------------------------------------------------------------------------------
	
	// After finishing all the tests following test case will be executed

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
//------------------------------------------------------------------------------------------------------

}

//#####################################################################################################
