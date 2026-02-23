package com.comcast.crm.generic.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocumentsPage {

	
	
	
	@FindBy(xpath="//img[@alt='Create Document...']")
	private WebElement documentImgBtn;
}
