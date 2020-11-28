package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_PopUp {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 3);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC01_Fixed_Popup() throws InterruptedException {
		driver.get("https://www.zingpoll.com/");
		driver.findElement(By.id("Loginform")).click();
		WebElement popupElement = driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']"));
		Assert.assertTrue(popupElement.isDisplayed());
		if(popupElement.isDisplayed()) {
			driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']//button[@class='close']")).click();
			sleepSec(3);
			Assert.assertFalse(popupElement.isDisplayed());
		}
	}
	
	
	public void TC02_Fixed_Popup() throws InterruptedException {
		driver.get("https://bni.vn/");
		sleepSec(5);
		//Cho cho 1 element duoc hien thi
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		
		//Cho cho element co the duoc click
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']")));
		driver.findElement(By.xpath("//img[@alt='Close']")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
	}
	
	
	public void TC03_Random_Popup_In_DOM() throws InterruptedException {
		driver.get("https://blog.testproject.io/");
		sleepSec(7);
		WebElement popElement = driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']"));
		if(popElement.isDisplayed()) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='mailch-wrap rocket-lazyload']//div[@id='close-mailch']")));
			driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']//div[@id='close-mailch']")).click();
			sleepSec(3);
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='search-2']//input[@class='search-field']")));
		driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("selenium");
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		
		List<WebElement> listSearchs = driver.findElements(By.xpath("//div[@class='posts-wrap']/div/div[@class='post-content']/h3/a"));
		for (WebElement result : listSearchs) {
			Assert.assertTrue(result.getText().contains("selenium"));
		}
	}
	
	@Test
	public void TC04_Random_Popup_Not_In_DOM() throws InterruptedException {
		driver.get("https://shopee.vn/");
		
		sleepSec(7);
		
		List<WebElement> popup = driver.findElements(By.xpath("//div[@class='shopee-popup shopee-modal__transition-enter-done']"));
		if(popup.size()> 0 && popup.get(0).isDisplayed()) {
			//close popup
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='shopee-popup__close-btn']")));
			driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
		}else {
			System.out.println("close popup");
		}
	}
	
	public void sleepSec(int second) throws InterruptedException  {
		Thread.sleep(second*1000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
