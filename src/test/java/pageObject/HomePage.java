package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
			//constructor
	public	HomePage(WebDriver driver)
	{
		super(driver);
	}
		
	//Locators
	
         @FindBy(xpath="//span[normalize-space()='My Account']")
          WebElement myAccount;
         
        @FindBy(xpath="(//a[normalize-space()='Register'])[1]")
         WebElement register;
         
         @FindBy(xpath="(//a[normalize-space()='Login'])[1]")
         WebElement login;

	
		          //Action methods
	public void ClickmyAccount()
		{
		myAccount.click();
		}
	
	public void Clickregister()
	{
		register.click();
	}
		
	public void Clicklogin() 	{
		
	
		login.click();
	}
		
	}
	
	
	


