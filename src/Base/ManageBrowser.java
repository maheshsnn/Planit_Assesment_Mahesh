package Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import Utility.Propertiesfile;

public class ManageBrowser {

	static Propertiesfile pro;

	
	/*
	 * class to create webdriver profile
	 * we have used singleton design pattern here
	 * 
	 */
	
	
	private WebDriver driver;

	// instance of singleton class
	private static ManageBrowser browserInstances = null;

	public ManageBrowser(String browser) throws Exception {

		pro = new Propertiesfile();
		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + System.getProperty("file.separator") + "chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + System.getProperty("file.separator") + "geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	// TO create instance of class
	public static ManageBrowser browserInstances(String browser) throws Exception {
		if (browserInstances == null) {
			browserInstances = new ManageBrowser(browser);
		}
		return browserInstances;
	}

	public WebDriver getdriver() {
		return driver;
	}

}
