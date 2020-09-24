package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class Topic_04_XpathExercise {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}

  @Test()
  public void TC01_Login_Empty_Email_Password() throws InterruptedException {
	 driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//*[@title='Login']")).click();
	 Thread.sleep(1000);
	 String requiredEmail = driver.findElement(By.id("advice-required-entry-email")).getText();
	 Assert.assertEquals(requiredEmail, "This is a required field.");
	 String requiredPassword = driver.findElement(By.id("advice-required-entry-pass")).getText();
	 Assert.assertEquals(requiredPassword, "This is a required field.");
  }
  
  @Test
  public void TC02_Login_Invalid_Email() throws InterruptedException {
	  driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("123@123.123");
	 driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456");
	 driver.findElement(By.xpath("//*[@title='Login']")).click();
	 Thread.sleep(1000);
	 
	 String requiredEmail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
	 Assert.assertEquals(requiredEmail, "Please enter a valid email address. For example johndoe@domain.com.");
  }
  
  @Test
  public void TC03_Login_Invalid_Password() throws InterruptedException {
	  driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("automation@gmail.com");
	 driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("1234");
	 driver.findElement(By.xpath("//*[@title='Login']")).click();
	 Thread.sleep(1000);
	 String requiredPassword = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
	 Assert.assertEquals(requiredPassword, "Please enter 6 or more characters without leading or trailing spaces.");
  }
  
  @Test
  public void TC04_Login_Incorrect_Email_Password() throws InterruptedException {
	  driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("automation@gmail.com");
	 driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456478");
	 driver.findElement(By.xpath("//*[@title='Login']")).click();
	 Thread.sleep(1000);
	 String errorMessage = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
	 Assert.assertEquals(errorMessage, "Invalid login or password.");
  }

  @Test
  public void TC05_Login_Valid_Email_Password() throws InterruptedException {
	 String myEmailString = "automation_13@gmail.com";
	 driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys(myEmailString);
	 driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123123");
	 driver.findElement(By.xpath("//*[@title='Login']")).click();
	 
	 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='my-account']//h1[text()='My Dashboard']")).isDisplayed());
	 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='my-account']//strong[text()='Hello, Automation Testing!']")).isDisplayed());
	 
	 String accountInforString = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p")).getText();	
	 Assert.assertTrue(accountInforString.contains("Automation Testing"));
	 Assert.assertTrue(accountInforString.contains(myEmailString));
  }

	@Test
	public void TC06_Create_Account() throws InterruptedException {
		String firstName = "Jack";
		String lastName = "Json";
		Random random = new Random();
		int number = random.nextInt(999999);
		String email = firstName + lastName + number + "@gmail.com";
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@title='First Name']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@title='Last Name']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@title='Confirm Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		String successMessage = driver.findElement(By.xpath("//li['success-msg']//span")).getText();
		Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");

		String myAccount = driver.findElement(By.xpath("//div[@class='col-1']//p")).getText();
		Assert.assertTrue(myAccount.contains(firstName));
		Assert.assertTrue(myAccount.contains(lastName));
		Assert.assertTrue(myAccount.contains(email));
		Thread.sleep(10000);

		// Logout
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();

		// Check Home page
		String welcomeMes = driver.findElement(By.xpath("//p[contains(text(),'Default welcome msg!')]")).getText();
		Assert.assertEquals(welcomeMes, "DEFAULT WELCOME MSG!");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
