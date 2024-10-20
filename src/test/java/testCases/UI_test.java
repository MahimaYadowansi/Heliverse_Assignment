//Home Page UI test
package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;



import baseTest.BaseTest;

@Test
public class UI_test extends BaseTest {
	// Store the window handle of the first window
    String mainPage;

    // Method to switch to the new window
    public void switchToNewWindow() {
        Set<String> allPages = driver.getWindowHandles();
        for (String page : allPages) {
            if (!page.equals(mainPage)) {
                driver.switchTo().window(page);
                break;
            }
        }
    }

    @Override
    public void setup() throws IOException, InterruptedException {
        super.setup();
        mainPage = driver.getWindowHandle();
    }

    public void clickWithRetry(WebElement element) {
        for (int i = 0; i < 3; i++) {
            try {
                // Scroll to the element
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                
                // Click the element
                element.click();
                return; // Exit if click is successful
            } catch (ElementClickInterceptedException e) {
                try {
                    Thread.sleep(500); // Wait before retrying
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        Assert.fail("Unable to click on the element after retries.");
    }

    @Test(priority = 1)
    public void flipkartImgView() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement flipkartImg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("Flipkart_img"))));

        Assert.assertTrue(flipkartImg.isDisplayed(), "Flipkart image should be displayed.");
        System.out.println("Flipkart image is viewed");

        switchToNewWindow();
    }

    @Test(priority = 2)
    public void helpDropDownClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement helpDropDown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("help_dropdown"))));

        clickWithRetry(helpDropDown);
        System.out.println("Help Drop Down is working as expected");
        switchToNewWindow();
    }

    @Test(priority = 3)
    public void addToCartClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("addToCart_click"))));

        clickWithRetry(addToCartClick);
        switchToNewWindow();
        System.out.println("Add To Cart is working as expected");
    }

    @Test(priority = 4)
    public void sellerStoriesClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sellerStories = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("seller_Stories"))));

        clickWithRetry(sellerStories);
        System.out.println("Seller Stories is working as expected");
        driver.navigate().to("https://www.flipkart.com/");
    }

    @Test(priority = 5)
    public void loginBtnClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("login_btn_clickable"))));

        clickWithRetry(loginBtn);
        System.out.println("Login button is working as expected");
        driver.navigate().to("https://www.flipkart.com/");
    }

    @Test(priority = 6)
    public void uiTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("search_xpath"))));

        clickWithRetry(search);
        search.sendKeys("Refrigerator");
        search.sendKeys(Keys.ENTER);
        
        System.out.println("Search bar works as expected");
        driver.navigate().to("https://www.flipkart.com/");
    }
    
}
