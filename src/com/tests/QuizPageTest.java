package com.tests;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.QuizPage;

public class QuizPageTest {
	private QuizPage _qp;
	public WebDriver driver;
	
	
	
	@BeforeTest
	
	public void init()
	{
		
	   driver=new FirefoxDriver();
		
		_qp=new QuizPage(driver);
		 driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.get("http://www.whiteboxqa.com");
         
	}
	
	
	
	
	@Test(priority=1) @Parameters({"userName","passWord"})
	public void testQuizPage(String userName,String passWord)
	{
		boolean actual=_qp.clickOnQuiz(userName,passWord);
		Assert.assertTrue(actual);
		
		
	}
	
	 @Test(priority=2,dependsOnMethods = {"testQuizPage"})
	 public void testSubjectsCount()
	 {
		 int actual=_qp.getSubjectsCount();
		 Assert.assertEquals(actual,21);
		
	}
	 
	  
	 @Test(priority=3,dependsOnMethods = {"testQuizPage"}) @Parameters({"subject"})
	 
	 public void testforSubjectName(String subject)
	 {
		boolean status= _qp.CheckSubjectNameExists(subject);
		Assert.assertTrue(status);
	 }
	 
	 
	 @Test(priority=4,dependsOnMethods = {"testQuizPage"}) @Parameters({"subject"})
	 public void testquizStartPage(String subject)
	 {
		boolean actual=_qp.quizStartPage(subject);
		Assert.assertTrue(actual);
	 }
	 
	 
	 
	   @Test(priority=5,dependsOnMethods = {"testQuizPage"})  @Parameters({"userName","passWord","subject"})
		 public void testquestionPage(String userName,String passWord,String subject)
		 {
			boolean actual=_qp.CheckForQuestionsPage(userName, passWord,subject);
			Assert.assertTrue(actual);
		 }
		 
	 
	 
	 @AfterTest
	public void teardown()
	{
		driver.close();
	}
	

}
