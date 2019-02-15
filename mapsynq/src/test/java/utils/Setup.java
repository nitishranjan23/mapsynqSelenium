package utils;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.GoogleMap;
import pageObject.MapsynqHome;
import pageObject.NavigationPages;
import reusableMethods.PropertiesFileReader;
/**
 * Setup class for performing all setups like launching the browser 
 * before starting test and closing the browser after test.
 * 
 * @author Nitish
 * */
public class Setup {

	static {
		DOMConfigurator.configure("log4j.xml"); // Configure logger from log4j.xml file
	}
	public static WebDriver driver;
	public static MapsynqHome homePage;
	public static GoogleMap googleMapPage;
	public static Properties properties;
	public static NavigationPages navigationPage;
	static PropertiesFileReader fileReader = new PropertiesFileReader();
	public static Actions action;
	static Scenario scenario;

	/**
	 * Initialization method: Here browser is selected based on data in
	 * globalConfig.properties file. Then {@link DesiredCapabilities} of that
	 * browser is provided and finally the browser is launched with the provided
	 * capabilities.
	 * 
	 * @author Nitish
	 */
	@SuppressWarnings("deprecation")
	@Before
	public void setupTest(Scenario scenario) {
		Setup.scenario = scenario;
		System.out.println("Scenario Name: " + scenario.getName());
		properties = fileReader.loadPropertiesFile("globalConfig.properties"); // reading properties file

		String browser = properties.getProperty("browser");
		String headless = properties.getProperty("headless");
		//For headless execution I will be using Chrome here in headless mode
		if (headless.equalsIgnoreCase("true")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
		}else if (browser.equalsIgnoreCase("IE")) {
			DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			  dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			  dc.setCapability(InternetExplorerDriver.
			  INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			  dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			  dc.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			  dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver(dc);
		} else if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("phantomjs")) {
			WebDriverManager.phantomjs().setup();
			// driver = new PhantomJSDriver();
		} else if (browser.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}
		driver.manage().window().maximize(); // Maximizing the browser window
		homePage = PageFactory.initElements(driver, MapsynqHome.class);
		googleMapPage = PageFactory.initElements(driver, GoogleMap.class);
		navigationPage = PageFactory.initElements(driver, NavigationPages.class);
		action = new Actions(driver);
	}

	/**
	 * To close all driver instances and take screenshot of failed Testcase and
	 * attach it to report file
	 * 
	 * @author Nitish
	 */
	@After
	public void closeResources(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png"); // Adding/Embedding failure screenshot to report
		}
		
		 driver.close();
		 driver.quit();
	}
}
