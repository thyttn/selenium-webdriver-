package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import common.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {
	WebDriver driver;
	javascriptFunctions jsScript;
	String html5Message;
	
	String currentURL, customerName, date, month, year, dateOfBirthInput, dateOfBirthOutput , addressInput, addressOutput, city, state, pin, mobileNumber,
			email, passwordCustomer, customerId;
	By customerIdTextbox = By.xpath("//*[text()='Customer ID']/following-sibling::td");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsScript = new javascriptFunctions();
		
		customerName = "Selenium test";
		date = "01";
		month = "11";
		year = "1993";
		dateOfBirthInput = date + "/" + month + "/" + year;
		dateOfBirthOutput = year + "-" + date + "-" + month;
		addressInput = "12 Ta Thanh Oai\nThanh Tri\nHa Noi";
		addressOutput = addressInput.replace("\n", " ");
		city = "Ha Noi";
		state = "Thanh Tri";
		pin = "123456";
		mobileNumber = "098222333";
		email = "test" + (new Random()).nextInt(9999) + "@gmail.com";
		passwordCustomer = "666666";
	}

	public void TC01_Javascript_Executor() throws InterruptedException {
		jsScript.navigateToUrlByJS(driver, "http://live.demoguru99.com/");
		sleepSec(2);
		
		String pageDomain = (String) jsScript.executeForBrowser(driver, " return document.domain;");
		System.out.println("Page Domain: " + pageDomain);
		Assert.assertEquals(pageDomain, "live.demoguru99.com");
		
		String homePageUrl = (String) jsScript.executeForBrowser(driver, "return document.URL;");
		System.out.println("Home page URL: " + homePageUrl);
		Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/");
		
		jsScript.highlightElement(driver, "//a[text()='Mobile']");
		jsScript.clickToElementByJS(driver, "//a[text()='Mobile']");
		sleepSec(2);
		
		
		jsScript.highlightElement(driver, "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
		jsScript.clickToElementByJS(driver, "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
		Assert.assertTrue(jsScript.areExpectedTextInInnerText(driver, "Samsung Galaxy was added to your shopping cart."));
		
		jsScript.highlightElement(driver, "//a[text()='Customer Service']");
		jsScript.clickToElementByJS(driver, "//a[text()='Customer Service']");
		sleepSec(2);
		
		String customerServiceTitle = (String) jsScript.executeForBrowser(driver, "return document.title;");
		System.out.println("Page Domain: " + customerServiceTitle);
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		jsScript.scrollToElement(driver, "//input[@id='newsletter']");
		jsScript.scrollToBottomPage(driver);
		
		String email = "testGuru" + (new Random()).nextInt(9999) + "@gmail.com";
		jsScript.sendkeyToElementByJS(driver, "//input[@id='newsletter']", email);
		
		jsScript.clickToElementByJS(driver, "//span[text()='Subscribe']");
		sleepSec(2);
		
		jsScript.areExpectedTextInInnerText(driver, "Thank you for your subscription.");
		
		jsScript.navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");
		sleepSec(2);
		
		String pageDomain1 = (String) jsScript.executeForBrowser(driver, "return document.domain;");
		System.out.println("Page Domain: " + pageDomain1);
		Assert.assertEquals(pageDomain1, "demo.guru99.com");
		
	}
	
	
	public void TC02_Verify_HTML5_Validation_Message() throws InterruptedException {
		driver.get("https://automationfc.github.io/html5/index.html");
		sleepSec(2);
		
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		html5Message = jsScript.getElementValidationMessage(driver, "//input[@name='fname']");
		Assert.assertEquals(html5Message, "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@name='fname']")).sendKeys("Thy trinh");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		
		html5Message = jsScript.getElementValidationMessage(driver, "//input[@name='pass']");
		Assert.assertEquals(html5Message, "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		
		html5Message = jsScript.getElementValidationMessage(driver, "//input[@name='em']");
		Assert.assertEquals(html5Message, "Please fill out this field.");
		
	}
	
	public void TC04_Remove_Attribute() throws InterruptedException {
		driver.get("http://demo.guru99.com/v4");
		//User ID :	mngr299491
		//Password :	AqymavA
		//Manger Id : mngr299491
		sleepSec(2);
		driver.findElement(By.name("uid")).sendKeys("mngr299491");
		driver.findElement(By.name("password")).sendKeys("AqymavA");
		driver.findElement(By.name("btnLogin")).click();
		sleepSec(2);
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(customerName);
		
		jsScript.removeAttributeInDOM(driver, "//input[@id='dob']", "type");
		sleepSec(2);
		
		driver.findElement(By.name("dob")).sendKeys(dateOfBirthInput);
		sleepSec(2);
		
		driver.findElement(By.name("addr")).sendKeys(addressInput);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pin);
		driver.findElement(By.name("telephoneno")).sendKeys(mobileNumber);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(passwordCustomer);

		driver.findElement(By.name("sub")).click();
		Thread.sleep(10000);
		
		customerId = driver.findElement(customerIdTextbox).getText();
		 // customerId:25483
		System.out.println("customerId:" + customerId);
	}
	
	@Test
	public void  TC05_Remove_Attribute() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		sleepSec(3);
		
		jsScript.clickToElementByJS(driver, "//div[@id='header-account']//a[@title='My Account']");
		sleepSec(3);
	}
	public void sleepSec(int second) throws InterruptedException  {
		Thread.sleep(second*1000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
