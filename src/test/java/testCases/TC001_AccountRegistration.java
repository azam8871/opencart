package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass {
	
	
   @Test(groups= {"Regression","Master"})
    public void verify_Account_Registration() throws InterruptedException {
	   
    	logger.info("***** Starting the registration page ***** ");	
    try {
	   HomePage hp=new HomePage(driver);
	   
	   hp.ClickmyAccount();
	   logger.info("Click on myAccount link ");	
	   
	 hp.Clickregister();
	logger.info("Click on register link ");	 
	   
	   AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
	  
	   logger.info("Providing User Details");	
	   regpage.setfirstName(randomString().toUpperCase());
       regpage.setlastName(randomString().toUpperCase());
       regpage.setEMail(randomString() + "@gmail.com");
       regpage.setPhnno(randomNumber());

       String pasword = randomAlphaNumeric();
       regpage.setpassword(pasword);
       regpage.setConfpassword(pasword);

       regpage.setPrivacyPolicy();
     
	   regpage.clickContinue();
	   
	   logger.info("Validating expected message");	
	  String confmsg = regpage.getConformationMsg();
	  
  if (confmsg.equals("Your Account Has Been Created!")) 
	  {
	  Assert.assertTrue(true);
	  }
	/*  if (confmsg.equals("Your Account Has Been Created!!!")) 
	  {
	  Assert.assertTrue(true);
	  }*/
	  else
	  {
		  logger.error("test failed..");
	      logger.debug("debug log.."); 
	      Assert.assertTrue(false);
	  }
    }
    catch(Exception e)
    {
    	Assert.fail();
    }
    
    logger.info("*** Finished test case ****");
    
    }
   
   // one method
/*   
   public String randomString() {
	   
	   String genstring=RandomStringUtils.randomAlphabetic(5);
	   return genstring;   
   }
   
   public String randomNumber() {
	   
	   String genNumber=RandomStringUtils.randomNumeric(5);
	   return genNumber;
   }
   
 public String randomAlphaNumberic() {
	   
	 String genstring=RandomStringUtils.randomAlphabetic(5);
	 String genNumber=RandomStringUtils.randomNumeric(5);
	   return (genstring+"@"+genNumber);
   }
	*/

}
