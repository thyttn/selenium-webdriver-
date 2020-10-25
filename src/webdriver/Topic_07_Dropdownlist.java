package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;



public class Topic_07_Dropdownlist {
	WebDriver driver;
	org.openqa.selenium.support.ui.Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC01_Dropdownlist() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.name("user_job1")));
		Assert.assertFalse(select.isMultiple());
		select = new Select(driver.findElement(By.name("user_job2")));
		select.selectByVisibleText("Automation");
		Thread.sleep(500);
		select.selectByVisibleText("Manual");
		Thread.sleep(500);
		
		List<WebElement> setectedOptionElements = select.getAllSelectedOptions();
		
		for(WebElement option :setectedOptionElements) {
			System.out.println("List selected: " + option.getText());
		}
	}

	@Test
	public void TC02() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Jone");
		driver.findElement(By.id("LastName")).sendKeys("Jackson");
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText("1");
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("May");
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1980");
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(By.id("Email")).sendKeys("Jack"+ (new Random()).nextInt() + "@gmail.com");
		driver.findElement(By.id("Company")).sendKeys("Fsoft");
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		Thread.sleep(5000);
		driver.findElement(By.id("register-button")).click();
		Thread.sleep(10000);
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		driver.findElement(By.className("ico-account")).click();
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
