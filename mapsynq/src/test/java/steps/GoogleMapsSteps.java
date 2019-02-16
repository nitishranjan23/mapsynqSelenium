package steps;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import cucumber.api.Scenario;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.GoogleMap;
import pageobjects.MapsynqHome;
import utils.ActionMethods;

/**
 * This Class is used for defining steps related to validations of interactions
 * on Google Map. It mainly contains step definition of
 * GoogleMapRelatedFunctionality.feature file.
 * 
 * @author Nitish
 */
public class GoogleMapsSteps {

	private static final Logger LOG = Logger.getLogger(GoogleMapsSteps.class);
	WebDriver driver = SetUp.driver;
	Properties prop = SetUp.properties;
	ActionMethods action = new ActionMethods();
	Actions actions = SetUp.action;
	MapsynqHome homePage = SetUp.homePage;
	GoogleMap googleMap = SetUp.googleMapPage;
	Scenario scenario = SetUp.scenario;

	private String defaultStyle;

	@When("^Google map is loaded$")
	public void google_map_is_loaded() throws Throwable {
		try {
			// Here we will be waiting for page loading status to be complete
			action.waitForPageLoad(driver);
			LOG.info("Google Map is loaded");
		} catch (Exception e) {
			LOG.error("Google Map is not loaded properly");
			scenario.write("Google Map is not loaded properly");
			throw e;
		}
	}

