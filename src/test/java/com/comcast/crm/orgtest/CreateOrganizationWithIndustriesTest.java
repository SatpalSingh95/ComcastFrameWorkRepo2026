package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustriesTest 
{
public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ParseException 
{

	FileUtility fLib=new FileUtility();
	ExcelUtility eLib=new ExcelUtility();
	JavaUtility jLib=new JavaUtility();
	
	String BROWSER =fLib.getDataFromPropertiesFile("browser");
	String URL =fLib.getDataFromPropertiesFile("url");
    String USERNAME = fLib.getDataFromPropertiesFile("username");
	String PASSWORD =fLib.getDataFromPropertiesFile("password");

	// Read TestScript data from Excel file
			String orgName = eLib.getDataFromExcel("org1",4,2)+ jLib.getRandomNumber();
			String industry = eLib.getDataFromExcel("org1",4,3)+ jLib.getRandomNumber();
			String type = eLib.getDataFromExcel("org1",4,4)+ jLib.getRandomNumber();
			
			WebDriver driver=null;
			
			if(BROWSER.equals("chrome"))
			{
				
				driver=new ChromeDriver();
				
			}
				else if(BROWSER.equals("firefox"))
				{
					driver=new FirefoxDriver();
			}
				else if(BROWSER.equals("edge"))
				{
					driver=new EdgeDriver();
				}
				else
				{
					driver=new ChromeDriver();
				}
			
			System.out.println(BROWSER);
			
 // step1: login to the Application
	driver.get(URL);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
	
	//Thread.sleep(2000);
	driver.findElement(By.linkText("Organizations")).click();
	

	// Step:3 click on "Create Organization " Button
	
	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	
	// Step 4 enter all the details and create new Organization
	
	driver.findElement(By.name("accountname")).sendKeys(orgName);
	Thread.sleep(2000);
	WebElement wbselec1=  driver.findElement(By.name("industry"));
	Select sell1 =new Select(wbselec1);
	sell1.selectByVisibleText(industry);
	
	WebElement wbselec2=  driver.findElement(By.name("accounttype"));
	
	Select sell2 =new Select(wbselec2);
	sell2.selectByVisibleText(type);
	
	
	driver.findElement(By.name("button")).click();
	Thread.sleep(2000);
	
	// verify the drop down industry and type info
	String actIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
	if(actIndustries.equals(industry))
	{
		System.out.println(industry + " information is verified == PASS");
		}
	else
	{
		System.out.println(industry+ " information is not verifed == FAIL");
	}
	
	
	String actType=driver.findElement(By.id("dtlview_Type")).getText();
	if(actType.equals(type))
	{
		System.out.println(type + " information is verified == PASS");
		}
	else
	{
		System.out.println(type+ " information is not verifed == FAIL");
	}
	
	// Step :5 logout
driver.quit();

	
	
	
	
}
}
