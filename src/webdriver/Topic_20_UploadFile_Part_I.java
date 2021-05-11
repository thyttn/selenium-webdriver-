package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_UploadFile_Part_I {
	WebDriver driver;
	String filePath = "E:\\Selenium\\02_Selenium API\\uploadFiles\\";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\02_Selenium API\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC01_One_File_Firefox() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Load file to web
		WebElement addFileButtonElement = driver.findElement(By.xpath("//input[@type='file']"));
		String image1PathString = filePath + "image1.jpg";
		addFileButtonElement.sendKeys(image1PathString);
		
		//Verify the loaded file
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='image1.jpg']")).isDisplayed());
		
		//Start to upload
		driver.findElement(By.xpath("//span[text()='Start']")).click();
		
		//Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='image1.jpg']")).isDisplayed());
	}
	

	@Test
	public void TC02_Mutiple_File() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Load file to web
		WebElement addFileButtonElement = driver.findElement(By.xpath("//input[@type='file']"));
		String image1Path = filePath + "image1.jpg";
		String image2Path = filePath + "image2.jpg";
		
		addFileButtonElement.sendKeys(image1Path + "\n" + image2Path);
		
		//Verify the loaded file
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='image1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='image1.jpg']")).isDisplayed());
		
		//Start to upload
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			Thread.sleep(1000);
		}
		
		
		//Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='image1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='image2.jpg']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
