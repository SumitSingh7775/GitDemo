package SeleniumTestGroup.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTestGroup.AbstractComponent.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> prodcutNames;
	
	public boolean verifyOrderHistoryDisplay(String productName) {
		
		boolean match = prodcutNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	
	
	

}
