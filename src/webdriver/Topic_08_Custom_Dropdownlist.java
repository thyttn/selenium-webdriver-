package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.sun.management.jmx.Trace;
import com.sun.org.apache.bcel.internal.generic.MULTIANEWARRAY;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.xml.internal.bind.v2.model.impl.ModelBuilderI;
import com.sun.xml.internal.txw2.Document;


public class Topic_08_Custom_Dropdownlist {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 3000);
		jExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC01_Jquery() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		sleepWait(1);
		selectItemInCustomDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//li", "Prof.");
		sleepWait(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']//span[text()='Prof.']")).isDisplayed());
		
		selectItemInCustomDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//li", "Other");
		sleepWait(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']//span[text()='Other']")).isDisplayed());
		
		selectItemInCustomDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//li", "Mr.");
		sleepWait(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']//span[text()='Mr.']")).isDisplayed());
	}
	
	@Test
	public void TC02_Angular() throws InterruptedException {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		sleepWait(1);
		selectItemInCustomDropdown("//*[@id='games']", "//ul[@id='games_options']//li", "Badminton");
		Assert.assertEquals(getHiddenText("select[id='games_hidden']>option"), "Badminton");
		
		sleepWait(1);
		selectItemInCustomDropdown("//*[@id='games']", "//ul[@id='games_options']//li", "Football");
		Assert.assertEquals(getHiddenText("select[id='games_hidden']>option"), "Football");
		
		sleepWait(1);
		selectItemInCustomDropdown("//*[@id='games']", "//ul[@id='games_options']//li", "Basketball");
		Assert.assertEquals(getHiddenText("select[id='games_hidden']>option"), "Basketball");

	}
	
	@Test
	public void TC0_ReactJS() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepWait(1);
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']//span", "Elliot Fu");
		sleepWait(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Elliot Fu");
		
	}
	
	public void selectItemInCustomDropdown(String parentXpath,String childXpath, String expectedValue) throws InterruptedException {
		//1.Click vao the cha de xo xuong tat ca cac item
		driver.findElement(By.xpath(parentXpath)).click();
		
		//2. Cho cho tat cac cac item duoc load het
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		//3.Lay het cac item dua vao list
		List<WebElement> itemElements = driver.findElements(By.xpath(childXpath));
		//4. Duyet qua tung item tren list
		for (WebElement item : itemElements) {
			//5. Moi lan duyet kiem tra xem item text co bang vs item ma minh chon hay khog
			if(item.getText().trim().equals(expectedValue)) {
				//6. Neu nhu tim thay item can click thi scroll den item do
				jExecutor.executeScript("arguments[0].scrollIntoView();", item);
				sleepWait(1);
				// 7. click vao item do
				item.click();
				break;
			}
		}
	}

	
	public void sleepWait(int second) throws InterruptedException {
		Thread.sleep(second*1000);
	}
	public String getHiddenText(String cssSelector) {
		return (String) jExecutor.executeScript("return document.querySelector(\"" + cssSelector + "\").textContent");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
