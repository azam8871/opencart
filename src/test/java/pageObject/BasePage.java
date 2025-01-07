package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver) {
	    this.driver = driver; // Assign the driver first
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    PageFactory.initElements(driver, this);
	}
}