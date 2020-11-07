package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.corba.se.spi.orbutil.fsm.Action;

public class Topic_11_UserInteraction {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC01_Hover_To_Element() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepSec(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");
	}
	@Test
	public void TC02_Hover_To_Element() throws InterruptedException {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navContent']//a[text()='Kids']"))).perform();
		sleepSec(1);
		driver.findElement(By.xpath("//div[@class='desktop-paneContent']//a[text()='Shirts']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumbs-list']//span[text()='Boy Shirts']")).isDisplayed());
	}
	@Test
	public void TC03_Hover_To_Element() {
		driver.get("https://hn.telio.vn/");
		action.moveToElement(driver.findElement(By.xpath("//div[@id='lefmenu-mobile']//span[text()='Bánh kẹo']"))).perform();
		
		List<WebElement> listElements= driver.findElements(By.xpath("//div[@id='lefmenu-mobile']//a[@href='/banh-keo?source=home']//following-sibling::ul//ul/li"));
		
		Assert.assertEquals(listElements.size(), 6);
	}
	@Test
	public void TC04_Click_And_Hold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> list = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		action.clickAndHold(list.get(0)).moveToElement(list.get(3)).release().perform();
		
		List<WebElement> listSelectedElements = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(listSelectedElements.size(), 4);
	}
	@Test
	public void TC05_Click_And_Select( ) {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> list = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).perform();
		list.get(0).click();
		list.get(4).click();
		list.get(5).click();
		action.keyUp(Keys.CONTROL).perform();
		
		List<WebElement> listSelectedElements = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(listSelectedElements.size(), 3);
	}
	@Test
	public void TC06_Double_Click() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepSec(1);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepSec(1);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
	}
	
	public void sleepSec(int second) throws InterruptedException {
		Thread.sleep(second * 1000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
