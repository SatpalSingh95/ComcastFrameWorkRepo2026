package com.comcast.crm.orgtest;

import java.io.IOException;

import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.generic.objectrepositoryutility.HomePage;
import com.comcast.crm.generic.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.generic.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass{
	
	@Test
	public  void createOrgTest() throws Exception, IOException
	{

		// Read TestScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

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
			System.out.println(orgName + " name is not  verified === Fail");
		}

		
	
	}
}
