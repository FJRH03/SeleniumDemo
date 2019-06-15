package grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/*
 * This class is an Example of Selenium DesireCapabilities Driver
 * @author: Francisco Ramirez > @XXXKaos (GitHub)
 * Note: You must have already running your selenium standalone-server -jar file 
 */

public class TestGrid {
	
	static WebDriver driver;
	static String nodeURL;
	
	public static void main(String[] args) {
	
		try {
			//set the URL of your node here: http://yourURL:port/wd/node
			nodeURL="http://your_host:4444/wd/hub"; ///for example
			
			//In this case I will use geckodriver
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox"); //Set the name of your Browser here
			capabilities.setPlatform(Platform.WINDOWS); // Set the OS here
			
			//Now invoke the RC WebDriver
			driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
			
			//set up driver
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			//driver get URL
			driver.get("https://www.amazon.com.mx/");
			driver.findElement(By.linkText("Promociones")).click();
			
			System.out.println("Driver is navigating on: " + driver.getTitle());
			
			System.out.println("--Driver will close--");
			
			//closing WebDriver
			driver.close();
			
			System.out.println("Driver is closed");
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
