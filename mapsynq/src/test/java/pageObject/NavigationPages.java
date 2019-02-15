package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * This class contains {@link WebElement} or locators of other pages 
 * which is reached after clicking on any link
 * 
 * @author Nitish
 * */
public class NavigationPages {

	@FindBy(how = How.CSS, using = "signin_wrapper")
	public WebElement signInContainer;
	
	public WebElement getPageWithHeading(WebDriver driver, String heading) {
		return driver.findElement(By.xpath("//h5[text() = '"+heading+"']"));
	}
	
	@FindBy(how = How.CSS, using = "a[title = 'Google Play Logo']")
	public WebElement googlePlayLogo;
			
}
