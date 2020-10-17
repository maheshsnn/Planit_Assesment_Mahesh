package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Shoppingcart_ {

	/*
	 * page object related to shopping cart are placed here and we have created
	 * methods related to to shopping cart page
	 */

	static By Shopping_cart = By.xpath("//*[@id='topcartlink']/a/span[1]");
	static By Go_TO_cart = By.xpath("//input[@value='Go to cart']");
	static By Remove_checkbox = By.xpath("//input[@name='removefromcart']");
	static By updatecart = By.xpath("//input[@name='updatecart']");
	static By cart_quantity = By.xpath("//*[@class='cart-qty']");

	public static void Shoppingcart(WebDriver driver) {
		Actions Ac = new Actions(driver);
		if (driver.findElement(cart_quantity).getText().equals("(0)")) {
		} else {
			Ac.moveToElement(driver.findElement(Shopping_cart)).build().perform();
			driver.findElement(Go_TO_cart).click();
			List<WebElement> cart_Items = driver.findElements(Remove_checkbox);
			for (int i = 1; i <= cart_Items.size(); i++) {
				try {
					driver.findElement(By.xpath("(//input[@name='removefromcart'])[" + i + "]")).click();
				} catch (Exception e) {
					System.out.println("Not clicked on Item checkbox");
				}
			}
			driver.findElement(updatecart).click();
			if (driver.findElement(cart_quantity).getText().equals("(0)")) {
				System.out.println("Cart  cleared");
			} else {
				System.out.println("cart  not  cleared");
			}
		}

	}

}
