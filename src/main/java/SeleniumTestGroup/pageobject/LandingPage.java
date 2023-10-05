package SeleniumTestGroup.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTestGroup.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	public ProductCatalogue webLogin(String emailEle,String pass) {
		email.sendKeys(emailEle);
		password.sendKeys(pass);
		submit.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
	}
	
	public String getErrorMessage() {
		return errormessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		
	}

}
