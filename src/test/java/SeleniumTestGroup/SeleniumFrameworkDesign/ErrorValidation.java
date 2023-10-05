package SeleniumTestGroup.SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumTestGroup.SeleniumFrameworkDesign.TestComponent.BaseTest;
import SeleniumTestGroup.pageobject.CartPage;
import SeleniumTestGroup.pageobject.LandingPage;
import SeleniumTestGroup.pageobject.ProductCatalogue;
import junit.framework.Assert;

public class ErrorValidation extends BaseTest {

	@SuppressWarnings({ "deprecation", "restriction" })
	@Test(retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {

		// String productName = "ZARA COAT 3";

		lp.webLogin("sumitsingh@gmail.com", "Sumit123!@#$");
		System.out.println(lp.getErrorMessage());
		Assert.assertEquals("Incorrect email or password", lp.getErrorMessage());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void ProductValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue pc = lp.webLogin("rahulshetty@gmail.com", "Iamking@000");

		List<WebElement> products = pc.getProduct();
		pc.addToCart(productName);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		CartPage cartP = pc.goToCart();

		boolean verifyProductnm = cartP.verifyProductDisplay(productName);
		System.out.println(verifyProductnm);
		Assert.assertTrue(verifyProductnm);
	}
}