package webdriver;

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

import javafx.fxml.JavaFXBuilderFactory;

public class Topic_09_Radiobuton_Checkbox {
	WebDriver driver;
	JavascriptExecutor jExecutor;

	By btnLogin = By.xpath("//button[@class='fhs-btn-login']");
	By chkDualzone = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
	By chkPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC01_Button() throws InterruptedException {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		Assert.assertFalse(isElementEnable(btnLogin));

		driver.findElement(By.id("login_username")).sendKeys("jone12@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");

		Assert.assertTrue(isElementEnable(btnLogin));

		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertFalse(isElementEnable(btnLogin));
		
		removeDisabledAttributebyJs(btnLogin);
		Assert.assertTrue(isElementEnable(btnLogin));
		driver.findElement(btnLogin).click();

		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='fhs-input-box checked-error']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='fhs-input-box fhs-input-display checked-error']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
	}

	@Test
	public void TC02_Default_Checkbox_Radiobutton() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		Thread.sleep(3000);
		clickByJs(chkDualzone);
		
		//Kiem tra checkbox duoc chon
		Assert.assertTrue(isSelected(chkDualzone));
		
		//Bo chon checkbox, kiem tra checkbox khong duoc chon
		clickByJs(chkDualzone);
		Assert.assertFalse(isSelected(chkDualzone));
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		//Kiem tra element click hay chua, neu chua thi click
		isClickOrNot(chkPetrol);
		
		//Kiem tra checkbox duoc chon
		Assert.assertTrue(isSelected(chkPetrol));
	}

	@Test
	public void TC03_Custom_Checkbox_Radiobutton() throws InterruptedException {
		driver.get("https://material.angular.io/components/radio/examples");
		Thread.sleep(3000);
		
		By radioSummerBy = By.xpath("//div[contains(text(),'Winter')]/preceding-sibling::div/input");
		clickByJs(radioSummerBy);
		Assert.assertTrue(isSelected(radioSummerBy));
		
		//Kiem tra element click chua, neu chua chon lai
		isClickOrNot(radioSummerBy);
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		By chkCheckBy = By.xpath("//span[text()='Checked']/preceding-sibling::div/input");
		clickByJs(chkCheckBy);
		Assert.assertTrue(isSelected(chkCheckBy));
		
		clickByJs(chkCheckBy);
		Assert.assertFalse(isSelected(chkCheckBy));
	}

	public void TC04() {

	}

	public boolean isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enable: " + by);
			return true;
		} else {
			System.out.println("Element is disable: " + by);
			return false;
		}
	}
	
	public boolean isSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element is selected: " + by);
			return true;
		} else {
			System.out.println("Element is not selected: " + by);
			return false;
		}
	}
	
	public void isClickOrNot(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			jExecutor.executeScript("arguments[0].click();", element);
		}
	}

	public void removeDisabledAttributebyJs(By by) {
		WebElement element = driver.findElement(by);
		jExecutor.executeScript("arguments[0].removeAttribute('disabled');", element);
	}

	public void clickByJs(By by) {
		WebElement element = driver.findElement(by);
		jExecutor.executeScript("arguments[0].click();", element);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
