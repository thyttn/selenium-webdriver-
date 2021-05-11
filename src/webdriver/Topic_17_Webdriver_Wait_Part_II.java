package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Webdriver_Wait_Part_II {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 3);
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void TC01_Find_Element() {
		//Tìm element nhưng không tìm thấy element nào -> 0 matching node
		//Mỗi 0.5s tìm lại 1 lần cho đến khi hết timeout thì thôi.
		//Nếu tìm thấy element thì pass điều kiện
		//Nếu không tìm thây element thì throw exception noSuchElement
		//Tìm element tìm thấy 1 element -> 1 matching node
		
		//Tìm element tìm thấy nhiều element -> n matching node
	}
	
	public void TC02_Find_Elements() {
		//Tìm element nhưng không tìm thấy element nào -> 0 matching node
		//Mỗi 0.5s tìm lại 1 lần cho đến khi hết timeout thì thôi.
		//Nếu tìm thấy element thì pass điều kiện
		//Nếu không tìm thây element thì trả về list empty
		//Tìm element tìm thấy 1 element -> 1 matching node
				
		//Tìm element tìm thấy nhiều element -> n matching node
	}
	
	public void TC03_Static_Wait() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		Thread.sleep(3);
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		Thread.sleep(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
	}
	
	public void TC04_Implicit_Wait(){
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
	}
	
	@Test
	public void TC05_Explicit_Wait_Invisible(){
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']/img")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
	}
	
	
	public void TC06_Explicit_Wait_Visible(){
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
