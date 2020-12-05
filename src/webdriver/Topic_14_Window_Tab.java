package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Window_Tab {
	WebDriver driver;
	JavascriptExecutor jExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC01_Window_Tab() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Lay ID cua tab trước khi click
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepSec(3);
		
		switchToWindowByTitle("Google");
		sleepSec(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='hplogo']")).isDisplayed());
		
		//Switch to parent window
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		//click facebook link
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepSec(3);
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		//close all window except parent window
		closeAllWindowExceptParent("SELENIUM WEBDRIVER FORM DEMO");
	}
	
	public void TC02_Window_Tab() throws InterruptedException {
		driver.get("https://kyna.vn/");
		
		//jExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		//driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		clickToElementByJs("//div[@class='hotline']//img[@alt='facebook']");
		sleepSec(2);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepSec(2);
		//driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		clickToElementByJs("//div[@class='hotline']//img[@alt='youtube']");
		sleepSec(2);
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepSec(2);
		
		closeAllWindowExceptParent("Kyna.vn - Học online cùng chuyên gia");
	}
	
	@Test
	public void TC03_Window_Tab() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepSec(2);
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		sleepSec(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		sleepSec(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		String parentWindowID = driver.getWindowHandle();
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		sleepSec(2);
		
		swithToChildWindow(parentWindowID);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		closeAllWindowExceptParent("Mobile");
		driver.switchTo().window(parentWindowID);
		sleepSec(3);
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		driver.switchTo().alert().accept();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	}
	//2 window/tab
	public void swithToChildWindow(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childwindow : allWindows) {
			if(!childwindow.equals(parentWindow)) {
				driver.switchTo().window(childwindow);
			}
		}
	}
	
	//>=2 window/tab
	public void switchToWindowByTitle(String expectedWindowTitle) {
		//lấy ra tất cả các window/tabs hiện có
		Set<String> allWindows = driver.getWindowHandles();
		
		//Duyệt qua tất cả cả windows/tabs
		for (String actualWindowID : allWindows) {
			//Switch qua từng window/tab
			driver.switchTo().window(actualWindowID);
			String actualWindowTitle = driver.getTitle();
			//Nếu actual window title = expect window title
			if(actualWindowTitle.equals(expectedWindowTitle)) {
				break;
			}
		}
	}
	
	//close all window/tab ngoại trừ parent
	public void closeAllWindowExceptParent(String parentWindowTitle) {
		Set<String> allWindowIDs  = driver.getWindowHandles();
		
		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			String windowTitle = driver.getTitle();
			if(!windowTitle.equals(parentWindowTitle)) {
				driver.close();
			}
		}
	}
	
	public void clickToElementByJs(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		jExecutor.executeScript("arguments[0].click();", element);
	}
	public void sleepSec(int second) throws InterruptedException  {
		Thread.sleep(second*1000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
