package SeleniumTestGroup.SeleniumFrameworkDesign.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v111.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumTestGroup.pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp;
	public WebDriver InitializeDriver() throws IOException  {
		
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumTestGroup\\resorces\\GlobalData.properties");
		prop.load(fis);
		String broswerName = (System.getProperty("browser")!=null) ? System.getProperty("browser"):prop.getProperty("browser");
		
		if(broswerName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions op = new ChromeOptions();
			if (broswerName.contains("headless")) {
				op.addArguments("headless");
			}
			driver = new ChromeDriver(op);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			
		}
		return driver;
		
		
		
		
	}

	//button[contains(@class,"value")]
	//button[type*="partial value"]
	
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		
		//converting json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		
		return data;
		
		
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot sc = (TakesScreenshot)driver;
		File source = sc.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName +".png");
		FileUtils.copyFile(source, target);
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName +".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = InitializeDriver();
		 lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	
	  @AfterMethod(alwaysRun=true)
	  public void tearDown() {
	  
	  driver.close(); 
	  }
	 
	

}
