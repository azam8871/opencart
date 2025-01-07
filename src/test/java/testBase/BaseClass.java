package testBase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups= {"Sanity","Regression","Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws MalformedURLException {
        logger = LogManager.getLogger(this.getClass());

        // Load configuration file
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            p = new Properties();
            p.load(input);
        } catch (IOException e) {
            logger.error("Error loading configuration file: " + e.getMessage());
            Assert.fail("Error loading configuration file: " + e.getMessage());
        }
        
 /*  // for slenium grid
  *        
  
        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // OS
            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.WIN11); // This should likely be Platform.MAC for macOS
            } else {
                System.out.println("No matching os");
                return;
            }

            // Browser
            switch (br.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("No matching browser");
                    return;
            }
            try {
                URI uri = new URI("http://localhost:4444/wd/hub");
                URL url = uri.toURL();
                driver = new RemoteWebDriver(url, capabilities);
            } catch (Exception e) {
                System.out.println("Error creating URL: " + e.getMessage());
                e.printStackTrace();
            }
        }  
        if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            // Browser initialization for local execution
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver(); // Fixed syntax
                    break;
                case "edge":
                    driver = new EdgeDriver(); // Fixed syntax
                    break;
                case "firefox":
                    driver = new FirefoxDriver(); // Fixed syntax
                    break;
                default:
                    System.out.println("Invalid browser name..");
                    return;
            }
        }
        */
      

        // Initialize WebDriver
        try {
            switch (br.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    logger.error("Invalid browser specified: " + br);
                    Assert.fail("Invalid browser specified: " + br);
                    return;
            }

            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            driver.get(p.getProperty("appURL2"));
            driver.manage().window().maximize();

            logger.info("Initialized WebDriver for browser: " + br);
            logger.info("Navigated to URL: " + p.getProperty("appURL2"));
        } catch (Exception e) {
            logger.error("Error during WebDriver initialization: " + e.getMessage());
            Assert.fail("Error during WebDriver initialization: " + e.getMessage());
        }
    }

    @AfterClass(groups= {"Sanity","Regression","Master"})
    public void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver session ended.");
        }
    }

    public String randomString() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();
        return generator.generate(5);
    }

    public String randomNumber() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build();
        return generator.generate(5);
    }

    public String randomAlphaNumeric() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(Character::isLetterOrDigit)
                .build();
        return generator.generate(10);
    }
    
    
    public String captureScreen(String tname) throws IOException {
        // Generate a timestamp to ensure the screenshot filename is unique
        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        // Cast the driver to TakesScreenshot
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        // Capture the screenshot and store it in a temporary file
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        // Define the target file path for the screenshot
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";

        // Create a new file instance for the target path
        File targetFile = new File(targetFilePath);

        // Copy the screenshot to the target location
        Files.copy(sourceFile.toPath(), targetFile.toPath());

        // Return the file path of the saved screenshot
        return targetFilePath;
    }
}