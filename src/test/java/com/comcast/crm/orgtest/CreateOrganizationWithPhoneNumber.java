package com.comcast.crm.orgtest;


import java.io.FileNotFoundException;

import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithPhoneNumber 
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
			String orgName = eLib.getDataFromExcel("org1",7,2)+ jLib.getRandomNumber();
			String phoneNumber = eLib.getDataFromExcel("org1",7,3)+ jLib.getRandomNumber();
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
	
	// phone number
	driver.findElement(By.id("phone")).sendKeys(phoneNumber);
	
	
	
	driver.findElement(By.name("button")).click();
	Thread.sleep(2000);
	
	
	// verify header phone Number info Expected Result

	
	String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
	if(actPhoneNumber.trim().contains(phoneNumber))
	{
		System.out.println(phoneNumber + "is created == PASS");
		}
	else
	{
		System.out.println(phoneNumber + "is not created == FAIL");
	}
	Thread.sleep(2000);
	/*
	 * // verify header message info Expected Result

	
	String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(headerInfo.trim().contains(orgName))
	{
		System.out.println(orgName + "is verified == PASS");
		}
	else
	{
		System.out.println(orgName + "is not verified == FAIL");
	}
	// verify header orgName info Expected Result

	
	String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
	if(actOrgName.trim().contains(orgName))
	{
		System.out.println(orgName + "is verified == PASS");
		}
	else
	{
		System.out.println(orgName + "is not verified == FAIL");
	}
	 * 
	 * 
	 * 
	 */
	
	
	// Step :5 logout
driver.quit();
	
}
}
