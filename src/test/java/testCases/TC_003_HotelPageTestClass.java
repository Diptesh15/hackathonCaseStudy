package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HotelFunctionality;
import testBase.BaseClass;

public class TC_003_HotelPageTestClass extends BaseClass{

//######################################################################################################
	
	// Test Cases to check hotel page are written in this class
	// test_hotel_page_functionality = will launch the hotel weblink and extract the adult's count
	
//######################################################################################################
	
	@Test(priority=4, groups= {"Smoke", "Regression"})
	public void test_hotel_page_functionality() {
		try {
			HotelFunctionality hf = new HotelFunctionality(driver);
			Thread.sleep(3000);
			hf.launchHotelWebsite();
			String expected = "MakeMyTrip.com: Save upto 60% on Hotel Booking 4,442,00+ Hotels Worldwide";
			String actual = driver.getTitle();
			hf.extractAdults();
			Assert.assertEquals(expected, actual);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
//######################################################################################################
}
