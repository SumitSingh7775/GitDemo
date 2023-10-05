package SeleniumTestGroup.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTestGroup.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this.driver);
	}



	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	 
	
	public List<WebElement> getProduct() {
		waitForElementToAppear(By.cssSelector(".mb-3"));
		return products;
	}
	
	public WebElement getProductName(String productName) {
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).
				findFirst().orElse(null);
		
		return prod;
	}
	
	public void addToCart(String productName) {
		WebElement prod = getProductName(productName);
		prod.findElement(addtocart).click();
	}
	
	
}
