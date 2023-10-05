package SeleniumTestGroup.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumTestGroup.pageobject.CartPage;
import SeleniumTestGroup.pageobject.OrdersPage;



public class AbstractComponent {
	
	WebDriver driver;
	
	
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='/dashboard/cart']")
	WebElement cart;

	@FindBy(css="[routerlink*='myorders']")
	WebElement orderheader;
	
	public void waitForElementToAppear(By find) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(find));
	}
	
	public void waitForElementToBeClickable(WebElement eb) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(eb));
	}
	
	public CartPage goToCart() throws InterruptedException {
		Thread.sleep(3000);
		cart.click();
		CartPage cartP = new CartPage(driver);
		return cartP;
	}
	
	public OrdersPage goToOrdersPage() throws InterruptedException {
		Thread.sleep(1000);
		orderheader.click();
		OrdersPage op = new OrdersPage(driver);
		return op;
	}
	
	
	
}
