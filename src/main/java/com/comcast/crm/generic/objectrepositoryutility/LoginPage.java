package com.comcast.crm.generic.objectrepositoryutility;
/**
 * 
 * @author satpal singh
 * Contains Login Page elements & business lib like login
 * 
 * 
 * 
 * 
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage  extends WebDriverUtility
{
	//Rule-1 Create a separate class
	//Rule-2 Object Creation
	
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private	WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private	WebElement loginBtn;

	// Rule:3 Object Initialization

	//Rule:4 Object Encapsulation
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	

	
	
	//Rule:5 provide Action
	// Business method only for specific script
	/**
	 * login tp application based on username, password, url arguments
	 * @param username
	 * @param password
	 * @param url
	 */
	public void loginToapp( String username, String password,String url)
	{
		
	driver.get(url);
driver.manage().window().maximize();
	WaitForPageToLoad(driver);
	    usernameEdit.sendKeys(username);
        passwordEdit.sendKeys(password);
        loginBtn.click();
	}
	
	
}
