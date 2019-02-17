package steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.MapsynqHome;
import utils.ActionMethods;

/**
 * This Class is used for defining steps related to validations of mapsynq home
 * page. It mainly contains step definition of HomePage.feature file.
 * 
 * @author Nitish
 * 
 */
public class MapsynqHomeSteps {

	static final Logger LOG = Logger.getLogger(MapsynqHomeSteps.class);
	WebDriver driver = SetUp.driver;
	Properties prop = SetUp.properties;
	ActionMethods action = new ActionMethods();
	Actions actions = SetUp.action;
	MapsynqHome homePage = SetUp.homePage;
	Scenario scenario = SetUp.scenario;

	@Given("^open the mapsync page with given URL$")
	public void open_the_mapsync_page_with_given_URL() throws Throwable {
		try {
			String url = prop.getProperty("URL"); // Opening the application URL in opened browser
			LOG.info("URL: " + url);
			driver.get(url);
			action.waitForPageLoad(driver);
			System.out.println("Browser is launched with mapsynq URL");
			LOG.info("Browser is launched with mapsynq URL");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify mapsync page is loaded properly$")
	public void verify_mapsync_page_is_loaded_properly() throws Throwable {
		try {
			action.sync(driver, homePage.mapsynqLogo); // Waiting for Logo to be visible on the page
			LOG.info("mapsynq page is loaded properly");
		} catch (Exception e) {
			LOG.error("mapsynq page is not loaded properly");
			scenario.write("mapsynq page is not loaded properly");
			LOG.error(e);
			throw e;
		}
	}

	@When("^Galactio popup is displayed on the page$")
	public void galactio_popup_is_displayed_on_the_page() throws Throwable {
		try {
			action.sync(driver, homePage.galactioAd);
			action.syncClickable(driver, homePage.galactioAd);
			// waiting for popup to come into view
			action.waitForDesiredValueOfAttribute(driver, homePage.galactioAd, "style", "right: 0px;");
			LOG.info("Galactico Ad popup is displayed");
		} catch (TimeoutException to) {
			// Popup didn't display, continue the execution
		} catch (Exception e) {
			LOG.error("Galactico Ad popup is not displayed");
			scenario.write("Galactico Ad popup is not displayed");
			LOG.error(e);
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
				LOG.info("Clicked on collapse button to collapse Galactio ad banner");
			} else {
				LOG.info("Galactio ad banner is already collapsed");
			}
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify page defaults to \"(.*?)\" tab$")
	public void verify_page_defaults_to_tab(String defaultTabName) throws Throwable {
		try {
			action.sync(driver, homePage.getLeftTabByName(driver, defaultTabName));
			String tabClass = homePage.getLeftTabByName(driver, defaultTabName).getAttribute("class");
			Assert.assertTrue("Page do not defaults to " + defaultTabName + " tab", tabClass.contains("live_tab"));
			LOG.info("Page defaults to " + defaultTabName + " tab\"");
		} catch (AssertionError ae) {
			LOG.error(ae);
			throw new Throwable("Page do not defaults to " + defaultTabName + " tab");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify following links are present on the top right corner of the page$")
	public void verify_following_links_are_present_on_the_top_right_corner_of_the_page(List<String> expectedLinks)
			throws Throwable {
		try {
			List<String> actualList = action.convertWebElementListToStringList(homePage.topRightLinks);
			LOG.info("Actual links: " + actualList);
			/*
			 * Here I am using SoftAssert because it will check all the links and do not
			 * stops the execution if any link is not present It will show the error (if
			 * any) only after checking all the conditions
			 */
			SoftAssert sf = new SoftAssert();
			for (String link : expectedLinks) {
				boolean present = actualList.contains(link);
				sf.assertTrue(present, link + " link is not present in the top right corner of the page");
				if (present) {
					LOG.info(link + " link is present in the top right corner of the page");
				}
			}
			sf.assertAll();
			LOG.info("All links are present");
		} catch (Exception e) {
			LOG.error("All/Some links are not present");
			scenario.write("All/Some links are not present");
			throw e;
		}
	}

	@Then("^verify following tabs are present on the top left corner$")
	public void verify_following_tabs_are_present_on_the_top_left_corner(List<String> expectedTabs) throws Throwable {
		try {
			/*
			 * Here I am using SoftAssert because it will check all the tabs and do not
			 * stops the execution if any tab is not present It will show the error (if any)
			 * only after checking all the conditions
			 */
			SoftAssert sf = new SoftAssert();
			for (String tabName : expectedTabs) {
				boolean present = homePage.getLeftTabByName(driver, tabName).isDisplayed();
				sf.assertTrue(present, tabName + " tab is not present in the top right corner of the page");
				if (present) {
					LOG.info(tabName + " tab is present in the top right corner of the page");
				}
			}
			sf.assertAll();
			LOG.info("All tabs are present");
		} catch (Exception e) {
			LOG.error("All/Some tabs are not present");
			scenario.write("All/Some tabs are not present");
			throw e;
		}
	}

	@Then("^user clicks on \"([^\"]*)\" tab$")
	public void user_clicks_on_tab(String tabName) throws Throwable {
		try {
			action.syncClickable(driver, homePage.getLeftTabByName(driver, tabName));
			actions.moveToElement(homePage.getLeftTabByName(driver, tabName)).click().build().perform();
			LOG.info("Clicked on " + tabName + " tab");
		} catch (Exception e) {
			LOG.error("Unable to click on " + tabName + " tab");
			scenario.write("Unable to click on " + tabName + " tab");
			throw e;
		}
	}

	@Then("^verify following sub tabs are present$")
	public void verify_following_sub_tabs_are_present(List<String> expectedSubTabNames) throws Throwable {
		try {
			/*
			 * Here I am using SoftAssert because it will check all the sub-tabs and do not
			 * stops the execution if any sub-tab is not present It will show the error (if
			 * any) only after checking all the conditions
			 */
			SoftAssert sf = new SoftAssert();
			for (String subTabName : expectedSubTabNames) {
				boolean present = homePage.getLeftTabsSubTabsByName(driver, subTabName).isDisplayed();
				sf.assertTrue(present, subTabName + " sub-tab is not present");
				if (present) {
					LOG.info(subTabName + " sub-tab is present");
				}
			}
			sf.assertAll();
			LOG.info("All sub-tabs are present");
		} catch (Exception e) {
			LOG.error("All/Some sub-tabs are not present");
			scenario.write("All/Some sub-tabs are not present");
			throw e;
		}
	}

	@When("^click on \"([^\"]*)\" sub-tab$")
	public void click_on_sub_tab(String subTabName) throws Throwable {
		try {
			action.syncClickable(driver, homePage.getLeftTabsSubTabsByName(driver, subTabName));
			homePage.getLeftTabsSubTabsByName(driver, subTabName).click();
			LOG.info("Clicked on " + subTabName + " sub-tab");
		} catch (Exception e) {
			LOG.error("Unable to click on " + subTabName + " sub-tab");
			scenario.write("Unable to click on " + subTabName + " sub-tab");
			throw e;
		}
	}

	@Then("^verify incident list is displayed$")
	public void verify_incident_list_is_displayed() throws Throwable {
		try {
			List<WebElement> incidentList = homePage.incidentsListIncidentsSubTabLiveTab;
			Assert.assertTrue("Incident list is not displayed", incidentList.size() > 0);
			LOG.info("Incident list is displayed");
		} catch (AssertionError ae) {
			LOG.error("Incident list is not displayed");
			throw new Throwable("Incident list is not displayed");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify \"([^\"]*)\" textbox is present$")
	public void verify_textbox_is_present(String tbPlaceholder) throws Throwable {
		try {
			Assert.assertTrue(tbPlaceholder + " textbox is not present",
					homePage.getTextboxWithPlaceholderText(driver, tbPlaceholder).isDisplayed());
			LOG.info(tbPlaceholder + " textbox is present");
		} catch (AssertionError ae) {
			LOG.error(tbPlaceholder + " textbox is not present");
			throw new Throwable(tbPlaceholder + " textbox is not present");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify \"([^\"]*)\" dropdown is present$")
	public void verify_dropdown_is_present(String dropdownName) throws Throwable {
		try {
			Assert.assertTrue(dropdownName + " dropdown is not present",
					homePage.getDropdownWithName(driver, dropdownName).isDisplayed());
			LOG.info(dropdownName + " dropdown is present");
		} catch (AssertionError ae) {
			LOG.error(dropdownName + " dropdown is not present");
			throw new Throwable(dropdownName + " dropdown is not present");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^click on \"([^\"]*)\" dropdown$")
	public void click_on_dropdown(String dropdownName) throws Throwable {
		try {
			action.syncClickable(driver, homePage.getDropdownWithName(driver, dropdownName));
			homePage.getDropdownWithName(driver, dropdownName).click();
			LOG.info("Clicked on " + dropdownName + " dropdown");
		} catch (Exception e) {
			LOG.error("Unable to click on " + dropdownName + " dropdown");
			scenario.write("Unable to click on " + dropdownName + " dropdown");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify past (\\d+) dates are present in the \"([^\"]*)\" dropdown$")
	public void verify_past_dates_are_present_in_the_dropdown(int numberOfPastDates, String dropdownName)
			throws Throwable {
		List<String> expectedDates = new ArrayList<String>();
		List<String> actualDates = new ArrayList<String>();
		try {
			WebElement dropdownElement = homePage.getDropdownWithName(driver, dropdownName);
			List<WebElement> dropdownOptionsEl = action.getOptionsFromDropdown(driver, dropdownElement);
			actualDates = action.convertWebElementListToStringList(dropdownOptionsEl);
			LOG.info("Actual dates: " + actualDates);
			// Logic for getting past dates and adding it to a list
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			for (int i = 0; i < numberOfPastDates; i++) {
				cal.add(Calendar.DATE, -1);
				expectedDates.add(df.format(cal.getTime()));
			}
			LOG.info("Expected dates: " + expectedDates);
			Assert.assertTrue("Past " + numberOfPastDates + " dates are not present in the dropdown",
					actualDates.containsAll(expectedDates));
			LOG.info("Past " + numberOfPastDates + " dates are present in the dropdown");
		} catch (AssertionError ae) {
			LOG.error("Past " + numberOfPastDates + " dates are not present in the dropdown");
			LOG.info("Actual dates: " + actualDates +" but expected dates was: "+expectedDates);
			throw new Throwable("Past " + numberOfPastDates + " dates are not present in the dropdown");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify the search functionality in Incident sub-tab$")
	public void verify_the_search_functionality_in_Incident_sub_tab() throws Throwable {
		try {
			List<WebElement> incidentList = homePage.incidentsListIncidentsSubTabLiveTab;
			List<String> incidentDescriptionList = action.convertWebElementListToStringList(incidentList);
			LOG.info("Actual Incident List: " + incidentDescriptionList);
			// Taking random incident from list
			int i = (int) (Math.random() * incidentDescriptionList.size());
			/*
			 * Splitting Incident data (time and description) by new line and taking only
			 * description
			 */
			String searchString = incidentDescriptionList.get(i).trim().split("\\r?\\n")[1];
			LOG.info("Searching for incident: " + searchString);
			homePage.getTextboxWithPlaceholderText(driver, "Search incident location").sendKeys(searchString);
			LOG.info("Searched string is typed in search textbox on Incident sub-tab");
			action.waitForPageLoad(driver);
			// Taking Incident list after searching
			List<WebElement> resultList = new ArrayList<WebElement>();
			int counter = 0;
			do {
				counter++;
				resultList = homePage.incidentsListIncidentsSubTabLiveTab;
				if (counter > 30) {
					Assert.fail("Search functionality is not working properly on Incident sub-tab");
					break;
				}
			} while (resultList.size() > 2);
			List<String> resultDescriptionList = action.convertWebElementListToStringList(incidentList);

			/*
			 * Splitting Incident data (time and description) by new line and taking only
			 * description
			 */
			String actualResultText = resultDescriptionList.get(0).split("\\r?\\n")[1];
			LOG.info(actualResultText);
			Assert.assertTrue(actualResultText.equalsIgnoreCase(searchString));
			LOG.info("Search functionality is working properly on Incident sub-tab");
		} catch (AssertionError ae) {
			LOG.error("Search functionality is not working properly on Incident sub-tab");
			throw new Throwable("Search functionality is not working properly on Incident sub-tab");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify camera list is displayed$")
	public void verify_camera_list_is_displayed() throws Throwable {
		try {
			List<WebElement> camerasList = homePage.cameraListCameraSubTabLiveTab;
			Assert.assertTrue("Camera list is not displayed", camerasList.size() > 0);
			LOG.info("Camera list is displayed");
		} catch (AssertionError ae) {
			LOG.error("Camera list is not displayed");
			throw new Throwable("Camera list is not displayed");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify search functionality on Cameras sub-tab$")
	public void verify_search_functionality_on_Cameras_sub_tab() throws Throwable {
		try {
			List<WebElement> cameraList = homePage.cameraListCameraSubTabLiveTab;
			List<String> cameraDescriptionList = action.convertWebElementListToStringList(cameraList);
			// Taking random camera from list
			int i = (int) (Math.random() * cameraDescriptionList.size());
			String searchString = cameraDescriptionList.get(i).trim();
			LOG.info("Searching for incident: " + searchString);
			homePage.getTextboxWithPlaceholderText(driver, "Search camera location").sendKeys(searchString);
			LOG.info("Searched string is typed in search textbox on Cameras sub-tab");
			action.waitForPageLoad(driver);
			// Taking Cameras list after searching
			List<WebElement> resultList = new ArrayList<WebElement>();
			int counter = 0;
			do {
				counter++;
				resultList = homePage.cameraListCameraSubTabLiveTab;
				if (counter > 30) {
					Assert.fail("Search functionality is not working properly on Cameras sub-tab");
					break;
				}
			} while (resultList.size() > 2);
			List<String> resultDescriptionList = action.convertWebElementListToStringList(cameraList);
			String actualResultText = resultDescriptionList.get(0);
			LOG.info(actualResultText);
			Assert.assertTrue(actualResultText.equalsIgnoreCase(searchString));
			LOG.info("Search functionality is working properly on Cameras sub-tab");
		} catch (AssertionError ae) {
			LOG.error("Search functionality is not working properly on Cameras sub-tab");
			throw new Throwable("Search functionality is not working properly on Cameras sub-tab");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify Tolls list is displayed$")
	public void verify_Tolls_list_is_displayed() throws Throwable {
		try {
			List<WebElement> tollsList = homePage.tollListTollSubTabLiveTab;
			Assert.assertTrue("Tolls list is not displayed", tollsList.size() > 0);
			LOG.info("Tolls list is displayed");
		} catch (AssertionError ae) {
			LOG.error("Tolls list is not displayed");
			throw new Throwable("Tolls list is not displayed");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}

	}

	@Then("^verify search functionality on Tolls tab$")
	public void verify_search_functionality_on_Tolls_tab() throws Throwable {
		try {
			List<WebElement> tollList = homePage.tollListTollSubTabLiveTab;
			List<String> tollDescriptionList = action.convertWebElementListToStringList(tollList);
			LOG.info("Actual toll lsit: " + tollDescriptionList);
			// Taking random Toll from list
			int i = (int) (Math.random() * tollDescriptionList.size());
			String searchString = tollDescriptionList.get(i).trim();
			LOG.info("Searching for incident: " + searchString);
			homePage.getTextboxWithPlaceholderText(driver, "Search gantry location").click();
			homePage.getTextboxWithPlaceholderText(driver, "Search gantry location").sendKeys(searchString);
			LOG.info("Searched string is typed in search textbox on Tolls sub-tab");
			action.waitForPageLoad(driver);
			// Taking Toll list after searching
			List<WebElement> resultList = new ArrayList<WebElement>();
			int counter = 0;
			do {
				counter++;
				resultList = homePage.tollListTollSubTabLiveTab;
				if (counter > 30) {
					Assert.fail("Search functionality is not working properly on Tolls sub-tab");
					break;
				}
			} while (resultList.size() > 2);
			List<String> resultDescriptionList = action.convertWebElementListToStringList(tollList);
			String actualResultText = resultDescriptionList.get(0);
			LOG.info(actualResultText);
			Assert.assertTrue(actualResultText.equalsIgnoreCase(searchString));
			LOG.info("Search functionality is working properly on Tolls sub-tab");
		} catch (AssertionError ae) {
			LOG.error("Search functionality is not working properly on Tolls sub-tab");
			throw new Throwable("Search functionality is not working properly on Tolls sub-tab");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify following buttons are present$")
	public void verify_following_buttons_are_present(List<String> expectedButtonNames) throws Throwable {
		try {
			/*
			 * Here I am using SoftAssert because it will check all the buttons and do not
			 * stops the execution if any buttons is not present It will show the error (if
			 * any) only after checking all the conditions
			 */
			SoftAssert sf = new SoftAssert();
			for (String buttonName : expectedButtonNames) {
				boolean present = homePage.getButtonByName(driver, buttonName).isDisplayed();
				sf.assertTrue(present, buttonName + " buttons is not present");
				if (present) {
					LOG.info(buttonName + " buttons is present");
				}
			}
			sf.assertAll();
			LOG.info("All buttons are present");
		} catch (Exception e) {
			LOG.error("All/Some buttons are not present");
			scenario.write("All/Some buttons are not present");
			throw e;
		}
	}

	@Then("^verify To/Origin and From/Destination textboxes are present$")
	public void verify_To_Origin_and_From_Destination_textboxes_are_present() throws Throwable {
		try {
			action.sync(driver, homePage.fromDirectionTB);
			LOG.info("Origin textbox is present");
		} catch (Exception e) {
			LOG.error("Origin textbox is not present");
			scenario.write("Origin textbox is not present");
			LOG.error(e);
			throw e;
		}

		try {
			action.sync(driver, homePage.toDirectionTB);
			LOG.info("Destination textbox is present");
		} catch (Exception e) {
			LOG.error("Destination textbox is not present");
			scenario.write("Destination textbox is not present");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify following checkboxes are present$")
	public void verify_following_checkboxes_are_present(List<String> expectedCheckboxes) throws Throwable {
		try {
			/*
			 * Here I am using SoftAssert because it will check all the checkboxes and donot
			 * stops the execution if any checkboxes is not present. It will show the error
			 * (if any) only after checking all the conditions
			 */
			SoftAssert sf = new SoftAssert();
			for (String checkboxName : expectedCheckboxes) {
				boolean present = homePage.getCheckboxByName(driver, checkboxName).isDisplayed();
				sf.assertTrue(present, checkboxName + " checkboxes is not present");
				if (present) {
					LOG.info(checkboxName + " checkboxes is present");
				}
			}
			sf.assertAll();
			LOG.info("All checkboxes are present");
		} catch (Exception e) {
			LOG.error("All/Some checkboxes are not present");
			scenario.write("All/Some checkboxes are not present");
			throw e;
		}
	}

	@Then("^verify swap origin destination button is present$")
	public void verify_swap_origin_destination_button_is_present() throws Throwable {
		try {
			action.sync(driver, homePage.swapOriginDestinationBTN);
			LOG.info("Swap origin/destination button is present");
		} catch (Exception e) {
			LOG.error("Swap origin/destination button is not present");
			scenario.write("Swap origin/destination button is not present");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^Type \"([^\"]*)\" in Destination textbox$")
	public void type_in_Destination_textbox(String text) throws Throwable {
		try {
			action.sync(driver, homePage.toDirectionTB);
			homePage.toDirectionTB.sendKeys(text);
			LOG.info(text + " is typed in Destination textbox");
		} catch (Exception e) {
			LOG.error("Destination textbox is not present");
			scenario.write("Destination textbox is not present");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify search auto-complete suggestions are displayed$")
	public void verify_search_auto_complete_suggestions_are_displayed() throws Throwable {
		try {
			action.sync(driver, homePage.autocompleteSuggestions);
			LOG.info("Auto-complete suggestions are displayed");
		} catch (Exception e) {
			LOG.error("Auto-complete suggestion is not displayed");
			scenario.write("Auto-complete suggestion is not displayed");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^select one of the suggestions$")
	public void select_one_of_the_suggestions() throws Throwable {
		try {
			action.sync(driver, homePage.autocompleteSuggestions);
			// Taking all suggestions in auto-complete into a list
			List<WebElement> suggestionElemens = homePage.autocompleteSuggestions.findElements(By.xpath("./div"));
			suggestionElemens.get(0).click();
			LOG.info("One suggestion is selected");
		} catch (Exception e) {
			LOG.error("Error in selecting option");
			scenario.write("Error in selecting option");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify Destination textbox is filled$")
	public void verify_Destination_textbox_is_filled() throws Throwable {
		try {
			action.sync(driver, homePage.toDirectionTB);
			String destinationTBValue = homePage.toDirectionTB.getAttribute("value");
			LOG.info("Actual value of Destination textbox: " + destinationTBValue);
			Assert.assertFalse(destinationTBValue.equalsIgnoreCase("") || destinationTBValue == null);
			LOG.info("Destination textbox is filled");
		} catch (AssertionError ae) {
			LOG.error("Destination textbox is not filled");
			throw new Throwable("Destination textbox is not filled");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify clicking on swap origin destination button swaps origin and destination$")
	public void verify_clicking_on_swap_origin_destination_button_swaps_origin_and_destination() throws Throwable {
		try {
			action.sync(driver, homePage.fromDirectionTB);
			action.sync(driver, homePage.toDirectionTB);
			String originalSource = homePage.fromDirectionTB.getAttribute("value");
			String originalDestination = homePage.toDirectionTB.getAttribute("value");
			LOG.info("Original Source: " + originalSource);
			LOG.info("Original Destination: " + originalDestination);
			homePage.swapOriginDestinationBTN.click();
			/*
			 * If alert appears after clicking on swap button then it will switch to alert
			 * and accept that alert. If Alert is not present, it will do nothing as of now.
			 */
			try {
				action.waitForAlertToAppear(driver);
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (NoAlertPresentException na) {
				/*
				 * Do Nothing in this case Continue with execution without giving any error
				 */
			}
			LOG.info("Clicked on Swap Origin/Destination button");
			action.waitForPageLoad(driver);
			String newSource = homePage.fromDirectionTB.getAttribute("value");
			String newDestination = homePage.toDirectionTB.getAttribute("value");
			LOG.info("New Source: " + originalSource);
			LOG.info("New Destination: " + originalDestination);
			/*
			 * Checking whether new destination is previous source and new source is
			 * previous destination
			 */
			Assert.assertTrue("Origin/Destion is not swapped after clicking on Swap origin/destination button",
					newSource.equalsIgnoreCase(originalDestination) && newDestination.equalsIgnoreCase(originalSource));
			LOG.info("Origin/Destion is swapped after clicking on Swap origin/destination button");
		} catch (AssertionError ae) {
			LOG.error("Origin/Destion is not swapped after clicking on Swap origin/destination button");
			throw new Throwable("Origin/Destion is not swapped after clicking on Swap origin/destination button");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^click on \"([^\"]*)\" button$")
	public void click_on_button(String buttonName) throws Throwable {
		try {
			action.sync(driver, homePage.getButtonByName(driver, buttonName));
			action.syncClickable(driver, homePage.getButtonByName(driver, buttonName));
			homePage.getButtonByName(driver, buttonName).click();
			LOG.info("Clicked on " + buttonName + " button");
		} catch (UnhandledAlertException uh) {
			// Do nothing and continue the execution
			// This Alert will be handled separately
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify Alert is displayed with message as \"([^\"]*)\"$")
	public void verify_Alert_is_displayed_with_message_as(String expectedAlertText) throws Throwable {
		String alertText = "";
		try {
			action.waitForAlertToAppear(driver);
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
			alert.accept();
			Assert.assertTrue("Alert text didn't match", alertText.equalsIgnoreCase(expectedAlertText));
			LOG.info("Alert text match successfully");
		} catch (NoAlertPresentException na) {
			LOG.error("Alert is not present");
			throw new Throwable("Alert is not present");
		} catch (AssertionError ae) {
			LOG.error("Actual alert text: " + alertText + " didn't match expected Alert text: " + expectedAlertText);
			throw new Throwable(
					"Actual alert text: " + alertText + " didn't match expected Alert text: " + expectedAlertText);
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

}
