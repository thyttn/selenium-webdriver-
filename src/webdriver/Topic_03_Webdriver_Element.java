package webdriver;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Webdriver_Element {
	WebDriver driver;

	  @BeforeClass
	  public void beforeClass() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	  }
	 
	  @Test
	  public void TC01_Verify_URL() {
	 driver.get("http://live.demoguru99.com");
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 
	 driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	 Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	 
	 driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	 Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	  }
	 
	  @Test
	  public void TC02_Verify_Title() {
	 driver.get("http://live.demoguru99.com");
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 
	 driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	 Assert.assertEquals(driver.getTitle(), "Customer Login");
	 
	 driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
	 Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	  }
	 
	  @Test
	  public void TC03_Navigate_Function() {
	 driver.get("http://live.demoguru99.com");
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 
	 driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	 driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
	 Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	 
	 driver.navigate().back();
	 Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	 
	 driver.navigate().forward();
	 Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	  }
	 
	  @Test
	  public void TC04_Get_Page_Source_Code() {
	 driver.get("http://live.demoguru99.com");
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 
	 driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	 Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
	 
	 driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
	 Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	  }

	  @AfterClass
	  public void afterClass() {
	 driver.quit();
	  }

}
