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
	 * This method will get a particular tab based on tab name passed
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
	
	/**
	 * This method will get a particular checkbox based on name passed
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 * @param checkboxName Name of checkbox to select
	 * @return It will return particular checkbox as {@link WebElement}
	 * 
	 * */
	public WebElement getCheckboxName(WebDriver driver, String checkboxName) {
		return driver.findElement(By.xpath("//span[text()='"+checkboxName+"']/preceding-sibling::input"));
	}
	
	//div[@id='popup']/preceding-sibling::div[1]//*[local-name()='image']
			
			
}