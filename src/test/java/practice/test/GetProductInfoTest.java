package practice.test;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest {
@Test (dataProvider = "getData")
public void getProductinfoTest(String brandName,String productName)
{
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("https://amazon.in");
	
	//search product
	driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("iphone",Keys.ENTER);
	
	//capture product Info
	// String x = "//span[normalize-space()='94,990']";
	// String x="//span[text()='Apple iPhone 12 (128) -Black']/../../../../div[3]/div[1]/div/div[1]/div[1]/a/span/span[2]";
	
	//String x="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/a/span/span[2]";
	//String x="//span[contains(text(),'iPhone Air 256 GB: Thinnest iPhone Ever, 16.63 cm ')]/../../../../../div/div/a//span";
//String x="//span[contains(text(),'"+productName+"')]/../../../../../div/div/a//span";
String x="//span[contains(text(),'"+productName+"')]/../../../../../div/div[3]//span[1]/span//span[@class='a-price-whole']";	

	 String price=driver.findElement(By.xpath(x)).getText();
	 System.out.println(price);
	 
	 
}

@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr=new Object[3][2];
		objArr[0][0]="iphone";
		objArr[0][1]="iPhone Air 256 GB: Thinnest iPhone Ever,";
		
		objArr[1][0]="iphone";
		objArr[1][1]="iPhone 17 Pro Max 1 TB: 17.42 cm";
		

		objArr[2][0]="iphone";
		objArr[2][1]="iPhone 17 Pro 256 GB: 15.93 cm";
	

		
		return objArr;
	}
}
