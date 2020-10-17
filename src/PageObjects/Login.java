package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import Base.ManageBrowser;
import Utility.CommonMethods;

public class Login {
	
	/*
	 * All the page objects related to login page  are placed in this class and  methods related to this page are also created here
	 * we have used page object model here
	 * 
	 */

	static By Login_Button_Homepage = By.xpath("//a[@class='ico-login']");
	static By Welcome_Text = By.xpath("//*[text()='Welcome, Please Sign In!']");
	static By Username = By.xpath("//input[@name='Email']");
	static By Password = By.xpath("//input[@name='Password']");
	static By LoginButton = By.xpath("//input[@class='button-1 login-button']");
	static By Account = By.xpath("//a[@class='account']");
	
	
	
	public static void Loginto(WebDriver driver) throws Exception {
		driver.findElement(Login_Button_Homepage).click();
		CommonMethods.pageload(driver);
		if (driver.findElement(Welcome_Text).isDisplayed()) {
			String WelcomeText = driver.findElement(Welcome_Text).getText();
			System.out.println("Text displayed   " + WelcomeText);
		}

		driver.findElement(Username).sendKeys(CommonMethods.getdata().get("Username"));
		driver.findElement(Password).sendKeys(CommonMethods.getdata().get("Password"));
		driver.findElement(LoginButton).click();
		Assert.assertEquals(driver.findElement(Account).getText(), CommonMethods.getdata().get("Username"));

	}

	
}
