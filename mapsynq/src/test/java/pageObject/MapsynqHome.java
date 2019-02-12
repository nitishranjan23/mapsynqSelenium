package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.Setup;


/**
 * This is page object class for mapsynq home page.
 * 
 * Most {@link WebElement}s in this class is find by using {@link FindBy} 
 * which uses {@link PageFactory} which is defined in {@link Setup} class using
 * PageFactory.initElements
 * 
 * @author Nitish
 * */
public class MapsynqHome {

	@FindBy(how = How.CSS, using = "a.header_logo")
	public WebElement mapsynqLogo;
	
	@FindBy(how = How.ID, using = "galactio_ad")
	public WebElement galactioAd; //Galactico Ad banner
	
	@FindBy(how = How.ID, using = "ad_toggle")
	public WebElement galactioAdToggleBTN; //Button to expand or collapse Galactico Ad banner
	
	@FindBy(how = How.CSS, using = "div.account_bar_wrapper a")
	public List<WebElement> topRightLinks; //All links present on top right corner
	
	/**
	 * This method will get a particular tab based on tab name passed
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 * @param tabName Name of tab to select
	 * @return It will return particular tab as {@link WebElement}
	 * 
	 * */
	public WebElement getLeftTabByName(WebDriver driver, String tabName) {
		return driver.findElement(By.cssSelector("div.left_tab a[onclick *= '"+tabName+"']"));
	}
	
	/**
	 * This method will get a particular sub-tab based on its name passed in parameter
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 * @param subTabName Name of sub-tab to select
	 * @return It will return particular sub-tab as {@link WebElement}
	 * 
	 * */
	public WebElement getLeftTabsSubTabsByName(WebDriver driver, String subTabName) {
		return driver.findElement(By.xpath("//span[.='"+subTabName+"']/parent::label"));
	}
	
	//@FindBy(how = How.CSS, using = "ul.incident div.item_content") //This locator is not working for hidden element
	@FindBy(how = How.XPATH, using = "//div[contains(@id, 'BrowseIncident')]/div[1]//li[not(contains(@style,'display: none;'))]//div[@class = 'item_content']")
	public List<WebElement> incidentsListIncidentsSubTabLiveTab;
	
	/**
	 * This method will get a particular Text box based on Placeholder text passed
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 * @param tbPlaceholder Placeholder text in the text box to select 
	 * @return It will return particular Text box as {@link WebElement}
	 * 
	 * */
	public WebElement getTextboxWithPlaceholderText(WebDriver driver, String tbPlaceholder) {
		return driver.findElement(By.cssSelector("input[placeholder='"+tbPlaceholder+"']"));
	}
	
	/**
	 * This method will get a particular Dropdown based on name passed
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 * @param dropdownName Name of Dropdown to select
	 * @return It will return particular Dropdown as {@link WebElement}
	 * 
	 * */
	public WebElement getDropdownWithName(WebDriver driver, String dropdownName) {
		return driver.findElement(By.xpath("//span[contains(normalize-space(), '"+dropdownName+"')]/select"));
	}
	
	//@FindBy(how = How.CSS, using = "ul[id *='camera_location'] a") //This locator is not working for hidden element
	@FindBy(how = How.XPATH, using = "//div[contains(@id, 'BrowseCamera')]/div[1]//div[@class = 'camera_list' and not(contains(@style,'display: none;'))]")
	public List<WebElement> cameraListCameraSubTabLiveTab;
	
	//@FindBy(how = How.CSS, using = "ul[id *='erp_location'] a") //This locator is not working for hidden element
	@FindBy(how = How.XPATH, using = "//div[contains(@id, 'ERP')]/div[1]//div[@class = 'erp_list' and not(contains(@style,'display: none;'))]")
	public List<WebElement> tollListTollSubTabLiveTab;
	
	/**
	 * This method will get a particular button based on button name passed
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 * @param buttonName Name of button to select
	 * @return It will return particular button as {@link WebElement}
	 * 
	 * */
	public WebElement getButtonByName(WebDriver driver, String buttonName) {
		return driver.findElement(By.cssSelector("input[type = 'button'][value ='"+buttonName+"']"));
	}
	
	@FindBy(how = How.CSS, using = "input[name *= 'from']")
	public WebElement fromDirectionTB; //From Textbox of directions tab
	
	@FindBy(how = How.CSS, using = "input[name *= 'to']")
	public WebElement toDirectionTB; //To Textbox of directions tab
	
	@FindBy(how = How.CSS, using = "input[type = 'button'][title ='Swap origin/destination']")
	public WebElement swapOriginDestinationBTN; //Curved Arrow button to swap origin and destination 
	
	@FindBy(how = How.XPATH, using = "//div[@class = 'autocomplete' and not(contains(@style, 'display: none;'))]")
	public WebElement autocompleteSuggestions;
	
	/**
	 * This method will get a particular checkbox based on name passed
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 * @param checkboxName Name of checkbox to select
	 * @return It will return particular checkbox as {@link WebElement}
	 * 
	 * */
	public WebElement getCheckboxByName(WebDriver driver, String checkboxName) {
		return driver.findElement(By.xpath("//span[contains(normalize-space(),'"+checkboxName+"')]/preceding-sibling::input"));
	}
	
	
	
	
	
	//div[@id='popup']/preceding-sibling::div[1]//*[local-name()='image']
			
	
}
