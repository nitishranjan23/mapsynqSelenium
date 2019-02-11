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
			action.syncClickable(driver, homePage.galactioAd);
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
	public void verify_following_links_are_present_on_the_top_right_corner_of_the_page(List<String> expectedLinks)
			throws Throwable {
		try {
			List<String> actualList = action.convertWebElementListToStringList(homePage.topRightLinks);
			Log.info("Actual links: " + actualList);
			// Here I am using SoftAssert because it will check all the links and do not
			// stops the execution if any link is not present
			// It will show the error (if any) only after checking all the conditions
			SoftAssert sf = new SoftAssert();
			for (String link : expectedLinks) {
				boolean present = actualList.contains(link);
				sf.assertTrue(present, link + " link is not present in the top right corner of the page");
				if (present) {
					Log.info(link + " link is present in the top right corner of the page");
				}
			}
			sf.assertAll();
			Log.info("All links are present");
		} catch (Exception e) {
			Log.error("All/Some links are not present");
			throw e;
		}
	}

	@Then("^verify following tabs are present on the top left corner$")
	public void verify_following_tabs_are_present_on_the_top_left_corner(List<String> expectedTabs) throws Throwable {
		try {
			// Here I am using SoftAssert because it will check all the tabs and do not
			// stops the execution if any tab is not present
			// It will show the error (if any) only after checking all the conditions
			SoftAssert sf = new SoftAssert();
			for (String tabName : expectedTabs) {
				boolean present = homePage.getLeftTabByName(driver, tabName).isDisplayed();
				sf.assertTrue(present, tabName + " tab is not present in the top right corner of the page");
				if (present) {
					Log.info(tabName + " tab is present in the top right corner of the page");
				}
			}
			sf.assertAll();
			Log.info("All tabs are present");
		} catch (Exception e) {
			Log.error("All/Some tabs are not present");
			throw e;
		}
	}

	@Then("^user clicks on \"([^\"]*)\" tab$")
	public void user_clicks_on_tab(String tabName) throws Throwable {
		try {
			action.syncClickable(driver, homePage.getLeftTabByName(driver, tabName));
			homePage.getLeftTabByName(driver, tabName).click();
			Log.info("Clicked on " + tabName + " tab");
		} catch (Exception e) {
			Log.error("Unable to click on " + tabName + " tab");
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify following sub tabs are present$")
	public void verify_following_sub_tabs_are_present(List<String> expectedSubTabNames) throws Throwable {
		try {
			// Here I am using SoftAssert because it will check all the sub-tabs and do not
			// stops the execution if any sub-tab is not present
			// It will show the error (if any) only after checking all the conditions
			SoftAssert sf = new SoftAssert();
			for (String subTabName : expectedSubTabNames) {
				boolean present = homePage.getLeftTabsSubTabsByName(driver, subTabName).isDisplayed();
				sf.assertTrue(present, subTabName + " sub-tab is not present");
				if (present) {
					Log.info(subTabName + " sub-tab is present");
				}
			}
			sf.assertAll();
			Log.info("All sub-tabs are present");
		} catch (Exception e) {
			Log.error("All/Some sub-tabs are not present");
			throw e;
		}
	}

	@When("^click on \"([^\"]*)\" sub-tab$")
	public void click_on_sub_tab(String subTabName) throws Throwable {
		try {
			action.syncClickable(driver, homePage.getLeftTabsSubTabsByName(driver, subTabName));
			homePage.getLeftTabsSubTabsByName(driver, subTabName).click();
			Log.info("Clicked on " + subTabName + " sub-tab");
		} catch (Exception e) {
			Log.error("Unable to click on " + subTabName + " sub-tab");
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify incident list is displayed$")
	public void verify_incident_list_is_displayed() throws Throwable {
		try {
			List<WebElement> incidentList = homePage.incidentsListIncidentsSubTabLiveTab;
			Assert.assertTrue("Incident list is not displayed", incidentList.size() > 0);
			Log.info("Incident list is displayed");
		} catch (AssertionError ae) {
			Log.error("Incident list is not displayed");
			throw new Throwable("Incident list is not displayed");
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify \"([^\"]*)\" textbox is present$")
	public void verify_textbox_is_present(String tbPlaceholder) throws Throwable {
		try {
			Assert.assertTrue(tbPlaceholder +" textbox is not present", homePage.getTextboxWithPlaceholderText(driver, tbPlaceholder).isDisplayed());
			Log.info(tbPlaceholder +" textbox is present");
		} catch (AssertionError ae) {
			Log.error(tbPlaceholder +" textbox is not present");
			throw new Throwable(tbPlaceholder +" textbox is not present");
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify search functionality on Incidents tab$")
	public void verify_search_functionality_on_Incidents_tab() throws Throwable {


	}

	@Then("^verify \"([^\"]*)\" dropdown is present$")
	public void verify_dropdown_is_present(String arg1) throws Throwable {


	}

	@Then("^click on \"([^\"]*)\" dropdown$")
	public void click_on_dropdown(String arg1) throws Throwable {


	}

	@Then("^verify past (\\d+) dates are present in the \"([^\"]*)\" dropdown$")
	public void verify_past_dates_are_present_in_the_dropdown(int arg1, String arg2) throws Throwable {


	}

	@Then("^verify the search functionality in Incident sub-tab$")
	public void verify_the_search_functionality_in_Incident_sub_tab() throws Throwable {


	}

	@Then("^verify camera list is displayed$")
	public void verify_camera_list_is_displayed() throws Throwable {


	}

	@Then("^verify search functionality on Cameras tab$")
	public void verify_search_functionality_on_Cameras_tab() throws Throwable {

	}

	@Then("^verify Tolls list is displayed$")
	public void verify_Tolls_list_is_displayed() throws Throwable {


	}

	@Then("^verify search functionality on Tolls tab$")
	public void verify_search_functionality_on_Tolls_tab() throws Throwable {


	}

}
