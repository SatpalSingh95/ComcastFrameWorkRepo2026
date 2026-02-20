package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.generic.objectrepositoryutility.HomePage;
import com.comcast.crm.generic.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.generic.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrgTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException {
		System.out.println("execute createOrgTest & verify");

		// Read TestScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.createOrg();
		op.getCreateNewOrgBtn().click();
		// enter all the details and create new Organization

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createorg(orgName);

		WebDriverUtility wb = new WebDriverUtility();
		wb.select(cnop.getIndustry(), 1);
		wb.select(cnop.getAccounttype(), 1);
		wb.select(cnop.getRating(), 1);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", cnop.getEmailoptout());
		// js.executeScript("arguments[0].click();", cnop.getEmailoptout());

		// js.executeScript("arguments[0].click();", cnop.getNotify_owner());
		// cnop.clickNotifyRadioBtn();
		// cnop.clickRadioBtn();

		// verify header msg expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is verified ==Pass");
		} else {
			System.out.println(orgName + "name is not verified ==Fail");

		}

	}

	@Test(groups = "regressionTest")
	public void createContactWithPhoneNumber() throws EncryptedDocumentException, IOException {
		System.out.println("execute createOrgWithIndustries & verify");

		// Read TestScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		// Read TestScript data from Excel file
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		OrganizationsPage op = new OrganizationsPage(driver);
		op.createOrg();
		op.getCreateNewOrgBtn().click();
		// enter all the details and create new Organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createorg(orgName);
		cnop.enterPhn(phoneNumber);

		cnop.getSaveBtn();

		// verify header phone Number info Expected Result

		String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actPhoneNumber.trim().contains(phoneNumber)) {
			System.out.println(phoneNumber + "is created == PASS");
		} else {
			System.out.println(phoneNumber + "is not created == FAIL");
		}
	}
	//@Test
	public void createOrganizationWithIndustries() throws Exception, IOException
	{
		// Read TestScript data from Excel file
				String orgName = eLib.getDataFromExcel("org1",4,2)+ jLib.getRandomNumber();
				String industry = eLib.getDataFromExcel("org1",4,3)+ jLib.getRandomNumber();
				String type = eLib.getDataFromExcel("org1",4,4)+ jLib.getRandomNumber();
			
		
		
		OrganizationsPage op = new OrganizationsPage(driver);
		op.createOrg();
		op.getCreateNewOrgBtn().click();
		// enter all the details and create new Organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createorg(orgName);
	

		cnop.getSaveBtn();


		

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
		

		
	}

}
