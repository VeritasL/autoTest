package seleniumTest.login;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StaticInfo{
	Properties pros=new Properties();
	WebDriver driver=null;
	
	@Before
	public void setUp() throws IOException{		
		pros.load(this.getClass().getClassLoader().getResourceAsStream("test.properties"));
		System.setProperty("webdriver.chrome.driver", pros.getProperty("chrome.driver"));
		driver=new ChromeDriver();
		driver.get(pros.getProperty("loginurl"));
	}
	
	@After
	public void shutDown(){
		driver.close();
	}
	
	@Test
	public void testTitle(){
		Assert.assertEquals(pros.getProperty("title").toString(), driver.getTitle().toString());
	}
	
	@Test
	public void testHead(){
		Assert.assertEquals(pros.getProperty("head").toString(), driver.findElement(By.xpath(pros.getProperty("xHead"))).getText().toString());
	}
	
	@Test
	public void testServiceNum(){
		Assert.assertEquals(pros.getProperty("serviceNum").toString(), driver.findElement(By.xpath(pros.getProperty("xServiceNum"))).getText().toString());
	}
	
	@Test
	public void testAboutUs(){
		WebElement e=driver.findElement(By.xpath(pros.getProperty("xAboutUs")));
		Assert.assertEquals(pros.getProperty("aboutUs").toString(),e.getText().toString());
		e.click();
		for(String handle:driver.getWindowHandles()){
			driver.switchTo().window(handle);
			// On the basis that title is correct.
			if(driver.getTitle().toString().equals(pros.getProperty("title").toString())){
				continue;
			}
			Assert.assertTrue(driver.getTitle().contains(pros.getProperty("aboutComp")));
		}		
	}
	
	@Test
	public void testCoop(){
		WebElement e=driver.findElement(By.xpath(pros.getProperty("xCoop")));
		Assert.assertEquals(pros.getProperty("coop").toString(),e.getText().toString());
		e.click();
		for(String handle:driver.getWindowHandles()){
			driver.switchTo().window(handle);
			if(driver.getTitle().toString().equals(pros.getProperty("title").toString())){
				continue;
			}
			Assert.assertTrue(driver.getTitle().contains(pros.getProperty("coop")));
		}		
	}
	
	@Test
	public void testlegal(){
		WebElement e=driver.findElement(By.xpath(pros.getProperty("xLegal")));
		Assert.assertEquals(pros.getProperty("legal").toString(),e.getText().toString());
		e.click();
		for(String handle:driver.getWindowHandles()){
			driver.switchTo().window(handle);
			if(driver.getTitle().toString().equals(pros.getProperty("title").toString())){
				continue;
			}
			Assert.assertTrue(driver.getTitle().contains(pros.getProperty("legal")));
		}		
	}
	
	@Test
	public void testContactUs(){
		WebElement e=driver.findElement(By.xpath(pros.getProperty("xContactUs")));
		Assert.assertEquals(pros.getProperty("contactUs").toString(),e.getText().toString());
		e.click();
		for(String handle:driver.getWindowHandles()){
			driver.switchTo().window(handle);
			if(driver.getTitle().toString().equals(pros.getProperty("title").toString())){
				continue;
			}
			Assert.assertTrue(driver.getTitle().contains(pros.getProperty("contactUs")));
		}		
	}
	
	@Test
	public void testRights(){
		WebElement e=driver.findElement(By.xpath(pros.getProperty("xRights")));
		Assert.assertTrue(e.getText().contains(pros.getProperty("rights")));
	}
}
