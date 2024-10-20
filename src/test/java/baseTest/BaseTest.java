package baseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static FileInputStream fis1;
	public static WebDriverWait wait;

	public static ExtentTest test;
	

	@BeforeSuite
	public void setup() throws IOException, InterruptedException {
		if (driver == null) {
			// getting data from config
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
			config.load(fis);

			// getting data from Or
			fis1 = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\or.properties");
			or.load(fis1);

		}
		if (config.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (config.getProperty("browser").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (config.getProperty("browser").equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.get(config.getProperty("testsuiteurl"));
		
		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	}
	
	/* @AfterSuite 
	 public void tearDown() throws InterruptedException { 
		 if (driver!= null) { 
		 Thread.sleep(10000); 
		 driver.quit(); 
		 }
	  
	 }*/
	 

}
