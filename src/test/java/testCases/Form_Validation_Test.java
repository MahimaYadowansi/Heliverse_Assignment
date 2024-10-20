package testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
@Test
public class Form_Validation_Test  {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  @BeforeTest
	  
	  public void setUp() throws Exception {
	    driver = new ChromeDriver();
	    baseUrl = "http://only-testing-blog.blogspot.in/";
	   
	  }

	  @Test
	  public void testForm() throws Exception {
		  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
	    driver.get(baseUrl);
	    try {
	      assertEquals("Form", driver.findElement(By.linkText("Form")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.linkText("Form")).click();
	    driver.findElement(By.name("FirstName")).clear();
	    driver.findElement(By.name("FirstName")).sendKeys("Mahima");
	    driver.findElement(By.name("LastName")).clear();
	    driver.findElement(By.name("LastName")).sendKeys("Kumari");
	    driver.findElement(By.name("EmailID")).clear();
	    driver.findElement(By.name("EmailID")).sendKeys("abc@email.com");
	    driver.findElement(By.name("MobNo")).clear();
	    driver.findElement(By.name("MobNo")).sendKeys("7823387344");
	    driver.findElement(By.name("Company")).clear();
	    driver.findElement(By.name("Company")).sendKeys("Heliverse");
	    assertEquals("Only Testing: Form", driver.getTitle());
	    driver.findElement(By.xpath("//input[@value='Submit']")).click();
	    
	  }


	@AfterTest
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	}
	


