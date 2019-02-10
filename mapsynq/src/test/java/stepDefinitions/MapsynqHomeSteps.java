package stepDefinitions;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.MapsynqHome;
import reusableMethods.ActionMethods;
import utils.Setup;

public class MapsynqHomeSteps {

	static Logger Log = Logger.getLogger(MapsynqHomeSteps.class);
	WebDriver driver = Setup.driver;
	Properties prop = Setup.properties;
	ActionMethods action = new ActionMethods();
	MapsynqHome homePage = Setup.homePage;

	@Given("^open the mapsync page with given URL$")
	public void open_the_mapsync_page_with_given_URL() throws Throwable {
		try {
			String url = prop.getProperty("URL");
			Log.info("URL: " + url);
			driver.get(url);
			action.waitForPageLoad(driver);
			System.out.println("Browser is launched with mapsynq URL");
			Log.info("Browser is launched with mapsynq URL");
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify mapsync page is loaded properly$")
	public void verify_mapsync_page_is_loaded_properly() throws Throwable {
		try {
			action.sync(driver, homePage.mapsynqLogo); // Waiting for Logo to be visible on the page
			Log.info("mapsynq page is loaded properly");
		} catch (Exception e) {
			Log.error("mapsynq page is not loaded properly");
			Log.error(e);
			throw e;
		}
	}

	@When("^Galactio popup is displayed on the page$")
	public void galactio_popup_is_displayed_on_the_page() throws Throwable {
		try {
			action.sync(driver, homePage.galactioAd);
			
			Log.info("Galactico Ad popup is displayed");
		} catch (Exception e) {
			Log.error("Galactico Ad popup is not displayed");
			Log.error(e);
			throw e;
		}

	}

	@Then("^close the Galactio popup$")
	public void close_the_Galactio_popup() throws Throwable {
		try {
			action.sync(driver, homePage.galactioAdToggleBTN);
			String btnClass = homePage.galactioAdToggleBTN.getAttribute("class");
			if (btnClass.contains("collapse")) {
				homePage.galactioAdToggleBTN.click();
				Log.info("Clicked on collapse button to collapse Galactio ad banner");
			} else {
				Log.info("Galactio ad banner is already collapsed");
			}
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify page defaults to \"(.*?)\" tab$")
	public void verify_page_defaults_to_tab(String defaultTabName) throws Throwable {
		try {
			action.sync(driver, homePage.getLeftTabByName(driver, defaultTabName));
			String tabClass = homePage.getLeftTabByName(driver, defaultTabName).getAttribute("class");
			Assert.assertTrue("Page do not defaults to " + defaultTabName + " tab", tabClass.contains("live_tab"));
			Log.info("Page defaults to " + defaultTabName + " tab\"");
		} catch (AssertionError ae) {
			throw new Throwable("Page do not defaults to " + defaultTabName + " tab");
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}

	}

	@Then("^verify following links are present on the top right corner of the page$")
	public void verify_following_links_are_present_on_the_top_right_corner_of_the_page(List<String> expectedLinks) throws Throwable {
		try {
			List<String> actualList = action.convertWebElementListToStringList(homePage.topRightLinks);
			SoftAssert sf = new SoftAssert();
			for (String link : expectedLinks) {
				boolean present = actualList.contains(link);
				sf.assertTrue(present, link+ " link is not present in the top right corner of the page");
				if(present) {
					Log.info(link+ " link is present in the top right corner of the page");
				}
			}
			sf.assertAll();
			Log.info("All links are present");
		}catch (Exception e) {
			Log.error("All/Some links are not present");
			throw e;
		}
	}

	
}
