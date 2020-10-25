package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String userNameLogin ="mngr291834", passwordLogin="hujegUb";
	String currentURL, customerName, date, month, year, dateOfBirthInput, dateOfBirthOutput , addressInput, addressOutput, city, state, pin, mobileNumber,
			email, passwordCustomer, customerId;
	By customerIdTextbox = By.xpath("//*[text()='Customer ID']/following-sibling::td");
	By customerNameTextbox = By.xpath("//*[text()='Customer Name']/following-sibling::td/input");
	By dateOfBirthTextbox = By.xpath("//*[text()='Birthdate']/following-sibling::td");
	By addressTextbox = By.xpath("//*[text()='Address']/following-sibling::td");
	By cityTextbox = By.xpath("//*[text()='City']/following-sibling::td");
	By stateTextbox = By.xpath("//*[text()='State']/following-sibling::td");
	By pinTextbox = By.xpath("//*[text()='Pin']/following-sibling::td");
	By mobileNumberTextbox = By.xpath("//*[text()='Mobile No.']/following-sibling::td");
	By emailTextbox = By.xpath("//*[text()='Email']/following-sibling::td");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");

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

	@Test(enabled = false)
	public void TC01_Register() {
		currentURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()= 'here']")).click();
		driver.findElement(By.xpath("//input[@name= 'emailid']")).sendKeys("selenium12@gmail.com");
		driver.findElement(By.xpath("//input[@name= 'btnLogin']")).click();

		userNameLogin = driver.findElement(By.xpath("//td[contains(text(),'User ID')]/following-sibling::td")).getText();
		passwordLogin = driver.findElement(By.xpath("//td[contains(text(),'Password')]/following-sibling::td")).getText();
		System.out.println("userNameLogin" + userNameLogin);
		System.out.println("passwordLogin" + passwordLogin);
	}

	@Test
	public void TC02_Login() throws InterruptedException {
		driver.get("http://demo.guru99.com/v4/");
		//driver.get(currentURL);
		driver.findElement(By.name("uid")).sendKeys(userNameLogin);
		driver.findElement(By.name("password")).sendKeys(passwordLogin);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
		Thread.sleep(10000);
	}

	@Test(enabled = false)
	public void TC03_NewCustomer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(customerName);
		driver.findElement(By.name("dob")).sendKeys(dateOfBirthInput);
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
		
		Assert.assertEquals(driver.findElement(customerNameTextbox).getText(), customerName);
		Assert.assertEquals(driver.findElement(dateOfBirthTextbox).getText(), dateOfBirthOutput );
		Assert.assertEquals(driver.findElement(addressTextbox).getText(),addressOutput );
		Assert.assertEquals(driver.findElement(cityTextbox).getText(), city);
		Assert.assertEquals(driver.findElement(stateTextbox).getText(), state);
		Assert.assertEquals(driver.findElement(mobileNumberTextbox).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(emailTextbox).getText(), email);
	}

	@Test
	public void TC04_EditCustomer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys("25483");
		driver.findElement(By.name("AccSubmit")).click();
		Thread.sleep(10000);
		
		Assert.assertEquals(driver.findElement(customerNameTextbox).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(addressTextbox).getText(), addressInput);
		
		driver.findElement(By.name("telephoneno")).clear();
		
		driver.findElement(By.name("telephoneno")).sendKeys("099111222");
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(mobileNumberTextbox).getText(), "099111222");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
