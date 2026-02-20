package practice.test;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest2 {
@Test (dataProvider = "getData")
public void getProductinfoTest(String brandName,String productName)
{
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.manage().window().maximize();
	driver.get("https://amazon.in");
	
	//search product
	driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("iphone",Keys.ENTER);
	
	//capture product Info
	// String x = "//span[normalize-space()='94,990']";
	// String x="//span[text()='Apple iPhone 12 (128) -Black']/../../../../div[3]/div[1]/div/div[1]/div[1]/a/span/span[2]";
	
//String x="//span[contains(text(),'"+productName+"')]/../../../../../div/div/a//span";
String x="//span[contains(text(),'"+productName+"')]/../../../../../div/div[3]//span[1]/span//span[@class='a-price-whole']";	

	 String price=driver.findElement(By.xpath(x)).getText();
	 System.out.println(price);
	 
	 driver.quit();
}

@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
	ExcelUtility eLib=new ExcelUtility();
	int rowCount = eLib.getRowcount("dataProvider");
	Object[][] objArr=new Object[rowCount][2];
	
	for(int i=0;i<rowCount;i++)
	{
	objArr[i][0]=eLib.getDataFromExcel("dataProvider", i+1, 0);	
	objArr[i][1]=eLib.getDataFromExcel("dataProvider", i+1, 1);	


	}
	
	
	return objArr;
	}
}