	@Then("^verify panel buttons are present on top of the map to show following$")
	public void verify_panel_buttons_are_present_on_top_of_the_map_to_show_following(List<String> pannelButtons)
			throws Throwable {
		try {
			/*
			 * Here I am using SoftAssert because it will check all the links and do not
			 * stops the execution if any link is not present It will show the error (if
			 * any) only after checking all the conditions
			 */
			SoftAssert sf = new SoftAssert();
			for (String buttonName : pannelButtons) {
				boolean present = googleMap.getTopPannelButtons(driver, buttonName).isDisplayed();
				sf.assertTrue(present, buttonName + " button is not present on top of the map");
				if (present) {
					LOG.info(buttonName + " button is not present on top of the map");
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

	@Then("^click on \"([^\"]*)\" button to make it active$")
	public void click_on_button_to_make_it_active(String buttonName) throws Throwable {
		try {
			String buttonClass = googleMap.getTopPannelButtons(driver, buttonName).getAttribute("class");
			if (!buttonClass.contains("Active")) {
				action.syncClickable(driver, googleMap.getTopPannelButtons(driver, buttonName));
				googleMap.getTopPannelButtons(driver, buttonName).click();
				LOG.info("Clicked on " + buttonName + " panel button");
			} else {
				LOG.info(buttonName + " panel button is already active");
			}
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify incidents are marked on the map$")
	public void verify_incidents_are_marked_on_the_map() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			List<WebElement> incidentsOnMap = googleMap.incidentsOnMap;
			Assert.assertTrue("Incidents are not marked on the map", incidentsOnMap.size() > 0);
			LOG.info("Incidents are marked on the map");
		} catch (AssertionError ae) {
			LOG.error("Incidents are not marked on the map");
			throw new Throwable("Incidents are not marked on the map");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify Live Traffic information is displayed on the map$")
	public void verify_Live_Traffic_information_is_displayed_on_the_map() throws Throwable {
		try {
			Assert.assertTrue("Live Traffic information is not displayed", googleMap.liveTrafficLegend.isDisplayed());
			LOG.info("Live Traffic information is displayed");
		} catch (AssertionError ae) {
			LOG.error("Live Traffic information is not displayed");
			throw new Throwable("Live Traffic information is not displayed");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify following direction slider buttons are present on the map$")
	public void verify_following_direction_slider_buttons_are_present_on_the_map(List<String> directionSliderButtons)
			throws Throwable {
		try {
			SoftAssert sf = new SoftAssert();
			for (String button : directionSliderButtons) {
				sf.assertTrue(googleMap.getLeftRightUpDownButtonOnMap(driver, button).isDisplayed(),
						button + " direction slider button is not present on the map");
				LOG.info(button + " direction slider button is present on the map");
			}
			sf.assertAll();
			LOG.info("All direction slider buttons are present on the map");
		} catch (AssertionError ae) {
			LOG.error("All/Some direction slider button is not present on the map");
			throw new Throwable("All/Some direction slider button is not present on the map");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify \"([^\"]*)\" and \"([^\"]*)\" buttons are present on the map$")
	public void verify_and_buttons_are_present_on_the_map(String buttonName1, String buttonName2) throws Throwable {
		// verification for first button
		try {
			Assert.assertTrue(buttonName1 + " button is not present on the map",
					googleMap.getZoomInZoomOutButtonOnZoomSliderOnMap(driver, buttonName1).isDisplayed());
			LOG.info(buttonName1 + " button is present on the map");
		} catch (Exception e) {
			/*
			 * Here both of Exception and Assertion Error denote that object is not present
			 * so not catching both the exception separately.
			 */
			LOG.error(buttonName1 + " button is not present on the map");
			scenario.write(buttonName1 + " button is not present on the map");
			LOG.error(e);
			throw e;
		}

		// verification for second button
		try {
			Assert.assertTrue(buttonName2 + " button is not present on the map",
					googleMap.getZoomInZoomOutButtonOnZoomSliderOnMap(driver, buttonName2).isDisplayed());
			LOG.info(buttonName2 + " button is present on the map");
		} catch (Exception e) {
			/*
			 * Here both of Exception and Assertion Error denote that object is not present
			 * so not catching both the exception separately.
			 */
			LOG.error(buttonName2 + " button is not present on the map");
			scenario.write(buttonName2 + " button is not present on the map");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify zoombar is present on the map$")
	public void verify_zoombar_is_present_on_the_map() throws Throwable {
		try {
			Assert.assertTrue("Zoombar is not present on the map", googleMap.zoomBarOnMap.isDisplayed());
			LOG.info("Zoombar is present on the map");
		} catch (Exception e) {
			/*
			 * Here both of Exception and Assertion Error denote that object is not present
			 * so not catching both the exception separately.
			 */
			LOG.error("Zoombar is not present on the map");
			scenario.write("Zoombar is not present on the map");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^click on \"([^\"]*)\" direction button$")
	public void click_on_direction_button(String buttonName) throws Throwable {
		try {
			defaultStyle = googleMap.googleMapContainer.getAttribute("style");
			LOG.info("Default Style: " + defaultStyle);
			action.syncClickable(driver, googleMap.getLeftRightUpDownButtonOnMap(driver, buttonName));
			googleMap.getLeftRightUpDownButtonOnMap(driver, buttonName).click();
			// actions.moveToElement(driver, googleMap.getLeftRightUpDownButtonOnMap(driver,
			// buttonName)).click().build().perform();
			LOG.info("Clicked on " + buttonName + " direction button");
		} catch (Exception e) {
			LOG.error("Unable to click on " + buttonName + " direction button");
			scenario.write("Unable to click on " + buttonName + " direction button");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify google map is slided to show upper area into view$")
	public void verify_google_map_is_slided_to_show_upper_area_into_view() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			action.forceWait(5);
			String defaultTopVal = action.getCSSAttributeValueFromStyleAttribute(defaultStyle, "top");
			int defaultIntVal = action.getIntegerValueOfCSS(defaultTopVal);
			LOG.info("Previous Value: " + defaultIntVal);
			String currentStyle = googleMap.googleMapContainer.getAttribute("style");
			LOG.info("Current Style: " + currentStyle);
			String currentTopVal = action.getCSSAttributeValueFromStyleAttribute(currentStyle, "top");
			int currentIntVal = action.getIntegerValueOfCSS(currentTopVal);
			LOG.info("Current Value: " + currentIntVal);
			// here we are verifying whether position in view changes or not
			Assert.assertTrue("Google map is not slided to show upper area into view", currentIntVal < defaultIntVal);
			LOG.info("Google map is slided to show upper area into view");
		} catch (AssertionError ae) {
			LOG.error("Google map is not slided to show upper area into view");
			throw new Throwable("Google map is not slided to show upper area into view");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify google map is slided to show lower area into view$")
	public void verify_google_map_is_slided_to_show_lower_area_into_view() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			action.forceWait(5);
			String defaultTopVal = action.getCSSAttributeValueFromStyleAttribute(defaultStyle, "top");
			int defaultIntVal = action.getIntegerValueOfCSS(defaultTopVal);
			LOG.info("Previous Value: " + defaultIntVal);
			String currentStyle = googleMap.googleMapContainer.getAttribute("style");
			LOG.info("Current Style: " + currentStyle);
			String currentTopVal = action.getCSSAttributeValueFromStyleAttribute(currentStyle, "top");
			int currentIntVal = action.getIntegerValueOfCSS(currentTopVal);
			LOG.info("Current Value: " + currentIntVal);
			// here we are verifying whether position in view changes or not
			Assert.assertTrue("Google map is not slided to show lower area into view", currentIntVal > defaultIntVal);
			LOG.info("Google map is slided to show upper lower into view");
		} catch (AssertionError ae) {
			LOG.error("Google map is not slided to show lower area into view");
			throw new Throwable("Google map is not slided to show lower area into view");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify google map is slided to show left area into view$")
	public void verify_google_map_is_slided_to_show_left_area_into_view() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			action.forceWait(5);
			String defaultTopVal = action.getCSSAttributeValueFromStyleAttribute(defaultStyle, "left");
			int defaultIntVal = action.getIntegerValueOfCSS(defaultTopVal);
			LOG.info("Previous Value: " + defaultIntVal);
			String currentStyle = googleMap.googleMapContainer.getAttribute("style");
			LOG.info("Current Style: " + currentStyle);
			String currentTopVal = action.getCSSAttributeValueFromStyleAttribute(currentStyle, "left");
			int currentIntVal = action.getIntegerValueOfCSS(currentTopVal);
			LOG.info("Current Value: " + currentIntVal);
			// here we are verifying whether position in view changes or not
			Assert.assertTrue("Google map is not slided to show left area into view", currentIntVal < defaultIntVal);
			LOG.info("Google map is slided to show upper left into view");
		} catch (AssertionError ae) {
			LOG.error("Google map is not slided to show left area into view");
			throw new Throwable("Google map is not slided to show left area into view");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify google map is slided to show right area into view$")
	public void verify_google_map_is_slided_to_show_right_area_into_view() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			action.forceWait(5);
			String defaultTopVal = action.getCSSAttributeValueFromStyleAttribute(defaultStyle, "left");
			int defaultIntVal = action.getIntegerValueOfCSS(defaultTopVal);
			LOG.info("Previous Value: " + defaultIntVal);
			String currentStyle = googleMap.googleMapContainer.getAttribute("style");
			LOG.info("Current Style: " + currentStyle);
			String currentTopVal = action.getCSSAttributeValueFromStyleAttribute(currentStyle, "left");
			int currentIntVal = action.getIntegerValueOfCSS(currentTopVal);
			LOG.info("Current Value: " + currentIntVal);
			// here we are verifying whether position in view changes or not
			Assert.assertTrue("Google map is not slided to show left area into view", currentIntVal > defaultIntVal);
			LOG.info("Google map is slided to show upper right into view");
		} catch (AssertionError ae) {
			LOG.error("Google map is not slided to show right area into view");
			throw new Throwable("Google map is not slided to show right area into view");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify clicking on any incident displays that incident on the map$")
	public void verify_clicking_on_any_incident_displays_that_incident_on_the_map() throws Throwable {
		String incidentDescToClick = null;
		String popupText = null;
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
			incidentDescToClick = incidentDescriptionList.get(i).trim().split("\\r?\\n")[1];
			incidentList.get(i).click();
			LOG.info("Click on incident: " + incidentDescToClick);

			action.sync(driver, googleMap.popupOnMap);
			popupText = googleMap.popupOnMap.getText().trim();
			LOG.info("Popup text: " + popupText);
			Assert.assertTrue("Clicking on incident donot displays that incident on the map",
					popupText.contains(incidentDescToClick));
			LOG.info("Clicking on incident displays that incident on the map");
		} catch (AssertionError ae) {
			LOG.error("Clicking on incident donot displays that incident on the map");
			throw new Throwable("Clicking on incident: " + incidentDescToClick + " displays the incident: " + popupText
					+ " on the map");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify \"([^\"]*)\" pannel is active on the top of the map$")
	public void verify_pannel_is_active_on_the_top_of_the_map(String panelName) throws Throwable {
		try {
			action.sync(driver, googleMap.getTopPannelButtons(driver, panelName));
			WebElement panel = googleMap.getTopPannelButtons(driver, panelName);
			String panelClass = panel.getAttribute("class");
			Assert.assertTrue(panelName + " panel is not active on the map", panelClass.contains("Active"));
			LOG.info(panelName + " panel is active on the map");
		} catch (AssertionError ae) {
			LOG.error(panelName + " panel is not active on the map");
			throw new Throwable(panelName + " panel is not active on the map");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^click on the close button in the popup$")
	public void click_on_the_close_button_in_the_popup() throws Throwable {
		try {
			action.syncClickable(driver, googleMap.closePopupOnMap);
			googleMap.closePopupOnMap.click();
			LOG.info("Clicked on close button in the popup");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify information popup is closed$")
	public void verify_information_popup_is_closed() throws Throwable {
		try {
			action.waitForElementToDisappear(driver, googleMap.popupOnMap);
			LOG.info("Information popup is closed successfully");
		} catch (Exception e) {
			LOG.error("Information popup is not closed");
			scenario.write("Information popup is not closed");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify clicking on any camera displays that camera on the map$")
	public void verify_clicking_on_any_camera_displays_that_camera_on_the_map() throws Throwable {
		String cameraDescToClick = null;
		String popupText = null;
		try {
			List<WebElement> cameraList = homePage.cameraListCameraSubTabLiveTab;
			List<String> cameraDescriptionList = action.convertWebElementListToStringList(cameraList);
			// Taking random camera from list
			int i = (int) (Math.random() * cameraDescriptionList.size());
			/*
			 * Splitting Camera data (time and description) by new line and taking only
			 * description
			 */
			cameraDescToClick = cameraDescriptionList.get(i).trim();// .split("\\r?\\n")[1];
			cameraList.get(i).click();
			LOG.info("Click on camera: " + cameraDescToClick);

			action.sync(driver, googleMap.popupOnMap);
			driver.switchTo().frame("ifCam"); // switching to camera description frame in popoup
			popupText = googleMap.popupDescriptionOnInsideFrameInMap.getText().trim();
			driver.switchTo().defaultContent();// comming out of the camera description frame
			LOG.info("Popup text: " + popupText);
			Assert.assertTrue("Clicking on camera donot displays that camera on the map",
					popupText.contains(cameraDescToClick));
			LOG.info("Clicking on camera displays that camera on the map");
		} catch (AssertionError ae) {
			LOG.error(
					"Clicking on camera: " + cameraDescToClick + " displays the camera: " + popupText + " on the map");
			throw new Throwable(
					"Clicking on camera: " + cameraDescToClick + " displays the camera: " + popupText + " on the map");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify clicking on any Tolls displays that Tolls on the map$")
	public void verify_clicking_on_any_Tolls_displays_that_Tolls_on_the_map() throws Throwable {
		String tollDescToClick = null;
		String popupText = null;
		try {
			List<WebElement> tollList = homePage.tollListTollSubTabLiveTab;
			List<String> tollDescriptionList = action.convertWebElementListToStringList(tollList);
			// Taking random toll from list
			int i = (int) (Math.random() * tollDescriptionList.size());
			/*
			 * Splitting Toll data (time and description) by new line and taking only
			 * description
			 */
			tollDescToClick = tollDescriptionList.get(i).trim();// .split("\\r?\\n")[1];
			// since above normal click is not working in every case so using javascript
			// click
			action.javascriptClick(driver, tollList.get(i));
			LOG.info("Click on Toll: " + tollDescToClick);

			action.sync(driver, googleMap.popupOnMap);
			driver.switchTo().frame(0); // switching to only available frame in popoup
			popupText = googleMap.popupDescriptionOnInsideFrameInMap.getText().trim();
			driver.switchTo().defaultContent();// comming out of the description frame
			LOG.info("Popup text: " + popupText);
			// Comparing case in-sensative text only
			Assert.assertTrue("Clicking on Toll donot displays that Toll on the map",
					popupText.toLowerCase().contains(tollDescToClick.toLowerCase()));
			LOG.info("Clicking on Toll displays that Toll on the map");
		} catch (AssertionError ae) {
			LOG.error("Clicking on Toll: " + tollDescToClick + " displays the Toll " + popupText + " on the map");
			throw new Throwable(
					"Clicking on Toll: " + tollDescToClick + " displays the Toll " + popupText + " on the map");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}
}
