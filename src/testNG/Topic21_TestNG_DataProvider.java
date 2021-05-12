package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic21_TestNG_DataProvider {
	WebDriver driver;
	
	By emailTextbox = By.id("email"); 
	By passTextbox = By.id("pass"); 
	By loginButton = By.id("send2");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\02_Selenium API\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "user_pass")
	public void TC01_Login(String userName, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys(userName);
		driver.findElement(passTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	@DataProvider(name="user_pass")
	public String[][] UserAndPassData(){
		return new String[][] {
			{"selenium_01_19@gmail.com","111111"},
			{"selenium_02_19@gmail.com","111111"},
			{"selenium_03_19@gmail.com","111111"}
		};
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
