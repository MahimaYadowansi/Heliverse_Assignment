package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;

@Test
public class AddItemToCart extends BaseTest {
	public void addItemToCart() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Store the window handle of the first window
		String mainPage = driver.getWindowHandle();
		System.out.println("Main page: " + mainPage);
		
		// Search for the item
		WebElement search = driver.findElement(By.xpath(or.getProperty("search_xpath")));
		search.click();
		search.sendKeys("Refrigerator");
		search.sendKeys(Keys.ENTER);

		// Select brand
		WebElement brand = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("brand"))));
		brand.click();

		// Choose product from the list and click
		List<WebElement> refrigeratorList = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(or.getProperty("refrigerator_list"))));
		System.out.println(refrigeratorList.size());

		boolean productFound = false;
		for (WebElement list : refrigeratorList) {
			System.out.println(list.getText());
			if (list.getText().contains("Whirlpool 235 L Frost Free Triple Door Refrigerator")) {
				list.click();
				productFound = true;
				break;
			}
		}

		// Assert that the product was found and clicked
		Assert.assertTrue(productFound, "Product not found in the list.");


		// To add in cart
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cart = driver.findElement(By.xpath(or.getProperty("add_to_cart")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cart);
		try {
			cart.click();
			System.out.println("Cart is clicked");
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
		}
		
		
		
		
		
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Assert that the item was added to cart
		WebElement placeOrderBtn = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("place_order_btn"))));
		Assert.assertTrue(placeOrderBtn.isDisplayed(), "Item is not added to cart");

		System.out.println("Item is added to cart, please place your order");
		driver.close();

		
	}
}
