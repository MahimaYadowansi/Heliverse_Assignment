package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class Login_Test  {
	 WebDriver driver;
	    WebDriverWait wait;

	    
		@BeforeTest
	    public void setUp() {
	        // Set up the WebDriver and launch the browser
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
	        
	        // Maximize the window and navigate to the login page
	        driver.manage().window().maximize();
	        driver.get("https://practicetestautomation.com/practice-test-login/");
	        
	        // Set up an explicit wait
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    @Test
	    public void testLogin() {
	        // Find the username input field and enter the username
	        WebElement usernameField = driver.findElement(By.id("username"));
	        usernameField.sendKeys("student");
	        
	        // Find the password input field and enter the password
	        WebElement passwordField = driver.findElement(By.id("password"));
	        passwordField.sendKeys("Password123");
	        
	        // Find the login button and click on it
	        WebElement loginButton = driver.findElement(By.xpath("//button[@id='submit']"));
	        loginButton.click();
	        
	        // Wait for the success message to be visible after login
	        WebElement successMessage = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Logged In Successfully']")));
	        
	        // Assert that the success message is displayed
	        AssertJUnit.assertTrue(successMessage.isDisplayed());
	        
	        System.out.println("Login was successful. Message displayed: " + successMessage.getText());
	    }

	    
		@AfterTest
	    public void tearDown() {
	       driver.close();
	    }
	

}
