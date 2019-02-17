package steps;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.GoogleMap;
import pageobjects.MapsynqHome;
import utils.ActionMethods;

/**
 * This class is used for defining Step Definitions for search related features
 * on mapsynq home page
 * 
 * @author Nitish
 * 
 */
public class Search {

	private static final Logger LOG = Logger.getLogger(GoogleMapsSteps.class);
	WebDriver driver = SetUp.driver;
	Properties prop = SetUp.properties;
	ActionMethods action = new ActionMethods();
	Actions actions = SetUp.action;
	MapsynqHome homePage = SetUp.homePage;
	GoogleMap googleMap = SetUp.googleMapPage;
	Scenario scenario = SetUp.scenario;

	@When("^search text box is displayed on the page$")
	public void search_text_box_is_displayed_on_the_page() throws Throwable {
		try {
			action.sync(driver, homePage.searchTB);
			LOG.info("Search text box is displayed successfully on the home page");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^type \"([^\"]*)\" in the search text box$")
	public void type_in_the_search_text_box(String searchText) throws Throwable {
		try {
			action.sync(driver, homePage.searchTB);
			homePage.searchTB.sendKeys(searchText);
			LOG.info(searchText + " is typed in the search text box");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify global search auto-complete suggestions are displayed$")
	public void verify_global_search_auto_complete_suggestions_are_displayed() throws Throwable {
		try {
			action.sync(driver, homePage.globalSearchSuggestion);
			LOG.info("Global search auto-complete suggestions are displayed");
		} catch (Exception e) {
			LOG.error("Global search auto-complete suggestion is not displayed");
			scenario.write("Global search auto-complete suggestion is not displayed");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^select one of the global search suggestions$")
	public void select_one_of_the_global_search_suggestions() throws Throwable {
		try {
			action.sync(driver, homePage.globalSearchSuggestion);
			// Taking all suggestions in auto-complete into a list
			List<WebElement> globalSuggestionElemens = homePage.globalSearchSuggestionList;
			globalSuggestionElemens.get(0).click();
			LOG.info("One global search suggestion is selected");
		} catch (Exception e) {
			LOG.error("Error in selecting global search option");
			scenario.write("Error in selecting global search option");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify global search result is displayed$")
	public void verify_global_search_result_is_displayed() throws Throwable {
		try {
			action.sync(driver, homePage.globalSearchResultContainer);
			int numberOfSearchResults = homePage
					.getGlobalSearchResultInsideContainer(homePage.globalSearchResultContainer).size();
			Assert.assertTrue("Search results is not displayed", numberOfSearchResults > 0);
			LOG.info("Global search Search results is displayed");
		} catch (Exception e) {
			LOG.error("Search results is not displayed");
			scenario.write("Search results is not displayed");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^click on triangle icon beside any search result$")
	public void click_on_triangle_icon_beside_any_search_result() throws Throwable {
		try {
			action.sync(driver, homePage.globalSearchResultContainer);
			int i = (int) (Math.random() * homePage.contextClickTriangleButtonBesideSearchResult.size());
			action.syncClickable(driver, homePage.contextClickTriangleButtonBesideSearchResult.get(i));
			homePage.contextClickTriangleButtonBesideSearchResult.get(i).click();
			LOG.info("Clicked on triangle icon beside one search result");
		} catch (Exception e) {
			LOG.error("Error in clicking on triangle icon beside a search result");
			scenario.write("Error in clicking on triangle icon beside a search result");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify context menu is opened$")
	public void verify_context_menu_is_opened() throws Throwable {
		try {
			action.sync(driver, homePage.contextMenuPopup);
			LOG.info("Context menu is opened");
		} catch (Exception e) {
			LOG.error("context menu didn't open");
			scenario.write("context menu didn't open");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify following options are present in the context menu$")
	public void verify_following_options_are_present_in_the_context_menu(List<String> expectedContextMenuOptions)
			throws Throwable {
		try {
			/*
			 * Here I am finding objects taking parent object into consideration. In this
			 * approach if parents locator changes we need to change it only one place and
			 * child objects will automatically be corrected
			 */
			List<String> actualOptions = action.convertWebElementListToStringList(
					homePage.getContextMenuOptionsInsidePopup(homePage.contextMenuPopup));
			/*
			 * Here I am using SoftAssert because it will check all the context menu options
			 * and do not stops the execution if any option is not present. It will show the
			 * error (if any) only after checking all the conditions
			 */
			SoftAssert sf = new SoftAssert();
			for (String option : expectedContextMenuOptions) {
				boolean present = actualOptions.contains(option);
				sf.assertTrue(present, option + " is not present in the context menu popup");
				if (present) {
					LOG.info(option + " is present in the context menu popup");
				} else {
					LOG.error(option + " is not present in the context menu popup");
					scenario.write(option + " is not present in the context menu popup");
				}
			}
			sf.assertAll();
			LOG.info("All context menu options are present");
		} catch (Exception e) {
			LOG.error("All/Some context menu options are not present");
			scenario.write("All/Some context menu options are not present");
			throw e;
		}
	}

	@Then("^verify fbShare icon is present$")
	public void verify_fbShare_icon_is_present() throws Throwable {
		try {
			action.sync(driver, homePage.contextMenuPopup);
			/*
			 * Here I am finding objects taking parent object into consideration. In this
			 * approach if parents locator changes we need to change it only one place and
			 * child objects will automatically be corrected
			 */
			action.sync(driver, homePage.getfbShareOptionsInsidePopup(homePage.contextMenuPopup));
			LOG.info("fb Share icon is present inside context popup");
		} catch (Exception e) {
			LOG.error("fb Share icon is not present inside context popup");
			scenario.write("fb Share icon is not present inside context popup");
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify clicking on any global search result displays that on map$")
	public void verify_clicking_on_any_global_search_result_displays_that_on_map() throws Throwable {
		String resultToClick = null;
		String popupTitle = null;
		try {
			List<WebElement> searchResultList = homePage
					.getGlobalSearchResultInsideContainer(homePage.globalSearchResultContainer);
			List<String> searchResultTextList = action.convertWebElementListToStringList(searchResultList);
			LOG.info("Actual Result List: " + searchResultTextList);
			// Taking random data from list
			int i = (int) (Math.random() * searchResultTextList.size());
			resultToClick = searchResultTextList.get(i).trim();
			action.waitForPageLoad(driver);
			action.syncClickable(driver, searchResultList.get(i));
			//searchResultList.get(i).click();
			//action.javascriptClick(driver, searchResultList.get(i));
			actions.doubleClick(searchResultList.get(i)).build().perform();
			LOG.info("Click on result: " + resultToClick);

			action.sync(driver, googleMap.popupHeader);
			popupTitle = googleMap.popupHeader.getText().trim();
			LOG.info("Popup Header: " + popupTitle);
			Assert.assertTrue("Clicking on global search result donot displays that search result on the map",
					popupTitle.toLowerCase().contains(resultToClick.toLowerCase()));
			LOG.info("Clicking on global search result displays that search result on the map");
		} catch (AssertionError ae) {
			LOG.error("Clicking on global search result donot displays that search result on the map");
			throw new Throwable(
					"Clicking on global search result: " + resultToClick + " displays the search result: " + popupTitle + " on the map");
		} catch (Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Then("^verify following category-wise links are present in the information popup$")
	public void verify_following_category_wise_links_are_present_in_the_information_popup(
			DataTable categoryWiseLink) throws Throwable {
		try {
			List<List<String>> categoryWiseLinkList = categoryWiseLink.raw();
			/*
			 * Here I am using SoftAssert because it will check all the category links
			 * and do not stops the execution if any one of them do not match. It will show the
			 * error (if any) only after checking all the conditions
			 */
			SoftAssert sf = new SoftAssert();
			for(int i =1; i < categoryWiseLinkList.size(); i++) {
				String category = categoryWiseLinkList.get(i).get(0);
				String links = categoryWiseLinkList.get(i).get(1);
				List<String> expectedLinks = Arrays.asList(links.split(",".trim()));
				LOG.info("Expected links for "+category+"category: " + expectedLinks);
				
				List<String> actList = action.convertWebElementListToStringList(googleMap.getLinksInInformationPopupCorrespndingToCategory(driver, category));
				LOG.info("Actual links for "+category+"category: " + actList);
				sf.assertTrue(actList.containsAll(expectedLinks), category + " category links do not match");
			}
			sf.assertAll();
			LOG.info("All category-wise links matches successfully");
		} catch (Exception e) {
			LOG.error("All/Some category-wise links do not match");
			scenario.write("All/Some category-wise links do not match");
			throw e;
		}
	}

}
