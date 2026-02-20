package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationTest0 {
public static void main(String[] args) throws Throwable {

	FileUtility fLib=new FileUtility();
	ExcelUtility eLib=new ExcelUtility();
	JavaUtility jLib=new JavaUtility();
	
	String BROWSER =fLib.getDataFromPropertiesFile("browser");
	String URL =fLib.getDataFromPropertiesFile("url");
    String USERNAME = fLib.getDataFromPropertiesFile("username");
	String PASSWORD =fLib.getDataFromPropertiesFile("password");

	// Read TestScript data from Excel file
			String orgName = eLib.getDataFromExcel("org",1,2)+ jLib.getRandomNumber();
		
			WebDriver driver = null;
			if (BROWSER.equals("chrome")) {

				driver = new ChromeDriver();
			} else if (BROWSER.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (BROWSER.equals("edge")) {
				driver = new EdgeDriver();
			} else {
				driver = new ChromeDriver();

			}
			// step:1 login to app
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(URL);
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();

			// Step:2 navigate to Organization Module
			driver.findElement(By.linkText("Organizations")).click();

			// Step:3 click on "Create Organization" button
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

			// step:4 enter all the details & create new organization
			driver.findElement(By.name("accountname")).sendKeys(orgName);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			// Step:5 logout

			driver.quit();
}
}
