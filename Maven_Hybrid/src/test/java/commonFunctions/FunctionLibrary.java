package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	/*ProjectName = Primus Bank
	 * ModuleName = Admin Module
	 * TesterName = Pradeep
	 * CreationDate = 27/09/2023
	 */
	//methods for login
	public static boolean adminLogin(String user,String pass)
	{
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();
		String Expected = "adminflow";
		String Actual = driver.getCurrentUrl();
		if(Actual.toLowerCase().contains(Expected.toLowerCase()))
		{
			Reporter.log("Login Success::"+Expected+"  "+Actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+Expected+"  "+Actual,true);
			return false;
		}
		
	}
	//method for click branches button
	public static void clickBranches()
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjBranches"))).click();
	}
	//method for branch creation
	public static boolean branchCreation(String Branchname,String Address1,String Address2,String Address3,String Area,String Zipcode,String Country,String State,String City) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjNewBranch"))).click();
		driver.findElement(By.xpath(conpro.getProperty("ObjBranchName"))).sendKeys("Branchname");
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress1"))).sendKeys("Address1");
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress2"))).sendKeys("Address2");
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress3"))).sendKeys("Address3");
		driver.findElement(By.xpath(conpro.getProperty("ObjArea"))).sendKeys("Area");
		driver.findElement(By.xpath(conpro.getProperty("ObjZipcode"))).sendKeys("Zipcode");
		new Select(driver.findElement(By.xpath(conpro.getProperty("ObjCountry")))).selectByVisibleText(Country);
		new Select(driver.findElement(By.xpath(conpro.getProperty("ObjState")))).selectByVisibleText(State);
		new Select(driver.findElement(By.xpath(conpro.getProperty("ObjCity")))).selectByVisibleText(City);
		driver.findElement(By.xpath(conpro.getProperty("ObjSubmitButton"))).click();
		//capture alert message
		String Expected_Alert = driver.switchTo().alert().getText();
		Thread.sleep(4000);
		driver.switchTo().alert().accept();
		String Actual_Alert = "New Branch with id";
		if(Expected_Alert.toLowerCase().contains(Actual_Alert.toLowerCase()))
		{
			Reporter.log(Expected_Alert,true);
			return true;
		}
		else
		{
			Reporter.log("New Branch Creation Fail",true);
			return false;
		}
		
	}
	//method for branch updation
	public static boolean branchUpdation(String BranchName,String Address1,String Area,String Zipcode) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjEdit"))).click();
		WebElement element1 = driver.findElement(By.xpath(conpro.getProperty("ObjBranch")));
		element1.clear();
		element1.sendKeys(BranchName);
		WebElement element2 = driver.findElement(By.xpath(conpro.getProperty("ObjAddress")));
		element2.clear();
		element2.sendKeys(Address1);
		WebElement element3 = driver.findElement(By.xpath(conpro.getProperty("ObjArea1")));
		element3.clear();
		element3.sendKeys(Area);
		WebElement element4 = driver.findElement(By.xpath(conpro.getProperty("ObjZip")));
		element4.clear();
		element4.sendKeys(Zipcode);
		driver.findElement(By.xpath(conpro.getProperty("ObjUpdateButton"))).click();
		String Exp_Alret = driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		String Act_Alert = "Branch Updated";
		if(Exp_Alret.toLowerCase().contains(Act_Alert.toLowerCase()))
		{
			Reporter.log(Exp_Alret,true);
			return true;
		}
		else
		{
			Reporter.log("Unable to update Branch",true);
			return false;
		}
		
	}
	//method for logout
	public static boolean adminLogout()
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
		if(driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).isDisplayed())
		{
			Reporter.log("Logout Success::",true);
			return true;
		}
		else
		{
			Reporter.log("Logout Fail::",true);
			return false;
		}
	}



}
