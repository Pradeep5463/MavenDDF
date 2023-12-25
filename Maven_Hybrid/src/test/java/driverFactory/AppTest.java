package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil{
	String inputpath = "./FileInput/DataEngine.xlsx";
	String outputpath = "./FileOutput/HybridResults.xlsx";
	String TCSheet = "TestCases";
	String TSSheet = "TestSteps";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		boolean res = false;
		String tcres = "";
		//create object for excelfileutil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no. of rows in TCsheet and TSsheet
		int TCcount = xl.rowCount(TCSheet);
		int TSCount = xl.rowCount(TSSheet);
		Reporter.log("No. of rows in TCSheet::"+TCcount+"No. of rows in TSSheet"+TSCount,true);
		//iterate all rows in TCsheet
		for(int i=1;i<=TCcount;i++)
		{
			//read module name cell
			String Modulename = xl.getCellData(TCSheet, i, 1);
			//define html path
			report = new ExtentReports("./target/ReportsHybrid/"+Modulename+".html");
			logger = report.startTest(Modulename);
			//read module status cell
			String modulestatus = xl.getCellData(TCSheet, i, 2);
			if(modulestatus.equalsIgnoreCase("Y"))
			{
			//read tcid cell from TCSheet
				String tcid = xl.getCellData(TCSheet, i, 0);
				//iterate all rows in TSSheet
				for(int j=1;j<=TSCount;j++)
				{
					//read description cell data
					String Description = xl.getCellData(TSSheet, j, 2);
					//read tcid from TSSheet
					String tsid =xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						//read keyword cell from TSsheet
						String keyword = xl.getCellData(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("adminLogin"))
						{
							String para1 = xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							res =FunctionLibrary.adminLogin(para1, para2);
							logger.log(LogStatus.INFO, Description);
						}
					
						if(keyword.equalsIgnoreCase("branchCreation"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							String para3 =xl.getCellData(TSSheet, j, 7);
							String para4 =xl.getCellData(TSSheet, j, 8);
							String para5 =xl.getCellData(TSSheet, j, 9);
							String para6 =xl.getCellData(TSSheet, j, 10);
							String para7 =xl.getCellData(TSSheet, j, 11);
							String para8 =xl.getCellData(TSSheet, j, 12);
							String para9 =xl.getCellData(TSSheet, j, 13);
							FunctionLibrary.clickBranches();
							res =FunctionLibrary.branchCreation(para1, para2, para3, para4, para5, para6, para7, para8, para9);
							
							logger.log(LogStatus.INFO, Description);
						}
						if(keyword.equalsIgnoreCase("branchUpdation"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							String para5 =xl.getCellData(TSSheet, j, 9);
							String para6 =xl.getCellData(TSSheet, j, 10);
							FunctionLibrary.clickBranches();
							res = FunctionLibrary.branchUpdation(para1, para2, para5, para6);
							logger.log(LogStatus.INFO, Description);
							
						}
						if(keyword.equalsIgnoreCase("adminLogout"))
						{
							res =FunctionLibrary.adminLogout();
							
						}
						String tsres ="";
						if(res)
						{
							//if res is true write as pass into status cell in TSsheet
							tsres="pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							logger.log(LogStatus.PASS, Description);
							
						}
						else
						{
							////if res is false write as fail into status cell in TSsheet
							tsres="Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							logger.log(LogStatus.FAIL, Description);
							
						}
						tcres=tsres;
					}
					report.endTest(logger);
					report.flush();
				}
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			
		}
			else
			{
				//write as blocked in to status cell in TCSheet which are flag to N
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
	}

}
}
