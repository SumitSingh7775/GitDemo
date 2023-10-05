package SeleniumTestGroup.resorces;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
static ExtentReports extent;
	
	@BeforeTest
	public static ExtentReports getReportObject() {
		
		String file = System.getProperty("user.dir")+"\\reports\\test.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(file);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Test Result");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name", "Sumit singh");
		//extent.createTest(file);
		return extent;

}
}
