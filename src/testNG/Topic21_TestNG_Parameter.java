package testNG;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactoryFinder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic21_TestNG_Parameter {
	WebDriver driver;
	
	By emailTextbox = By.id("email"); 
	By passTextbox = By.id("pass"); 
	By loginButton = By.id("send2");
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName,String urlvalue) {
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\02_Selenium API\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\02_Selenium API\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else {
			throw new RuntimeException("Check browser again!");
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(urlvalue);
	}
	
	@Test()
	public void TC01_Login() {
		driver.findElement(emailTextbox).sendKeys("selenium_01_19@gmail.com");
		driver.findElement(passTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
