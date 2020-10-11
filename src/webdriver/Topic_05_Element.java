package webdriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element {
	WebDriver driver;
	WebElement element;
	By emailBy = By.id("mail") ;
	By ageBy = By.id("under_18");
	By educationBy = By.id("edu");
	By job1By = By.id("job1");
	By developmentBy = By.id("development");
	By slide01By = By.id("slider-1");
	By passwordDisableBy = By.id("password");
	By ageDisabledBy = By.id("radio-disabled");
	By biographyDisableBy = By.id("bio");
	By job3DisableBy = By.id("job3");
	By interestDisableBy = By.id("check-disbaled");
	By slider02DisableBy = By.id("slider-2");
	By javaBy = By.id("java");
	
	By emailChimpBy = By.id("email");
	By usernameChimpBy = By.id("new_username");
	By passwordChimpBy= By.id("new_password");
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC01_IsDisplayed() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if(isElementDisplay(emailBy)) {
			driver.findElement(emailBy).sendKeys("Automation Testing");
		}
		
		if(isElementDisplay(ageBy)) {
			driver.findElement(ageBy).click();
		}
		
		if(isElementDisplay(educationBy)) {
			driver.findElement(educationBy).sendKeys("Automation Testing");
		}
		Thread.sleep(3000);
	}
	
	@Test
	public void TC02_IsEnable() throws InterruptedException {
		driver.navigate().refresh();
		Assert.assertTrue(isElementEnable(emailBy));
		Assert.assertTrue(isElementEnable(ageBy));
		Assert.assertTrue(isElementEnable(educationBy));
		Assert.assertTrue(isElementEnable(job1By));
		Assert.assertTrue(isElementEnable(developmentBy));
		Assert.assertTrue(isElementEnable(slide01By));
		Assert.assertFalse(isElementEnable(passwordDisableBy));
		Assert.assertFalse(isElementEnable(ageDisabledBy));
		Assert.assertFalse(isElementEnable(biographyDisableBy));
		Assert.assertFalse(isElementEnable(job3DisableBy));
		Assert.assertFalse(isElementEnable(interestDisableBy));
		Assert.assertFalse(isElementEnable(slider02DisableBy));
		Thread.sleep(3000);
	}
	
	@Test
	public void TC03_IsSelected() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		driver.findElement(ageBy).click();
		driver.findElement(javaBy).click();
		
		Assert.assertTrue(isElementSelect(ageBy));
		Assert.assertTrue(isElementSelect(javaBy));
		
		driver.findElement(javaBy).click();
		Thread.sleep(3000);
	}
	
	@Test
	public void TC04_Combine() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(emailChimpBy).sendKeys("thyttn93@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("thyttn");
		
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		
		driver.findElement(passwordChimpBy).sendKeys("thy");
		Assert.assertTrue(isElementDisplay(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		driver.findElement(passwordChimpBy).clear();
		
		
		driver.findElement(passwordChimpBy).sendKeys("Thy");
		Assert.assertTrue(isElementDisplay(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		driver.findElement(passwordChimpBy).clear();
		
		driver.findElement(passwordChimpBy).sendKeys("Thy93");
		Assert.assertTrue(isElementDisplay(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		driver.findElement(passwordChimpBy).clear();
		
		driver.findElement(passwordChimpBy).sendKeys("Thy93@");
		Assert.assertTrue(isElementDisplay(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		driver.findElement(passwordChimpBy).clear();
		
		driver.findElement(passwordChimpBy).sendKeys("12345678");
		Assert.assertTrue(isElementDisplay(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		driver.findElement(passwordChimpBy).clear();
		
		driver.findElement(passwordChimpBy).sendKeys("Thyttn93@");
		Assert.assertTrue(isElementEnable(By.id("create-account")));
		driver.findElement(passwordChimpBy).clear();
	}
	
	public boolean isElementDisplay(By elementBy) {
		if(driver.findElement(elementBy).isDisplayed()) {
			System.out.println("Element" + elementBy + " is displayed");
			return true;
		}else {
			System.out.println("Element" + elementBy + " displayed");
			return false;
		}
	}
	
	public boolean isElementEnable(By elementBy) {
		if(driver.findElement(elementBy).isEnabled()) {
			System.out.println("Element" + elementBy + " is enable");
			return true;
		}else {
			System.out.println("Element" + elementBy + " is disable");
			return false;
		}
	}
	
	public boolean isElementSelect(By elementBy) {
		if(driver.findElement(elementBy).isSelected()) {
			System.out.println("Element" + elementBy + " is selected");
			return true;
		}else {
			System.out.println("Element" + elementBy + " is not selected");
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
