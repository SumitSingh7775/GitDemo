package SeleniumTestGroup.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumTestGroup.SeleniumFrameworkDesign.TestComponent.BaseTest;
import SeleniumTestGroup.pageobject.CartPage;
import SeleniumTestGroup.pageobject.CheckoutPage;
import SeleniumTestGroup.pageobject.ConfirmationPage;
import SeleniumTestGroup.pageobject.LandingPage;
import SeleniumTestGroup.pageobject.OrdersPage;
import SeleniumTestGroup.pageobject.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EcommerceSA2Framework extends BaseTest {
	
	String pName = "ZARA COAT 3";
	String countryName = "India";
	
	@Test(dataProvider="getData",groups="Purchase")
	public void submitOrderTest(HashMap<String,String> input ) throws InterruptedException, IOException {
		
		
		ProductCatalogue pc =lp.webLogin(input.get("email"), input.get("pass"));
		
		List<WebElement> products=pc.getProduct();
		pc.addToCart(input.get("product"));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		CartPage cartP = pc.goToCart();
		
		boolean verifyProductnm = cartP.verifyProductDisplay(input.get("product"));
		System.out.println(verifyProductnm);
		Assert.assertTrue(verifyProductnm);
		
		CheckoutPage cp = cartP.Checkout();
		cp.SelectCountry(countryName);
		ConfirmationPage confirmation = cp.SubmitOrder();
		String message = confirmation.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	@Test(dependsOnMethods= {"submitOrderTest"})
	public void OrderHistoryTest() throws InterruptedException {
		ProductCatalogue pc =lp.webLogin("sumitsingh@gmail.com", "Sumit123!");
		OrdersPage op = pc.goToOrdersPage();
		Assert.assertTrue(op.verifyOrderHistoryDisplay(pName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data	= getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumTestGroup\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	
		
		
	}
	
	
	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * return new Object [][] {{"sumitsingh@gmail.com","Sumit123!","ZARA COAT 3"},
	 * {"rahulshetty@gmail.com", "Iamking@000","ADIDAS ORIGINAL"}};
	 * /*
		 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
		 * "sumitsingh@gmail.com"); map.put("pass", "Sumit123!"); map.put("product",
		 * "ZARA COAT 3");
		 * 
		 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
		 * "rahulshetty@gmail.com"); map1.put("pass", "Iamking@000");
		 * map1.put("product", "ADIDAS ORIGINAL");
		 *  return new Object [][]
		 * {{map},{map1}};
		 *
	 *  
	 *  }
	 */

}
