package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;



//@Listeners(com.comcast.crm.generic.listenerutility.ListImpClass.class)
public class InvoiceWithRetryAnalyzerTest extends BaseClass
{

	@Test(retryAnalyzer = com.comcast.crm.generic.listenerutility.RetryListenerImp.class)
	public void createInvoiceTest()
	{
		System.out.println("execute createInvoiceTest");
		String actTitle=driver.getTitle();
		//Assert.assertEquals(actTitle,"Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
	
}

	
