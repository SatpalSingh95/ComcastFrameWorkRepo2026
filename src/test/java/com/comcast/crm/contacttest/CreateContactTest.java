package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.listenerutility.ListImpClass;
import com.comcast.crm.generic.objectrepositoryutility.ContactPage;
import com.comcast.crm.generic.objectrepositoryutility.HomePage;
import com.comcast.crm.generic.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.objectrepositoryutility.CreateNewOrganizationLookUp;

// align the code ctrl+shift+F
/**
 * @author Satpal Singh
 * 
 */
public class CreateContactTest extends BaseClass {

	@Test(groups = { "smokeTest", "regressionTest" })
	public void createContactTest() throws Throwable {
		//Test 1
		//ListImpClass.test.log(Status.INFO,"read data from excel");
		UtilityClassObject.getTest().log(Status.INFO, "read data drom excel");

		/* Read TestScript data from Excel file */
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step:2 navigate to contact module
		UtilityClassObject.getTest().log(Status.INFO, " navigate to contact module");

		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// Step:3 click on "Create Contacts " Button
		UtilityClassObject.getTest().log(Status.INFO, "click on \"Create Contacts \" Button");

		ContactPage cp = new ContactPage(driver);
		cp.createContact();

		// Step:4 enter all the details and create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "enter all the details and create new Organization");

		cp.getLastName().sendKeys(lastName);
		cp.getSaveButton().click();

		// Step:5 verify header, lastName, phone Number info & Expected Result
		UtilityClassObject.getTest().log(Status.INFO, "verify header, lastName, phone Number info & Expected Result");

		String actHeader = cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);

		String actLastName = cp.getLastNameVerification().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastName);
		soft.assertAll();
		/*
		 * String actHeader =
		 * driver.findElement(By.className("dvHeaderText")).getText(); if
		 * (actHeader.trim().contains(lastName)) { System.out.println(lastName +
		 * " Header is verified == PASS"); } else { System.out.println(lastName +
		 * " Header is not verified == FAIL"); }
		 * 
		 * String actLastName =
		 * driver.findElement(By.id("dtlview_Last Name")).getText(); if
		 * (actLastName.trim().contains(lastName)) { System.out.println(lastName +
		 * "Information is verified == PASS"); } else { System.out.println(lastName +
		 * "Information is not verified == FAIL"); }
		 */

	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDate() throws EncryptedDocumentException, IOException {

		// Read TestScript data from Excel file
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// step:2 navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// Step:3 click on "Create Contacts " Button
		ContactPage cp = new ContactPage(driver);
		cp.createContact();
		// Step 4 enter all the details and create new Organization

		String startDate = jLib.getSystemDateyyyyMMdd();
		String endDate = jLib.getRequiredDateyyyyMMdd(30);
		cp.lastName(lastName);
		cp.startDate(startDate);
		cp.endDate(endDate);

		cp.clickOnSaveBtn();
		// verify header phone Number info Expected Result
		String actStartDate = cp.getSupportStartDate();
		Assert.assertEquals(actStartDate.trim(), startDate);

		String actEndDate = cp.getSupportEndDate();
		Assert.assertEquals(actEndDate.trim(), endDate);

		/*
		 * String actStartDate =
		 * driver.findElement(By.id("dtlview_Support Start Date")).getText(); if
		 * (actStartDate.trim().equals(startDate)) { System.out.println(startDate +
		 * " information is verified == PASS"); } else { System.out.println(startDate +
		 * "is not verified == FAIL"); }
		 * 
		 * String actendDate =
		 * driver.findElement(By.id("dtlview_Support End Date")).getText(); if
		 * (actendDate.trim().equals(endDate)) { System.out.println(endDate +
		 * "information is  verified == PASS"); } else { System.out.println(endDate +
		 * "information is not verified == FAIL"); }
		 */

	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Exception, IOException {

		System.out.println("execute createContactWithDate and verify");

		// Read TestScript data from Excel file
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

		// navigate to organization Module

		HomePage hp = new HomePage(driver);

		// step 3 click on "create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.createOrg();
		op.createNewOrgBtn();

		// enter all the details and create new Organization
		op.orgNameText(orgName);
		op.saveBtn();
		// verify header phone Number info Expected Result

		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "header verified===Pass");
		} else {
			System.out.println(orgName + "header is not  verified==Fail");
		}

		// click on create contact button

		ContactPage cp = new ContactPage(driver);
		cp.navigateToContactsLink();

		// enter all the details and create new Org
		cp.createContact();
		cp.lastName(contactLastName);

		cp.lookup();

		// switch to child window
		wLib.switchToTabOnURL(driver,
				"http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
		System.out.println(driver.getCurrentUrl());
		CreateNewOrganizationLookUp cnop = new CreateNewOrganizationLookUp(driver);
		System.out.println(orgName);
		cnop.searchInfo(orgName);
		cnop.searchButton();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		wLib.switchToTabOnURL(driver, "Contacts&action");

		cp.clickOnSaveBtn();

		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actOrgName.trim().contains(orgName)) {
			System.out.println(orgName + "is verified == PASS");
		} else {
			System.out.println(orgName + "is not verified == FAIL");
		}
	}
}
