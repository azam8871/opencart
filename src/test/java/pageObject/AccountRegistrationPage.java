package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	
	public	AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	
  @FindBy(xpath="//input[@id='input-firstname']")
   WebElement txtfirstName;
  
  @FindBy(xpath="//input[@id='input-lastname']")
   WebElement txtlastName;
  
  @FindBy(xpath="//input[@id='input-email']")
  WebElement txteMail;
  
  @FindBy(xpath="//input[@id='input-telephone']")
  WebElement txtphno;
  
  @FindBy(xpath="//input[@id='input-password']")
  WebElement txtpassword;
  
  @FindBy(xpath="//input[@id='input-confirm']")
  WebElement confpassword;

 
  @FindBy(xpath="//input[@name='agree']") 
   WebElement clkPolicy;
  
  @FindBy(xpath="//input[@value='Continue']")
  WebElement btnContinue;
	
  @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
  WebElement msgConfrmation;
  
  
  //Action
  
  public void setfirstName (String fname)
	{
	  txtfirstName.sendKeys(fname);
	}
	
  public void setlastName (String lname)
	{
	  txtlastName.sendKeys(lname);
	}
	
  public void setEMail (String email)
	{
	  txteMail.sendKeys(email);
	}
  
  public void setPhnno (String phnno)
	{
	  txtphno.sendKeys(phnno);
	}
	
  public void setpassword (String pwd)
	{
	  txtpassword.sendKeys(pwd);
	}
  
  public void setConfpassword (String pwd)
	{
	  confpassword.sendKeys(pwd);
	}
	
  public void setPrivacyPolicy() 
	{
	  
	  Actions act= new Actions(driver);
	  act.moveToElement(clkPolicy).click().perform();
	}
	  
	
  public void clickContinue() 
	{
	  
	  btnContinue.click();
	}
  
  public String getConformationMsg(){
	  
	  try 
	  {
	  return(msgConfrmation.getText());
	  }
	  catch(Exception e){
		  
		  return(e.getMessage());
		  }
	  }	

}



