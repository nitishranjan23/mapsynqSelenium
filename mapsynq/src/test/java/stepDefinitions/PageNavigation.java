package stepDefinitions;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.GoogleMap;
import pageObject.MapsynqHome;
import pageObject.NavigationPages;
import reusableMethods.ActionMethods;
import utils.Setup;

public class PageNavigation {

	private static final Logger LOG = Logger.getLogger(PageNavigation.class);
	WebDriver driver = Setup.driver;
	Properties prop = Setup.properties;
	ActionMethods action = new ActionMethods();
	Actions actions = Setup.action;
	MapsynqHome homePage = Setup.homePage;
	GoogleMap googleMap = Setup.googleMapPage;
	NavigationPages navigationPage = Setup.navigationPage;
	
	
	@When("^user clicks on \"([^\"]*)\" link$")
	public void user_clicks_on_link(String linkName) throws Throwable {
	     try {
	    	 action.waitForPageLoad(driver);
	    	 List<WebElement> linkList = homePage.topRightLinks;
	    	 int count = 0;
	    	 while (linkList.size()<1) {
	    		 linkList = homePage.topRightLinks;
	    		 if (count >30) {
	    			 break;
	    		 }
	    	 }
	    	 for (WebElement link: homePage.topRightLinks) {
	    		 if (link.getText().trim().equalsIgnoreCase(linkName)) {
	    			 link.click();
	    			 LOG.info("Clicked on "+link + " link");
	    			 break;
	    		 }
	    	 }
	     }catch (Exception e) {
	    	 LOG.error(e);
	    	 throw e;
		}
	}

	@Then("^verify Sign in page is opened$")
	public void verify_Sign_in_page_is_opened() throws Throwable {
		try {
			action.sync(driver, navigationPage.signInContainer);
			LOG.info("Sign in page is opened successfully");
		} catch (Exception e) {
			LOG.error("Sign in page didn't open");
			throw e;
		}
	}

	@Then("^verify \"([^\"]*)\" page is opened$")
	public void verify_page_is_opened(String pageName) throws Throwable {
		try {
			action.sync(driver, navigationPage.getPageWithHeading(driver, pageName));
			LOG.info(pageName +" page is opened successfully");
		} catch (Exception e) {
			LOG.error(pageName +" page didn't open");
			throw e;
		}
	}

	@Then("^verify mapsynq mobile information page is opened in new browser tab$")
	public void verify_mapsynq_mobile_information_page_is_opened_in_new_browser_tab() throws Throwable {
		try {
			for(String handle: driver.getWindowHandles()) {
				driver.switchTo().window(handle);
			}
			action.waitForPageLoad(driver);
			Assert.assertTrue("Mapsynq mobile information page didn't open in new browser tab",driver.getTitle().trim().equalsIgnoreCase("mapSYNQ Mobile") && driver.getWindowHandles().size()>1);
			LOG.info("Mapsynq mobile information page opened successfully in new browser tab");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify Galactio page is opened in new browser tab$")
	public void verify_Galactio_page_is_opened_in_new_browser_tab() throws Throwable {
		try {
			for(String handle: driver.getWindowHandles()) {
				driver.switchTo().window(handle);
			}
			action.waitForPageLoad(driver);
			Assert.assertTrue("Galactio page didn't open in new browser tab",driver.getTitle().trim().equalsIgnoreCase("Galactio") && driver.getWindowHandles().size()>1);
			LOG.info("Galactio page opened successfully in new browser tab");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	     
	}

	@Then("^verify Google Play page is opened in new browser tab$")
	public void verify_Google_Play_page_is_opened_in_new_browser_tab() throws Throwable {
		try {
			for(String handle: driver.getWindowHandles()) {
				driver.switchTo().window(handle);
			}
			action.waitForPageLoad(driver);
			Assert.assertTrue("Google Play page didn't open in new browser tab",driver.getWindowHandles().size()>1);
			action.sync(driver, navigationPage.googlePlayLogo);
			LOG.info("Google Play page is opened in new browser tab");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify \"([^\"]*)\" popup is displayed$")
	public void verify_popup_is_displayed(String title) throws Throwable {
	     try {
	    	 action.sync(driver, googleMap.getPopupByTitle(driver, title));
	    	 LOG.info(title + " popup is diaplayed");
	     } catch (Exception e) {
			LOG.error(title + " popup is not displayed");
			LOG.error(e);
			throw e;
		}
	}


}
