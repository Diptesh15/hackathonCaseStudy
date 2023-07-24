package pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtils;


public class CabBooking extends BasePage {
	
	public CabBooking(WebDriver driver) {
		super(driver);
	}
	
	
//###########################################################################################################
	
	// Setting path for reading Excel File
	
	String file = System.getProperty("user.dir") + "\\testData\\HackathonInputData.xlsx";
	
	// Identifying all the required webelements
	
	@FindBy(xpath="//a[@href='https://www.makemytrip.com/cabs/']")
	WebElement website;
	
	@FindBy(xpath="//label[@for='fromCity']")
	WebElement labelForCity;
	
	@FindBy(xpath="//label[@for='toCity']")
	WebElement labelToCity;
	
	@FindBy(xpath="//input[@placeholder='From']")
	WebElement searchFrom;
	
	@FindBy(xpath="//input[@placeholder='To']")
	WebElement searchTo;
	
	@FindBy(xpath="(//p[@class='searchedResult font14 darkText'])[1]")
	WebElement suggestion;
	
	@FindBy(xpath="//div[@aria-label='Wed Aug 02 2023']")
	WebElement departure;
	
	@FindBy(xpath="(//li[@class='hrSlotItemParent'])[7]")
	WebElement pickupTimeHr;
	
	@FindBy(xpath="(//li[@class='minSlotItemParent'])[6]")
	WebElement pickupTimeMin;
	
	@FindBy(xpath="//span[@class='applyBtnText']")
	WebElement pickupTimeApply;
	
	@FindBy(xpath="//a[normalize-space()='Search']")
	WebElement searchButton;
	
	@FindBy(xpath="//label[normalize-space()='SUV']")
	WebElement checkSuv;
	
	@FindBy(xpath="//div[@class='appliedFilterButton']")
	WebElement appliedFilter;
	
	@FindBy(xpath="//p[@class='font28 latoBlack blackText ']")
	List<WebElement> lowestPrice;
	
//###################################################################################################
	
	// Action Methods for the WebElement Identified
	
	public void launchCabPage() {
		setElementBorder(website);
		website.click();
	}
	
//-----------------------------------------------------------------------------------------------------
	
	public void fillCabData() throws InterruptedException, IOException {
		
		setElementBorder(labelForCity);
		labelForCity.click();
		
		String from = ExcelUtils.getCellData(file, "sheet1", 1, 1);
		setElementBorder(searchFrom);
		searchFrom.sendKeys(from);
		Thread.sleep(3000);
		setElementBorder(suggestion);
		suggestion.click();
		
		driver.switchTo().activeElement();
		
		String to = ExcelUtils.getCellData(file, "sheet1", 1, 2);
		setElementBorder(searchTo);
		searchTo.sendKeys(to);
		Thread.sleep(3000);
		setElementBorder(suggestion);
		suggestion.click();
		
		driver.switchTo().activeElement();
		
		setElementBorder(departure);
		departure.click();
		
		driver.switchTo().activeElement();
		
		setElementBorder(pickupTimeHr);
		pickupTimeHr.click();
		setElementBorder(pickupTimeMin);
		pickupTimeMin.click();
		setElementBorder(pickupTimeApply);
		pickupTimeApply.click();
		setElementBorder(searchButton);
		searchButton.click();
	}

//--------------------------------------------------------------------------------------------------
	
	public WebElement getSuv() {
		setElementBorder(checkSuv);
		return checkSuv;
	}
//-----------------------------------------------------------------------------------------------------
	public WebElement getAppliedFilter() {
		setElementBorder(appliedFilter);
		return appliedFilter;
	}

//-----------------------------------------------------------------------------------------------------
	
	public List<WebElement> getLowestPrice() {
		return lowestPrice;
	}
}

//################################################################################################
