package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//input[@id='input-email']")
    WebElement txtemail;
   
   @FindBy(xpath="//input[@id='input-password']")
    WebElement txtpwd;
   
   @FindBy(xpath="//input[@value='Login']") 
   WebElement btnlogin;
   
  
   public void setEmail (String email)
  	{
	   txtemail.clear();
	   txtemail.sendKeys(email);
  	}
  	
    public void setpwd (String pwd)
  	{
    	txtpwd.clear();
  	  txtpwd.sendKeys(pwd);
  	}
    
    public void clickLoginButton() 
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(btnlogin)).click();
    }
  
}
