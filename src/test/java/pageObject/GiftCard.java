package pageObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtils;

public class GiftCard extends BasePage{

	public GiftCard(WebDriver driver) {
		super(driver);
	}

//####################################################################################################
	
	//Finding all the WebElements required
	
	String file = System.getProperty("user.dir") + "\\testData\\HackathonInputData.xlsx";

	@FindBy(xpath="//li[@data-cy='tertiaryRowItem_Gift Cards']")
	WebElement giftCardIcon;
	
	@FindBy(xpath="//p[normalize-space()='All']")
	WebElement allRadioButton;
	
	@FindBy(xpath="//a[normalize-space()='Clear']")
	WebElement clearCategory;
	
	@FindBy(xpath="//img[@alt='banner']")
	WebElement weddingGiftCard;
	
	@FindBy(xpath="//h1[normalize-space()='Wedding Gift Card']")
	WebElement headingWGC;
	
	@FindBy(xpath="//input[@name='senderName']")
	WebElement senderName;
	
	@FindBy(xpath="//input[@name='senderMobileNo']")
	WebElement senderMobile;
	
	@FindBy(xpath="//input[@name='senderEmailId']")
	WebElement senderMail;
	
	@FindBy(xpath="//p[@class='red-text font11 append-top5']")
	WebElement errorMessage;
	
	@FindBy(xpath="//button[normalize-space()='BUY NOW']")
	WebElement buyNow;
	
	@FindBy(xpath="//div[@id='deliveredSection']")
	WebElement errorArea;
	
//#####################################################################################################
	
	// Action Methods on the WebElements
	
	public void launchGiftCard() throws InterruptedException {
		giftCardIcon.click();
		Set<String> windows = driver.getWindowHandles();
		List<String> ids = new ArrayList<String>(windows);
		
		String giftCardPage = ids.get(1);
		
		driver.switchTo().window(giftCardPage);
	}

//-----------------------------------------------------------------------------------------------------
	
	public WebElement getAllRadioButton() {
		return allRadioButton;
	}
	
//-----------------------------------------------------------------------------------------------------
	
	public void clearAllCategories() {
		clearCategory.click();
	}

//----------------------------------------------------------------------------------------------------
	
	public void selectGiftCard() {
		weddingGiftCard.click();
	}

//----------------------------------------------------------------------------------------------------
	
	public String getHeadingOfGiftCrad() {
		return headingWGC.getText();
	}

//-----------------------------------------------------------------------------------------------------
	
	public void setSenderData() throws IOException {
		String name = ExcelUtils.getCellData(file, "sheet1", 1, 3);
		senderName.sendKeys(name);
		String number = ExcelUtils.getCellData(file, "sheet1", 1, 4);
		senderMobile.sendKeys(number);
		String mail = ExcelUtils.getCellData(file, "sheet1", 1, 5);
		senderMail.sendKeys(mail);
		
	}
	
//----------------------------------------------------------------------------------------------------
	
	public void clickBuyNow() {
		buyNow.click();
	}
	
//----------------------------------------------------------------------------------------------------
	
	public String getInvalidErrorMessage() {
		return errorMessage.getText();
	}

//-----------------------------------------------------------------------------------------------------
}

//#####################################################################################################