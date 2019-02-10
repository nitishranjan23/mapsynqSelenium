package reusableMethods;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionMethods {

	static Logger Log = Logger.getLogger(ActionMethods.class);
	
	/**
	 * This method will check the ready state of a page to 
	 * find whether the page is loaded or not
	 * 
	 * @author Nitish
	 * @param driver {@link WebDriver} instance
	 * 
	 * */
	public void waitForPageLoad(WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("status: "+js.executeScript("return document.readyState").toString());
		if(js.executeScript("return document.readyState").toString().equals("complete")) {
			return;
		}
	}

	public void sync(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will convert any {@link WebElement} list to String list.
	 * It is useful when we are picking multiple {@link WebElement}s in a {@link List}
	 * with a single selector.
	 * 
	 * @author Nitish
	 * @param elementList {@link WebElement} List
	 * @return string list of all {@link WebElement} text
	 * */
	public List<String> convertWebElementListToStringList(List<WebElement> elementList){
		try {
			List<String> stringList = new ArrayList<String>();
			for (WebElement el : elementList) {
				stringList.add(el.getText().trim()); //getting text of each element and adding it to string list after removing space
			}
			return stringList;
		} catch (Exception e) {
			Log.error(e);
			throw e;
		}
	}
}