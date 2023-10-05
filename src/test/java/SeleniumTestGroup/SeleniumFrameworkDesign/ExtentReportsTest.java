package SeleniumTestGroup.SeleniumFrameworkDesign;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsTest {
	
	static ExtentReports extent;
	
	@BeforeTest
	public static ExtentReports config() {
		
		String file = System.getProperty("user.dir")+"\\reports\\test.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(file);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Test Result");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		return extent;
		
		
		
	}

	
	/*
	 * @Test public void initialDemo() {
	 * 
	 * ExtentTest test = extent.createTest("First Demo Test");
	 * 
	 * 
	 * 
	 * WebDriverManager.chromedriver().setup(); WebDriver driver = new
	 * ChromeDriver();
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 * driver.manage().window().maximize();
	 * driver.get("https://rahulshettyacademy.com/client/");
	 * System.out.println(driver.getTitle());
	 * 
	 * test.fail("do not match");
	 * 
	 * extent.flush();
	 * 
	 * 
	 * }
	 */
	
	
	

}
