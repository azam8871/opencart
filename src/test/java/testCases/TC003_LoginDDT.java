package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	
   @Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
    public void verify_LoginDDT(String email,String pwd,String exp) throws InterruptedException  {
	   
    	logger.info("***** Starting the Login page ***** ");	
      
    	HomePage hp=new HomePage(driver);
	    hp.ClickmyAccount();
	    hp.Clicklogin();
	   
	    LoginPage lp = new LoginPage(driver);
	    lp.setEmail(email); // Use the email from the configuration file
	    lp.setpwd(pwd); // Use the password from the configuration file

	    lp.clickLoginButton();
	    
	    MyAccountPage macc=new MyAccountPage(driver);
	   boolean targetpage=macc.isMyAccountPageExists();
	   
	   
	   /*Data is valid - login success   - test pass      - logout
                          login failed   - test fail

	   Data is invalid - login success   - test fail      - logout
                          login failed   - test pass

	   */

	   if(exp.equalsIgnoreCase("valid")) 
	   {
		 if(targetpage==true) { 
		   Assert.assertTrue(true);
		   macc.clickLogout();
		   
		 }
		 else {
			 
			 Assert.assertTrue(false);
		 }
	   }
	
	   
	   if(exp.equalsIgnoreCase("invalid")) 
	   {
		 if(targetpage==true) { 
		  
		   macc.clickLogout();
		   Assert.assertTrue(false);
		 }
		 else {
			 
			 Assert.assertTrue(true);
		 }
	   }
	
	     
	    logger.info("***** Ending the Login page ***** ");
	    
	    
	    
    
   }
}
