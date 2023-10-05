package SeleniumTestGroup.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTestGroup.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//*[@placeholder='Select Country']")
	WebElement country ;
	
	@FindBy(xpath="//a[text()=\"Place Order \"]")
	WebElement submit ;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry ;
	
	By results = By.cssSelector(".ta-results");
	
	public void SelectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();;
		waitForElementToAppear(results);
		selectCountry.click();
		
	}
	
	public ConfirmationPage SubmitOrder() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", submit);
		//waitForElementToBeClickable(submit);
	   // submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
}
