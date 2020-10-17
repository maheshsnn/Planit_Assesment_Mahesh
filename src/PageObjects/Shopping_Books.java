package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utility.Propertiesfile;

public class Shopping_Books {

	/*
	 * page objects for shopping page is placed in this class and methods related to this page are also created here
	 */
	static Propertiesfile pro;

	static By Books_TopMenu = By.xpath("//ul[@class='top-menu']//li//a[@href='/books']");
	static By Books_link = By.xpath("//div[@class='listbox']//ul//li//a[@href='/books']");
	static By First_Book = By.xpath("(//div[@class='product-item'])[1]");
	static By Add_to_cart = By.xpath("(//input[@value='Add to cart'])[1]");
	static By prouct_price = By.xpath("//span[@class='price-value-13']");
	static By product_Quantity = By.xpath("//*[@id='addtocart_13_EnteredQuantity']");
	static By Banner = By.xpath("//*[@class='content']");
	static By SubTotal = By.xpath("//td[@class='cart-total-right']");
	static By Shopping_cart = By.xpath("//*[@id='topcartlink']/a/span[1]");
	static By terms_services = By.xpath("//input[@id='termsofservice']");
	static By checkout = By.xpath("//button[@id='checkout']");

	public static void Shop_Books(WebDriver driver) throws Exception {
		pro = new Propertiesfile();
		driver.findElement(Books_TopMenu).click();
		driver.findElement(Books_link).click();
		driver.findElement(First_Book).click();
		String product_single_Unit = driver.findElement(prouct_price).getText();
		driver.findElement(product_Quantity).click();
		driver.findElement(product_Quantity).clear();
		driver.findElement(product_Quantity).sendKeys(pro.Quantity());
		driver.findElement(Add_to_cart).click();
		String Banner_Text = driver.findElement(Banner).getText();
		Assert.assertEquals(Banner_Text, "The product has been added to your shopping cart");
		driver.findElement(Shopping_cart).click();
		String subtotal = driver.findElement(SubTotal).getText();

		double singleunt_cost = Double.parseDouble(product_single_Unit);
		double total_untcost = Double.parseDouble(subtotal);
		int Qau = Integer.parseInt(pro.Quantity());
		Assert.assertEquals(total_untcost, singleunt_cost * Qau);

		driver.findElement(terms_services).click();
		driver.findElement(checkout).click();

	}

}
