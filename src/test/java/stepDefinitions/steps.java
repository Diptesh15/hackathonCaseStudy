package stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.CabBooking;
import pageObject.GiftCard;
import pageObject.HotelFunctionality;

public class steps {

	WebDriver driver;
	Logger logger; // for logging
	ResourceBundle rb; // for reading properties file
	String br; // to store browser name

	@Before
	public void setup() // Junit hook - executes once before starting
	{
		// for logging
		logger = LogManager.getLogger(this.getClass());
		// Reading config.properties (for browser)
		rb = ResourceBundle.getBundle("config");
		br = rb.getString("browser");

	}

	@After
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status ======> " + scenario.getStatus());
		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

		}
		driver.quit();
	}

	@Given("user launch browser")
	public void user_launch_browser() {
		// Write code here that turns the phrase above into concrete actions
		if (br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (br.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().window().maximize();
	}

	@Given("opens the url {string}")
	public void opens_the_url(String string) {
		// Write code here that turns the phrase above into concrete actions
		driver.get("https://www.makemytrip.com/");

	}

	@When("user clicks on cab weblink")
	public void user_clicks_on_cab_weblink() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		CabBooking cb = new CabBooking(driver);
		Thread.sleep(15000);
		cb.launchCabPage();
	}

	@Then("cab webpage will open")
	public void cab_webpage_will_open() {
		// Write code here that turns the phrase above into concrete actions
		String expected = "Online Cab Booking - Book Outstation Cabs at Lowest Fare @ MakeMyTrip";
		String actual = driver.getTitle();
		Assert.assertEquals(expected, actual);
	}
	
	@When("user enter data for cab search")
	public void user_enter_data_for_cab_search() throws InterruptedException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		CabBooking cb = new CabBooking(driver);
		cb.fillCabData();
	}

	@Then("results are displayed")
	public void results_are_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		String expected = "Cabs";
		String actual = driver.getTitle();
		Assert.assertEquals(expected, actual);
	}
	
	@When("user sort the results by selecting suv")
	public void user_sort_the_results_by_selecting_suv() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		CabBooking cb = new CabBooking(driver);
		cb.getSuv().click();
		Thread.sleep(3000);
		
	}

	@Then("user will get suv available as per criteria")
	public void user_will_get_suv_available_as_per_criteria() {
	    // Write code here that turns the phrase above into concrete actions
		CabBooking cb = new CabBooking(driver);
		String dummy = cb.getAppliedFilter().getText();
		Assert.assertEquals("SUV", dummy);
		String pr = cb.getLowestPrice().get(0).getText();
		System.out.println("Lowest fare is: " + pr);
	}
	
	@When("user clicks on gift card option")
	public void user_clicks_on_gift_card_option() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		GiftCard gc = new GiftCard(driver);
		Thread.sleep(2000);
		gc.launchGiftCard();
	}

	@Then("redirects to the gift card page")
	public void redirects_to_the_gift_card_page() {
	    // Write code here that turns the phrase above into concrete actions
		String expected = "Gift Cards - Buy Gift Card Online, Gift Vouchers | MakeMyTrip.com";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}
	
	@When("select wedding gift card")
	public void select_wedding_gift_card()  {
	    // Write code here that turns the phrase above into concrete actions
		GiftCard gc = new GiftCard(driver);
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
		gc.selectGiftCard();
		String expected = "Wedding Gift Card";
		String actual = gc.getHeadingOfGiftCrad();
		Assert.assertEquals(expected, actual);
	}

	@When("gives sender information with invalid email")
	public void gives_sender_information_with_invalid_email()  {
	    // Write code here that turns the phrase above into concrete actions
		GiftCard gc = new GiftCard(driver);
		try {
			gc.setSenderData();
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
		gc.clickBuyNow();
	}

	@Then("error is captured")
	public void error_is_captured() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		GiftCard gc = new GiftCard(driver);
		String error = gc.getInvalidErrorMessage();
		Thread.sleep(3000);
		Assert.fail(error);
	}
	
	@When("user clicks on hotel weblink")
	public void user_clicks_on_hotel_weblink() {
	    // Write code here that turns the phrase above into concrete actions
		try {
			HotelFunctionality hf = new HotelFunctionality(driver);
			Thread.sleep(10000);
			hf.launchHotelWebsite();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	@Then("user redirects to the hotel page")
	public void user_redirects_to_the_hotel_page() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		HotelFunctionality hf = new HotelFunctionality(driver);
		String expected = "MakeMyTrip.com: Save upto 60% on Hotel Booking 4,442,00+ Hotels Worldwide";
		String actual = driver.getTitle();
		hf.extractAdults();
		Assert.assertEquals(expected, actual);
	}
}
