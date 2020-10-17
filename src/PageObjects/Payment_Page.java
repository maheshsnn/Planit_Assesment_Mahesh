package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utility.Propertiesfile;

public class Payment_Page {

	/*
	 * All page objects related to payment page are placed in this class and methods related to this page are also created here
	 */

	static Propertiesfile pro;

	static By new_Address = By.xpath("//select[@name='billing_address_id']");
	static By Country = By.xpath("//select[@name='BillingNewAddress.CountryId']");
	static By City = By.xpath("//input[@id='BillingNewAddress_City']");
	static By Address = By.xpath("//input[@id='BillingNewAddress_Address1']");
	static By Zip = By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']");
	static By phone = By.xpath("//input[@id='BillingNewAddress_PhoneNumber']");
	static By Billing_Continue = By.xpath("//*[@onclick='Billing.save()']");
	static By Shipping_Continue = By.xpath("//*[@onclick='Shipping.save()']");
	static By ShippingMethod_Continue = By.xpath("//*[@onclick='ShippingMethod.save()']");
	static By PaymentMethd_Continue = By.xpath("//*[@onclick='PaymentMethod.save()']");
	static By paymentinfo_Continue = By.xpath("//*[@onclick='PaymentInfo.save()']");
	static By confirmorder_continue = By.xpath("//*[@onclick='ConfirmOrder.save()']");
	static By confirmation_order_continue = By.xpath("//input[@class='button-2 order-completed-continue-button']");
	static By Next_Air = By.xpath("//input[@id='shippingoption_1']");
	static By Shipping_dd = By.xpath("//Select[@id='shipping-address-select']");
	static By COD = By.xpath("//input[@value='Payments.CashOnDelivery']");
	static By COD_MSG = By.xpath("//*[@id='checkout-payment-info-load']/div/div/div[1]/table/tbody/tr/td/p");
	static By Confirm_order = By.xpath("//input[@value='Confirm']");
	static By confirm_msg = By.xpath("//div[@class='title']");
	static By Order_Num = By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/ul/li[1]");
	static By logout = By.xpath("//a[@class='ico-logout']");

	public static void payment(WebDriver driver) throws Exception {
		pro = new Propertiesfile();
		new Select(driver.findElement(new_Address)).selectByVisibleText("New Address");
		new Select(driver.findElement(Country)).selectByVisibleText(pro.Country());
		driver.findElement(City).sendKeys(pro.City());
		driver.findElement(Address).sendKeys(pro.Address1());
		driver.findElement(Zip).sendKeys(pro.Zip());
		driver.findElement(phone).sendKeys(pro.Phone());
		driver.findElement(Billing_Continue).click();

	}

	public static void shipping(WebDriver driver) {
		WebElement DD_values = driver.findElement(Shipping_dd);
		List<WebElement> Alloptions = new Select(DD_values).getOptions();

		for (int i = 0; i < Alloptions.size(); i++) {
			if ((Alloptions.get(i).getText()).contains(pro.Zip())
					&& (Alloptions.get(i).getText()).contains(pro.Address1())
					&& (Alloptions.get(i).getText()).contains(pro.Zip())) {
				System.out.println("******" + Alloptions.get(i).getText());
				new Select(driver.findElement(Shipping_dd)).selectByVisibleText(Alloptions.get(i).getText());
				break;
			}
		}

		driver.findElement(Shipping_Continue).click();
		driver.findElement(Next_Air).click();
		driver.findElement(ShippingMethod_Continue).click();
		driver.findElement(COD).click();
		driver.findElement(PaymentMethd_Continue).click();
		String COD_MSSG = driver.findElement(COD_MSG).getText();
		Assert.assertEquals(COD_MSSG, "You will pay by COD");
		driver.findElement(paymentinfo_Continue).click();
		driver.findElement(Confirm_order).click();
		String confirms_msg = driver.findElement(confirm_msg).getText();
		Assert.assertEquals(confirms_msg, "Your order has been successfully processed!");
		System.out.println(driver.findElement(Order_Num).getText());
		driver.findElement(confirmation_order_continue).click();
		driver.findElement(logout).click();
	}

}
