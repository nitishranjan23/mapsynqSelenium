package steps;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
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
import pageobjects.GoogleMap;
import pageobjects.MapsynqHome;
import pageobjects.NavigationPages;
import utils.PropertiesFileReader;

/**
 * Setup class for performing all setups like launching the browser before
 * starting test and closing the browser after test.
 * 
 * @author Nitish
 */
public class SetUp {

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
	public static Scenario scenario;

	/**
	 * Initialization method: Here browser is selected based on data in
	 * globalConfig.properties file. Then {@link DesiredCapabilities} of that
	 * browser is provided and finally the browser is launched with the provided
	 * capabilities.
	 * 
	 * @author Nitish
	 */
	
	@Before
	@SuppressWarnings("deprecation")
	public void setupTest(Scenario scenario) {
		SetUp.scenario = scenario;
		System.out.println("Scenario Name: " + scenario.getName());
		properties = fileReader.loadPropertiesFile("globalConfig.properties"); // reading properties file

		String browser = properties.getProperty("browser");
		String headless = properties.getProperty("headless");
		// For headless execution I will be using Chrome here in headless mode
		if (headless.equalsIgnoreCase("true")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			// Getting current screen resolution
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = screenSize.width;
			int height = screenSize.height;
			System.out.println(width + "x" + height);
			// Launching headless chrome in same size as of current screen resolution
			// chromeOptions.addArguments("window-size="+width+"x"+height);
			chromeOptions.addArguments("window-size=1366x768");
			chromeOptions.addArguments("--allow-insecure-localhost");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
		} else if (browser.equalsIgnoreCase("IE")) {
			
			DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			dc.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			//dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
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
	 * attach it to report file also it will save taken failed screenshots to
	 * screenshot/failed folder
	 * 
	 * @author Nitish
	 * @throws IOException
	 */
	@After
	public void closeResources(Scenario scenario) throws IOException {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		if (scenario.isFailed()) {
			// Taking screenshot and adding/embedding it to report
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
			
			// Saving screenshot of failed scenarios in screenshot/failed folder
			File folderLocation = new File(
					"screenshots/failed/" + System.currentTimeMillis() + "_" + scenario.getName() + ".png");
			FileUtils.copyFile(screenshotFile, folderLocation);
		} else {
			// Saving screenshot of passed scenarios in screenshot/passed folder
			File folderLocation = new File(
					"screenshots/passed/" + System.currentTimeMillis() + "_" + scenario.getName() + ".png");
			FileUtils.copyFile(screenshotFile, folderLocation);
		}
		driver.close();
		driver.quit();
	}
}
