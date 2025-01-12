package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
    WebElement msgtxt;
	
	//
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") 
    WebElement linkLogout;
	
	
	public boolean isMyAccountPageExists() throws InterruptedException {
		
		Thread.sleep(2000);
		
		try 
		{
			return(msgtxt.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout() {
		
		linkLogout.click();
	}
   
	

}
