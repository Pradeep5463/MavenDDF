package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
	public static WebDriver driver;
	public static Properties conpro;
	@BeforeSuite
	public static void setUp() throws Throwable 
	{
	 conpro = new Properties();
	 conpro.load(new FileInputStream("./PropertyFiles/Environment.Properties"));
	 if(conpro.getProperty("Browser").equalsIgnoreCase("Chrome"))
	 {
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get(conpro.getProperty("Url"));
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 }
	 if(conpro.getProperty("Browser").equalsIgnoreCase("Firefox"))
	 {
		 driver =new FirefoxDriver();
		 driver.get(conpro.getProperty("Url"));
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 }
	 else
	 {
		 Reporter.log("Browser value not matching",true);
	 }
	 
	}
	@AfterSuite
	public static void tearDown()
	{
		driver.quit();
	}

}
