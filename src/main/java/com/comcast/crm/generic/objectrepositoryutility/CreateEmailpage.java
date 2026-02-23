package com.comcast.crm.generic.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateEmailpage {

public WebDriver driver;

	public CreateEmailpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
}
