package docker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
 * This class is an Example of Selenium Docker Remote Driver
 * @author: Francisco Ramirez > @XXXKaos (GitHub)
 * Note: You must have already running your Selenium debug server on docker
 */

public class DockerStandAlone {

	
	static RemoteWebDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		
		System.out.println("Running Test in Docker <<Firefox>>");
		
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setPlatform(Platform.LINUX);
		cap.setVersion("");
	
		
	driver = new RemoteWebDriver(new URL("http://your_host:4444/wd/hub"), cap);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	
	@Test(description = "Search in Youtube")
	public void googleSearch() throws InterruptedException
	{
		driver.navigate().to("https://www.youtube.com/");
		driver.findElement(By.name("search_query")).sendKeys("Selenium - Edureka!");
		driver.findElement(By.id("search-icon-legacy")).click();
		System.out.println("Search in Firefox Completed");
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public static void tearDown() throws Exception
	{
		if(driver!=null) 
		{
			System.out.println("Completed Test in Docker Container <<Firefox>>");
			driver.quit();
		}
	}

}
