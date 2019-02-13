package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.GoogleMap;
import pageObject.MapsynqHome;
import reusableMethods.ActionMethods;
import utils.Setup;

/**
 * This Class is used for defining steps related to validations of interactions on Google Map.
 * It mainly contains step definition of GoogleMapRelatedFunctionality.feature file.
 * 
 * @author Nitish
 * */
public class GoogleMapsSteps {

	private static final Logger Log = Logger.getLogger(GoogleMapsSteps.class);
	WebDriver driver = Setup.driver;
	Properties prop = Setup.properties;
	ActionMethods action = new ActionMethods();
	Actions actions = Setup.action;
	MapsynqHome homePage = Setup.homePage;
	GoogleMap googleMap = Setup.googleMapPage;
			
	private String defaultStyle;
	
	@When("^Google map is loaded$")
	public void google_map_is_loaded() throws Throwable {
	     try {
	    	 //Here we will be waiting for page loading status to be complete
	    	 action.waitForPageLoad(driver);
	    	 Log.info("Google Map is loaded");
	     } catch (Exception e) {
			Log.error("Google Map is not loaded properly");
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify panel buttons are present on top of the map to show following$")
	public void verify_panel_buttons_are_present_on_top_of_the_map_to_show_following(List<String> pannelButtons)
			throws Throwable {
		try {
			/*Here I am using SoftAssert because it will check all the links and do not
			 stops the execution if any link is not present
			 It will show the error (if any) only after checking all the conditions*/
			SoftAssert sf = new SoftAssert();
			for (String buttonName : pannelButtons) {
				boolean present = googleMap.getTopPannelButtons(driver, buttonName).isDisplayed();
				sf.assertTrue(present, buttonName + " button is not present on top of the map");
				if (present) {
					Log.info(buttonName + " button is not present on top of the map");
				}
			}
			sf.assertAll();
			Log.info("All buttons are present");
		} catch (Exception e) {
			Log.error("All/Some buttons are not present");
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
			//actions.moveToElement(googleMap.getTopPannelButtons(driver, buttonName)).click().build().perform();
			Log.info("Clicked on " + buttonName + " panel button");
			} else {
				Log.info(buttonName + " panel button is already active");
			}
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify incidents are marked on the map$")
	public void verify_incidents_are_marked_on_the_map() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			List<WebElement> incidentsOnMap = googleMap.incidentsOnMap;
			Assert.assertTrue("Incidents are not marked on the map", incidentsOnMap.size() > 0);
			Log.info("Incidents are marked on the map");
		} catch (AssertionError ae) {
			Log.error("Incidents are not marked on the map");
			throw new Throwable("Incidents are not marked on the map");
		}catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify Live Traffic information is displayed on the map$")
	public void verify_Live_Traffic_information_is_displayed_on_the_map() throws Throwable {
		try {
			Assert.assertTrue("Live Traffic information is not displayed", googleMap.liveTrafficLegend.isDisplayed());
			Log.info("Live Traffic information is displayed");
		} catch (AssertionError ae) {
			Log.error("Live Traffic information is not displayed");
			throw new Throwable("Live Traffic information is not displayed");
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify following direction slider buttons are present on the map$")
	public void verify_following_direction_slider_buttons_are_present_on_the_map(List<String> directionSliderButtons)
			throws Throwable {
		try {
			SoftAssert sf = new SoftAssert();
			for(String button: directionSliderButtons) {
				sf.assertTrue(googleMap.getLeftRightUpDownButtonOnMap(driver, button).isDisplayed(), button +" direction slider button is not present on the map");
				Log.info(button +" direction slider button is present on the map");
			} 
			sf.assertAll();
			Log.info("All direction slider buttons are present on the map");
		} catch (AssertionError ae) {
			Log.error("All/Some direction slider button is not present on the map");
			throw new Throwable("All/Some direction slider button is not present on the map");
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify \"([^\"]*)\" and \"([^\"]*)\" buttons are present on the map$")
	public void verify_and_buttons_are_present_on_the_map(String buttonName1, String buttonName2) throws Throwable {
		//verification for first button
		try {
			Assert.assertTrue(buttonName1 + " button is not present on the map", googleMap.getZoomInZoomOutButtonOnZoomSliderOnMap(driver, buttonName1).isDisplayed());
			Log.info(buttonName1 + " button is present on the map");
		} catch (Exception e) {
			/*Here both of Exception and Assertion Error denote that object is not present
			 * so not catching both the exception separately.
			 * */
			Log.error(buttonName1 + " button is not present on the map");
			Log.error(e);
			throw e;
		}
		
		//verification for second button
		try {
			Assert.assertTrue(buttonName2 + " button is not present on the map", googleMap.getZoomInZoomOutButtonOnZoomSliderOnMap(driver, buttonName2).isDisplayed());
			Log.info(buttonName2 + " button is present on the map");
		} catch (Exception e) {
			/*Here both of Exception and Assertion Error denote that object is not present
			 * so not catching both the exception separately.
			 * */
			Log.error(buttonName2 + " button is not present on the map");
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify zoombar is present on the map$")
	public void verify_zoombar_is_present_on_the_map() throws Throwable {
		try {
			Assert.assertTrue("Zoombar is not present on the map", googleMap.zoomBarOnMap.isDisplayed());
			Log.info("Zoombar is present on the map");
		} catch (Exception e) {
			/*Here both of Exception and Assertion Error denote that object is not present
			 * so not catching both the exception separately.
			 * */
			Log.error("Zoombar is not present on the map");
			Log.error(e);
			throw e;
		}
	}

	@Then("^click on \"([^\"]*)\" direction button$")
	public void click_on_direction_button(String buttonName) throws Throwable {
		try {
			defaultStyle = googleMap.googleMapContainer.getAttribute("style");
			Log.info("Default Style: "+ defaultStyle);
			action.syncClickable(driver, googleMap.getLeftRightUpDownButtonOnMap(driver, buttonName));
			googleMap.getLeftRightUpDownButtonOnMap(driver, buttonName).click();
			//actions.moveToElement(driver, googleMap.getLeftRightUpDownButtonOnMap(driver, buttonName)).click().build().perform();
			Log.info("Clicked on " + buttonName + " direction button");
		} catch (Exception e) {
			Log.error("Unable to click on " + buttonName + " direction button");
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify google map is slided to show upper area into view$")
	public void verify_google_map_is_slided_to_show_upper_area_into_view() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			Thread.sleep(5000); //forcefully waiting (although it is a bad practice to use Thread.sleep)
			String defaultTopVal = action.getCSSAttributeValueFromStyleAttribute(defaultStyle, "top");
			int defaultIntVal = action.getIntegerValueOfCSS(defaultTopVal);
			Log.info("Previous Value: "+ defaultIntVal);
			String currentStyle = googleMap.googleMapContainer.getAttribute("style");
			Log.info("Current Style: "+ currentStyle);
			String currentTopVal = action.getCSSAttributeValueFromStyleAttribute(currentStyle, "top");
			int currentIntVal = action.getIntegerValueOfCSS(currentTopVal);
			Log.info("Current Value: "+ currentIntVal);
			//here we are verifying whether position in view changes or not 
			Assert.assertTrue("Google map is not slided to show upper area into view", currentIntVal < defaultIntVal);
			Log.info("Google map is slided to show upper area into view");
		} catch (AssertionError ae) {
			Log.error("Google map is not slided to show upper area into view");
			throw new Throwable("Google map is not slided to show upper area into view");
		}catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify google map is slided to show lower area into view$")
	public void verify_google_map_is_slided_to_show_lower_area_into_view() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			Thread.sleep(5000); //forcefully waiting (although it is a bad practice to use Thread.sleep)
			String defaultTopVal = action.getCSSAttributeValueFromStyleAttribute(defaultStyle, "top");
			int defaultIntVal = action.getIntegerValueOfCSS(defaultTopVal);
			Log.info("Previous Value: "+ defaultIntVal);
			String currentStyle = googleMap.googleMapContainer.getAttribute("style");
			Log.info("Current Style: "+ currentStyle);
			String currentTopVal = action.getCSSAttributeValueFromStyleAttribute(currentStyle, "top");
			int currentIntVal = action.getIntegerValueOfCSS(currentTopVal);
			Log.info("Current Value: "+ currentIntVal);
			//here we are verifying whether position in view changes or not 
			Assert.assertTrue("Google map is not slided to show lower area into view", currentIntVal > defaultIntVal);
			Log.info("Google map is slided to show upper lower into view");
		} catch (AssertionError ae) {
			Log.error("Google map is not slided to show lower area into view");
			throw new Throwable("Google map is not slided to show lower area into view");
		}catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify google map is slided to show left area into view$")
	public void verify_google_map_is_slided_to_show_left_area_into_view() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			Thread.sleep(5000); //forcefully waiting (although it is a bad practice to use Thread.sleep)
			String defaultTopVal = action.getCSSAttributeValueFromStyleAttribute(defaultStyle, "left");
			int defaultIntVal = action.getIntegerValueOfCSS(defaultTopVal);
			Log.info("Previous Value: "+ defaultIntVal);
			String currentStyle = googleMap.googleMapContainer.getAttribute("style");
			Log.info("Current Style: "+ currentStyle);
			String currentTopVal = action.getCSSAttributeValueFromStyleAttribute(currentStyle, "left");
			int currentIntVal = action.getIntegerValueOfCSS(currentTopVal);
			Log.info("Current Value: "+ currentIntVal);
			//here we are verifying whether position in view changes or not 
			Assert.assertTrue("Google map is not slided to show left area into view", currentIntVal < defaultIntVal);
			Log.info("Google map is slided to show upper left into view");
		} catch (AssertionError ae) {
			Log.error("Google map is not slided to show left area into view");
			throw new Throwable("Google map is not slided to show left area into view");
		}catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify google map is slided to show right area into view$")
	public void verify_google_map_is_slided_to_show_right_area_into_view() throws Throwable {
		try {
			action.waitForPageLoad(driver);
			Thread.sleep(5000); //forcefully waiting (although it is a bad practice to use Thread.sleep)
			String defaultTopVal = action.getCSSAttributeValueFromStyleAttribute(defaultStyle, "left");
			int defaultIntVal = action.getIntegerValueOfCSS(defaultTopVal);
			Log.info("Previous Value: "+ defaultIntVal);
			String currentStyle = googleMap.googleMapContainer.getAttribute("style");
			Log.info("Current Style: "+ currentStyle);
			String currentTopVal = action.getCSSAttributeValueFromStyleAttribute(currentStyle, "left");
			int currentIntVal = action.getIntegerValueOfCSS(currentTopVal);
			Log.info("Current Value: "+ currentIntVal);
			//here we are verifying whether position in view changes or not 
			Assert.assertTrue("Google map is not slided to show left area into view", currentIntVal > defaultIntVal);
			Log.info("Google map is slided to show upper right into view");
		} catch (AssertionError ae) {
			Log.error("Google map is not slided to show right area into view");
			throw new Throwable("Google map is not slided to show right area into view");
		}catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	@Then("^verify clicking on any incident displays that incident on the map$")
	public void verify_clicking_on_any_incident_displays_that_incident_on_the_map() throws Throwable {
		
	}

	@Then("^verify \"([^\"]*)\" pannel is active on the top of the map$")
	public void verify_pannel_is_active_on_the_top_of_the_map(String arg1) throws Throwable {

	}

	@Then("^click on the close button in the popup$")
	public void click_on_the_close_button_in_the_popup() throws Throwable {

	}

	@Then("^verify information popup is closed$")
	public void verify_information_popup_is_closed() throws Throwable {

	}

	@Then("^click on any icon incident icon on map$")
	public void click_on_any_icon_incident_icon_on_map() throws Throwable {

	}

	@Then("^verify information popup is displayed$")
	public void verify_information_popup_is_displayed() throws Throwable {

	}

	@Then("^verify clicking on any camera displays that camera on the map$")
	public void verify_clicking_on_any_camera_displays_that_camera_on_the_map() throws Throwable {

	}

	@Then("^click on any camera icon on map$")
	public void click_on_any_camera_icon_on_map() throws Throwable {

	}

	@Then("^verify clicking on any Tolls displays that Tolls on the map$")
	public void verify_clicking_on_any_Tolls_displays_that_Tolls_on_the_map() throws Throwable {

	}

	@Then("^click on any Tolls icon on map$")
	public void click_on_any_Tolls_icon_on_map() throws Throwable {

	}

}
