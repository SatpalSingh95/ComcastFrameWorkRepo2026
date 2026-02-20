package practice.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.objectrepositoryutility.LoginPage;
/**
 * test class for Contact module
 * @author satpal singh
 * 
 * 
 * 
 * 
 * 
 */
public class SearchContactTest extends BaseClass {
/**
 * login()=>navigateContact==>createcontact()==verify
 */
	
	@Test
	public void searchcontactTest() 
	{
		/*step:1 login to app*/
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp("url", "username", "password");
		
	}
}
