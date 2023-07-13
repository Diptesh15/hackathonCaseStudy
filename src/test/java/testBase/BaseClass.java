package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public Logger logger;
	public static WebDriver driver;

//#####################################################################################################################################
	
	/* 
	 - Below Method will run before execution of each Test Class
	 - Passing name of the browser as a parameter from xml file
	 - Deleting cookies to avoid prepopulated browser data if any
	 - Applying implicite wait
	 - Launching the website
	*/
	
	@BeforeClass(groups= {"Smoke", "Regression"})
	@Parameters("browser")
	public void setup(String br) {
		
		logger = LogManager.getLogger(this.getClass());
		
		//ChromeOptions options = new ChromeOptions();
		//options.setExperimentalOption("exculdeSwitches", new String[] {"enable-automation"});
		
		if(br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(br.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get("https://www.makemytrip.com/");
		
	}
	
//#####################################################################################################################################
	
	/*
	- Below methode will run after execution of each Test Class
	- Quitting all the browser windows
	*/
	
	@AfterClass(groups= {"Smoke", "Regression"})
	public void tearDown() {
		driver.quit();
	}
	
//#####################################################################################################################################
	
	/*
	 - This method will generate random string of size 5
	 */
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
//#####################################################################################################################################
	/* 
	 - This method will generate random numbers of 5 digits
	 */
	
	public String randomNumber() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return generatedString;
	}
	
//#####################################################################################################################################
	
	/*
	 - This method will take screenshot of the page
	 - This will return destination path to which screnshot is stored 
	 */
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	
}
