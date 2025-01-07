package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_Login extends BaseClass {
	
	
   @Test(groups= {"Sanity","Master"})
    public void verify_Login() throws InterruptedException {
	   
    	logger.info("***** Starting the Login page ***** ");	
   
	try {   
    	HomePage hp=new HomePage(driver);
	    hp.ClickmyAccount();
	    hp.Clicklogin();
	   
	    LoginPage lp = new LoginPage(driver);
	    lp.setEmail(p.getProperty("email")); // Use the email from the configuration file
	    lp.setpwd(p.getProperty("password")); // Use the password from the configuration file

	    lp.clickLoginButton();
	    
	    MyAccountPage macc=new MyAccountPage(driver);
	   boolean targetpage=macc.isMyAccountPageExists();
	    Assert.assertTrue(targetpage);
	}
	catch(Exception e) {
		
		Assert.fail();
	}
	     
	    logger.info("***** Ending the Login page ***** ");	
	    
	    
	    
    
   }
}
