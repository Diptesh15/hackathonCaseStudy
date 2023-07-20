package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.CabBooking;
import testBase.BaseClass;

public class TC_001_CabPageTestClass extends BaseClass {

//#######################################################################################################
	
	// Test Cases to check cab page are written in this class
	// test_redirect_to_cabs = will check if user is able to redirect or not
	// test_cab_data_fill = will check if after giving proper details user is able to see results or not
	// test_car_type_suv = will check if proper filter is set to the result or not (Filter: SUV)
	
//#######################################################################################################
	
	@Test(priority=1, groups= {"Smoke", "Regression"})
	public void test_redirect_to_cabs() {
		logger.info("Starting test_redirect_to_cabs...");
		try {
			logger.info("Creating object for class CabBooking.java present in pageObject package...");
			CabBooking cb = new CabBooking(driver);
			Thread.sleep(3000);
			logger.info("Clicking on the Cab page present in website...");
			cb.launchCabPage();
			Thread.sleep(3000);
			String expected = "Online Cab Booking - Book Outstation Cabs at Lowest Fare @ MakeMyTrip";
			String actual = driver.getTitle();
			logger.info("Checking if correct Cab page is opened or not by verifying web-page title...");
			Assert.assertEquals(expected, actual);
			logger.info("test_redirect_to_cabs successful...");
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info("test_redirect_to_cabs failed...");
			Assert.fail();
		}
		
	}
	
//------------------------------------------------------------------------------------------------------
	
	@Test(priority=2, groups= {"Regression"})
	public void test_cab_data_fill() {
		logger.info("Starting test_cab_data_fill...");
		try {
			CabBooking cb = new CabBooking(driver);
			logger.info("Filling the cab data...");
			cb.fillCabData();
			String expected = "Cabs";
			String actual = driver.getTitle();
			Assert.assertEquals(expected, actual);
			logger.info("test_cab_data_fill successful...");
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info("test_cab_data_fill failed...");
			Assert.fail();
		}
		
	}
	
//------------------------------------------------------------------------------------------------------
	
	@Test(priority=3, groups= {"Regression"})
	public void test_car_type_suv() {
		logger.info("Starting test_car_type_suv...");
		try {
			CabBooking cb = new CabBooking(driver);
			logger.info("Selecting the cab type as SUV...");
			cb.getSuv().click();
			Thread.sleep(3000);
			String dummy = cb.getAppliedFilter().getText();
			Assert.assertEquals("SUV", dummy);
			logger.info("test_car_type_suv successful...");
			logger.info("Printing the lowest fare in console...");
			String pr = cb.getLowestPrice().get(0).getText();
			logger.info("Lowest fare is: Rs. " + pr);
			Thread.sleep(3000);
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info("test_car_type_suv failed...");
			Assert.fail();
		}
		
	}
	
}

//#####################################################################################################
