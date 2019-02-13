package reusableMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionMethods {

	static Logger Log = Logger.getLogger(ActionMethods.class);

	/**
	 * This method will check the ready state of a page to find whether the page is
	 * loaded or not
	 * 
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 * 
	 */
	public void waitForPageLoad(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("status: " + js.executeScript("return document.readyState").toString());
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			return;
		}
	}

	/**
	 * This method is used for synchronization. It will wait for the
	 * {@link WebElement} to be present on the page By default I have put the
	 * maximum waiting time to be 30sec. If the element will be available before the
	 * given time it will continue the execution without waiting for max time
	 * provided. It will ignore the {@link StaleElementReferenceException} and
	 * {@link NoSuchElementException} if occured in between the availablity of
	 * {@link WebElement} and maximum time interval provided.
	 * 
	 * @author Nitish
	 * @param driver  {@link WebDriver} instance
	 * @param element {@link WebElement} to wait for
	 */
	public void sync(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method is used for synchronization. It will wait for the
	 * {@link WebElement} to be clickable. By default I have put the maximum waiting
	 * time to be 30sec. If the element will be available before the given time it
	 * will continue the execution without waiting for max time provided. It will
	 * ignore the {@link StaleElementReferenceException} and
	 * {@link NoSuchElementException} if occured in between the clickability of
	 * {@link WebElement} and maximum time interval provided.
	 * 
	 * @author Nitish
	 * @param driver  {@link WebDriver} instance
	 * @param element {@link WebElement} to wait for
	 */
	public void syncClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class)
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will convert any {@link WebElement} list to String list. It is
	 * useful when we are picking multiple {@link WebElement}s in a {@link List}
	 * with a single selector.
	 * 
	 * @author Nitish
	 * @param elementList {@link WebElement} List
	 * @return string list of all {@link WebElement} text
	 */
	public List<String> convertWebElementListToStringList(List<WebElement> elementList) {
		try {
			List<String> stringList = new ArrayList<String>();
			for (WebElement el : elementList) {
				stringList.add(el.getText().trim()); // getting text of each element and adding it to string list after
														// removing space
			}
			return stringList;
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}

	/**
	 * This method will get all options/values present in a dropdown. Dropdown
	 * should be of {@link Select} type
	 * 
	 * @author Nitish
	 * @param driver          {@link WebDriver} instance
	 * @param dropdownElement {@link WebElement} of dropdown
	 * @return All available options in the form of {@link List} of
	 *         {@link WebElement}
	 */
	public List<WebElement> getOptionsFromDropdown(WebDriver driver, WebElement dropdownElement) {
		Select select = new Select(dropdownElement);
		List<WebElement> availableOptions = select.getOptions();
		return availableOptions;
	}

	/**
	 * This method will wait for alert to appear. By default I have given maximum
	 * waiting time of 10sec waiting time. If alert appears before that, the
	 * execution will continue without waiting for maximum time.
	 * 
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 */
	public void waitForAlertToAppear(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * This method will return a particular css attribute value from style attribute of tag.
	 * 
	 * @author Nitish
	 * @param styleAttribute Style attribute of any {@link WebElement}
	 * @param cssValueToGet Required CSS value to get from Style attribute
	 * @return CSS value in from of a string
	 * 
	 * */
	public String getCSSAttributeValueFromStyleAttribute(String styleAttribute, String cssValueToGet) {
		// Separating different style attributes
		String[] styleArr = styleAttribute.split(";");
		HashMap<String, String> attributeMap = new HashMap<String, String>();
		for (int i = 0; i < styleArr.length; i++) {
			try {
				 //Separating style attributes and its value and putting it in a Map
				String[] attributes = styleArr[i].split(":");
				attributeMap.put(attributes[0].trim(), attributes[1].trim());
			} catch (NullPointerException np) {
				// Do Nothing and continue the execution
			}
		}
		return attributeMap.get(cssValueToGet);
	}
	
	/**
	 * This method will remove any "%" symbol or "px" after the exact CSS value
	 * It will also convert the String value to Integer value which will help us in numerical computation
	 * 
	 * @author Nitish
	 * @param stringCSSValue CSS value in form of a string e.g. 10px or 100%
	 * @return It will return Integer value corresponding to String value passed 
	 * after removing "px" or "%"
	 * */
	public int getIntegerValueOfCSS(String stringCSSValue) {
		int integerVal = Integer.parseInt(stringCSSValue.replace("%", "").replace("px", ""));
		return integerVal;
	}
	
	/* Incomplete...
	 * public void waitForPropertyToChange(WebDriver driver, WebElement element,
	 * String attribute, String attributeValue) { WebDriverWait wait = new
	 * WebDriverWait(driver, 10); boolean match = true; do { match =
	 * element.getAttribute(attribute).equalsIgnoreCase(attributeValue);
	 * }while(match); wait. }
	 */
}
