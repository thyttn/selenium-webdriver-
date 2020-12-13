package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javafx.stage.Popup;

public class Topic_16_Webdriver_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	}

	
	public void TC01_Element_Visible() {
		//wait cho element hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	}
	
	
	public void TC02_Element_InVisible_UnDisplayed_In_DOM() {
		//Tìm element đầu tiên, Khi tìm thấy element thì thực hiện điều kiện invisible ngay
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[text()='There is 1 error']")));
	}
	
	
	public void TC03_Element_InVisible_Out_DOM() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//Tìm elent đầu tiên, khi không tìm thấy element thực hiện chờ implicitwait(Tìm đi tìm lại nhiều lần
		//cho đến khi hết timeout của implicit) mới apply điều kiện của explicit wait
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[text()='There is 1 error_update']")));
	}
	
	
	public void TC04_Element_Present_In_UI() {
		//Element in DOM + in UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		
		//Element in DOM + not in UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='There is 1 error']")));
	}
	
	
	public void TC05_Element_Clickable() {
		//Button/Radio Button/Dropdownlist/Link/Check box
	}
	
	@Test
	public void TC06_Element_Staleness() {
		driver.get("https://shopee.vn/");
		//Wait cho element staleless
		//Element không có/còn ở trong DOM
		WebElement popup = driver.findElement(By.xpath("//div[@class='shopee-popup__container']"));
		//Step 1: Thao tác vs element -> error message(*) xuất hiện - hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='shopee-popup__container']")));
		//Step 2: Thao tác một số hành động - (*) không còn xuất hiện
		driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
		//Step 3: Cần chờ cho (*) không còn trong DOM nữa
		explicitWait.until(ExpectedConditions.stalenessOf(popup));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
