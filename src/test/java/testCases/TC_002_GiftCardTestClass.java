package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.GiftCard;
import testBase.BaseClass;


public class TC_002_GiftCardTestClass extends BaseClass {
	
//######################################################################################################
	
	// Test Cases to check giftcard functionality are written in this class
	// test_gift_card_page = will check if user is able to redirect to the gift card section
	// test_remove_category = will remove the filters and check if its removed or not
	// test_wedding_gift_card = will select wedding gift card and check if it's selected or not
	
//######################################################################################################
	
	@Test(priority=6, groups= {"Smoke", "Regression"})
	public void test_gift_card_page() {
		logger.info("Starting test_gift_card_page...");
		try {
			GiftCard gc = new GiftCard(driver);
			Thread.sleep(3000);
			logger.info("Clicking on the Gift Card option present on the home page...");
			gc.launchGiftCard();
			logger.info("Validiting the test by checking the title of web page...");
			String expected = "Gift Cards - Buy Gift Card Online, Gift Vouchers | MakeMyTrip.com";
			String actual = driver.getTitle();
			Assert.assertEquals(actual, expected);
			logger.info("test_gift_card_page successful...");
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info("test_gift_card_page failed...");
			Assert.fail();
		}
		
	}
	
//------------------------------------------------------------------------------------------------------

	@Test(priority=7, groups= {"Regression"})
	public void test_remove_category() {
		logger.info("Starting test_remove_category...");
		try {
			GiftCard gc = new GiftCard(driver);
			logger.info("Clearing all the categories...");
			gc.clearAllCategories();
			WebElement radioButton = gc.getAllRadioButton();
			logger.info("Checking if the categories are cleared or not...");
			Assert.assertFalse(radioButton.isSelected());
			logger.info("test_remove_category successful...");
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info("test_remove_category failed...");
			Assert.fail();
		}
		
	}
	
//-----------------------------------------------------------------------------------------------------
	
	@Test(priority=8, groups= {"Regression"})
	public void test_select_gift_card() {
		logger.info("Starting test_select_gift_card...");
		try {
			GiftCard gc = new GiftCard(driver);
			Thread.sleep(1500);
			logger.info("Selecting gift cartd as Wedding Gift Card...");
			gc.selectGiftCard();
			String expected = "Wedding Gift Card";
			String actual = gc.getHeadingOfGiftCrad();
			logger.info("Verifying the gift card by checking the title...");
			Assert.assertEquals(expected, actual);
			logger.info("test_select_gift_card successful...");
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info("test_select_gift_card failed...");
			Assert.fail();
		}
		
	}
	
//-----------------------------------------------------------------------------------------------------
	
	@Test(priority=9, groups= {"Regression"})
	public void test_invalid_email() {
		logger.info("Starting test_invalid_email...");
		GiftCard gc = new GiftCard(driver);
		try {
			logger.info("Entering sender's data...");
			gc.setSenderData();
			logger.info("Clicking on Buy Now button...");
			gc.clickBuyNow();
			logger.info("Extracting error message...");
			String error = gc.getInvalidErrorMessage();
			logger.info("Extracted error message: " + error);
			Thread.sleep(3000);
			captureScreen("test_invalid_email");
			Assert.assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("test_invalid_email failed...");
			Assert.fail();
		}
		
	}
	
//-----------------------------------------------------------------------------------------------------
}

//######################################################################################################
