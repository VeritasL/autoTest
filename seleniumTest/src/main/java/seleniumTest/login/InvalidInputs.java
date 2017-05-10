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
import org.junit.BeforeClass;
import org.junit.Test;


public class InvalidInputs{
	static Properties pros=new Properties();
	static WebDriver driver=null;
	
	@BeforeClass
	public static void testExists() throws IOException{
		InvalidInputs i=new InvalidInputs();
		i.setUp();
		if(!driver.findElement(By.xpath(pros.getProperty("xUserName"))).isDisplayed()
				&&driver.findElement(By.xpath(pros.getProperty("xUserNameInput"))).isDisplayed()
				&&driver.findElement(By.xpath(pros.getProperty("xPsw"))).isDisplayed()
				&&driver.findElement(By.xpath(pros.getProperty("xPswInput"))).isDisplayed()
				&&driver.findElement(By.xpath(pros.getProperty("xCheckCode"))).isDisplayed()
				&&driver.findElement(By.xpath(pros.getProperty("xCheckCodeInput"))).isDisplayed()
				&&driver.findElement(By.xpath(pros.getProperty("xSubmit"))).isDisplayed()
				&&driver.findElement(By.xpath(pros.getProperty("xForgetPsw"))).isDisplayed()
				&&driver.findElement(By.xpath(pros.getProperty("xLoginViaWechat"))).isDisplayed()){
			System.out.println("Missing elements for login.");
			return;
		}
		driver.close();
	}

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
	public void testOnlyUsername(){
		WebElement u=driver.findElement(By.xpath(pros.getProperty("xUserNameInput")));
		u.sendKeys(pros.getProperty("onlyUsn"));
		driver.findElement(By.xpath(pros.getProperty("xSubmit"))).click();
		Assert.assertTrue(driver.findElement(By.xpath(pros.getProperty("xPswRe"))).getText().contains(pros.getProperty("pswRe")));
	}
	
	@Test
	public void testOnlyPsw(){
		WebElement u=driver.findElement(By.xpath(pros.getProperty("xPswInput")));
		u.sendKeys(pros.getProperty("onlyPsw"));
		driver.findElement(By.xpath(pros.getProperty("xSubmit"))).click();
		Assert.assertTrue(driver.findElement(By.xpath(pros.getProperty("xUserNameRe"))).getText().contains(pros.getProperty("usnRe")));
	}
	
	@Test
	public void testCheckCode(){
		driver.findElement(By.xpath(pros.getProperty("xUserNameInput"))).sendKeys(pros.getProperty("onlyUsn"));
		driver.findElement(By.xpath(pros.getProperty("xPswInput"))).sendKeys(pros.getProperty("onlyPsw"));
		driver.findElement(By.xpath(pros.getProperty("xCheckCodeInput"))).sendKeys(pros.getProperty("onlyCode")+"11");//Correct usn/psw Errored check code
		driver.findElement(By.xpath(pros.getProperty("xSubmit"))).click();
		System.out.println("需在页面验证验证码的正确性，而非提交到后台");
	}
	
	@Test
	public void testUsernameExceedsLenthLimit(){
		driver.findElement(By.xpath(pros.getProperty("xUserNameInput"))).sendKeys(pros.getProperty("lenthLimits"));
		driver.findElement(By.xpath(pros.getProperty("xPswInput"))).sendKeys(pros.getProperty("onlyPsw"));
		driver.findElement(By.xpath(pros.getProperty("xCheckCodeInput"))).sendKeys(pros.getProperty("onlyCode"));
		driver.findElement(By.xpath(pros.getProperty("xSubmit"))).click();
		System.out.println("There should be lenth limits");
	}
	
	@Test
	public void testPswExceedsLenthLimit(){
		driver.findElement(By.xpath(pros.getProperty("xUserNameInput"))).sendKeys(pros.getProperty("onlyUsn"));
		driver.findElement(By.xpath(pros.getProperty("xPswInput"))).sendKeys(pros.getProperty("lenthLimits"));
		driver.findElement(By.xpath(pros.getProperty("xCheckCodeInput"))).sendKeys(pros.getProperty("onlyCode"));
		driver.findElement(By.xpath(pros.getProperty("xSubmit"))).click();
		System.out.println("There should be lenth limits");
	}
	
	@Test
	public void testCodeExceedsLenthLimit(){
		driver.findElement(By.xpath(pros.getProperty("xUserNameInput"))).sendKeys(pros.getProperty("onlyUsn"));
		driver.findElement(By.xpath(pros.getProperty("xPswInput"))).sendKeys(pros.getProperty("onlyPsw"));
		driver.findElement(By.xpath(pros.getProperty("xCheckCodeInput"))).sendKeys(pros.getProperty("lenthLimits"));
		driver.findElement(By.xpath(pros.getProperty("xSubmit"))).click();
		System.out.println("There should be lenth limits");
	}

}
