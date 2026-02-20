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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.generic.objectrepositoryutility.HomePage;
import com.comcast.crm.generic.objectrepositoryutility.LoginPage;
import com.comcast.crm.generic.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.generic.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class DeleteOrgTest {
	public static void main(String[] args) throws Throwable {

		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// Read TestScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();

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
		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD, URL);

		// Step:2 navigate to Organization Module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// Step:3 click on "Create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		// step:4 enter all the details & create new organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createorg(orgName);

		// verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified === Pass");
		} else {
			System.out.println(orgName + " name is not verified === Fail");
		}

		// go back to Organization Page
		hp.getOrgLink().click();
		// search for Organization
		op.getSearchEdit().sendKeys(orgName);
		wLib.select(op.getSearchDD(), "Organization Name");
		op.getSearchButton().click();
		
		// In dynamic webtable select and delete org
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
driver.switchTo().alert().accept();
		// Step:5 logout
				hp.logout();
		driver.quit();

	}
}
