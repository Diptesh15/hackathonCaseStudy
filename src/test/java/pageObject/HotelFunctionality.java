package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HotelFunctionality extends BasePage {
	public HotelFunctionality(WebDriver driver) {
		super(driver);
	}

//#######################################################################################################
	
	// Finding all the Required WebElements

	@FindBy(xpath="(//a[@href='https://www.makemytrip.com/hotels/'])[1]")
	WebElement website;
	
	@FindBy(xpath="//label[@for='guest']")
	WebElement guestBox;
	
	@FindBy(xpath="(//div[@class='gstSlctCont'])[2]")
	WebElement adultsField;
	
	@FindBy(xpath="//li[@data-cy='GuestSelect$$_323']")
	List<WebElement> adults;
	
//######################################################################################################
	
	// Action Methods on the WebElement
	
	public void launchHotelWebsite() {
		website.click();
	}
	
//------------------------------------------------------------------------------------------------------
	
	public WebElement getGuestBox() {
		return guestBox;
	}
	
//-------------------------------------------------------------------------------------------------------
	
	public WebElement getAdultsField() {
		return adultsField;
	}
	
//------------------------------------------------------------------------------------------------------
	
	public void extractAdults() throws InterruptedException {
		getGuestBox().click();
		Thread.sleep(2000);
		getAdultsField().click();
		Thread.sleep(2000);
		for(WebElement e : adults) {
			System.out.println("You can select " + e.getText() + " Adults ");
		}
	}
	
//------------------------------------------------------------------------------------------------------
}

//######################################################################################################
