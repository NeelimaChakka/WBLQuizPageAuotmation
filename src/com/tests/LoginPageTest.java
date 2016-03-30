package com.tests;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.LoginPage;

public class LoginPageTest 

{
	public LoginPage _lp;
	
	public WebDriver driver;
	
	  @BeforeTest
	  public void setup(){
	        
	         driver = new FirefoxDriver();
	         _lp = new LoginPage(driver);
	         driver.manage().window().maximize();
	          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	          driver.get("http://www.whiteboxqa.com");
	  }
	 
	
	@Test(dataProvider="users-data" )
	public void testLoginPage(String uname, String pwd)
	{
		//String actual=_lp.performLogin(uname,pwd);
		//Assert.assertEquals(actual,"Neelu326@gmail.com");
		boolean actual=_lp.performLogin(uname, pwd);
		Assert.assertTrue(actual);
		
	}
	@DataProvider(name="users-data")
	public Object[][] getUsers()
	{
		return new Object[][] {{"Neelu326@gmail.com","training"}};
		
	}
	@AfterTest
	public void teardown()
	{
		driver.close();
	}
	
	
}
