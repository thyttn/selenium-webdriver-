package webdriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Iframe_Window {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC01_Iframe() throws InterruptedException {
		driver.get("https://kyna.vn/");
		sleepSec(3);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'//www.facebook.com')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']")).getText(), "Kyna.vn");
		
		String likeString = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		int likeNumber =Integer.parseInt(likeString.split(" ")[0].replace("K", ""));
		
		assertThat(likeNumber, greaterThan(168));
		
		//step 4
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
		Assert.assertTrue(driver.findElement(By.xpath("//label[@class='greeting ng-scope ng-binding']")).isDisplayed());
		driver.findElement(By.xpath("//label[@class='greeting ng-scope ng-binding']")).click();
		
		//step 6
		driver.switchTo().defaultContent();
		Actions builder = new Actions(driver);
		WebElement searchElement = driver.findElement(By.id("live-search-bar"));
		Actions seriActions = builder
				.moveToElement(searchElement)
				.sendKeys(searchElement,"excel")
				.keyDown(searchElement, Keys.SHIFT);
		seriActions.perform();

		//driver.findElement(By.id("live-search-bar")).sendKeys("excel");
		
		List<WebElement> headerResults= driver.findElements(By.cssSelector("div.content h4")); 
		List<String> textResults = new ArrayList<>();
		
		for (WebElement header : headerResults) {
			textResults.add(header.getText());
		}
		
		for (String text : textResults) {
			Assert.assertTrue(text.contains("excel"));
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
