package com.pages;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class QuizPage {
	 WebDriver driver;
	LoginPage _lp;
	
	
	public QuizPage(WebDriver driver)
	{
		this.driver=driver;
		this._lp=new LoginPage(driver);
	}
	
	public boolean clickOnQuiz(String uname,String pwd)
	
	{
		
	try
		{
			boolean text=false;
			
			if(_lp.performLogin(uname, pwd))
			{
		
		waitForLoad();
		Actions action=new Actions(driver);
		WebElement resources=driver.findElement(By.cssSelector(".dropdown-toggle"));
		WebElement quiz=driver.findElement(By.cssSelector(".dropdown-menu>li>a[href*='quiz']"));
		
		
	//	action.moveToElement(resources).moveToElement(quiz).click().build().perform();
		action.moveToElement(resources).build().perform();
		By locator=By.cssSelector(".dropdown-menu>li>a[href*='quiz']");
		
		quiz=RefreshObject(locator);
		WebDriverWait wait=new WebDriverWait(driver,300);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		quiz.click();
			
		
	if(driver.getCurrentUrl().contains("quiz"))
		{
			
			text=true;
			return text;
		}
	
  }
	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	return false;
						
}
	
	
	public void waitForLoad()
	 {
	     
	         ExpectedCondition<Boolean> pageLoadCondition = new
	                 ExpectedCondition<Boolean>() {
	                     public Boolean apply(WebDriver _webDriver) {
	                         return ((JavascriptExecutor) _webDriver).executeScript("return document.readyState").equals("complete");
	                     }
	                 };
	         
	         WebDriverWait wait = new WebDriverWait(driver, 60);
	         wait.until(pageLoadCondition);
	    
	 
  }
	 
	 //code to avoid stale Element Exception
	 public  WebElement RefreshObject(By Locator)
	 
	 {
		 
		 try
		 {
			// counter=counter+1;
			 return driver.findElement(Locator);
			 
		 }
		 
		 catch(StaleElementReferenceException e)
		 {
			 return RefreshObject(Locator);
			 
		 }
		 
	 }
	 
	
	 
	 //method to check for subjects count
	 
	 public int getSubjectsCount()
	 {
		 
		 
		 WebElement subject=driver.findElement(By.cssSelector("#subject"));
		 Select subjectSelect=new Select(subject);
		 int subjectCount=subjectSelect.getOptions().size();
		 return subjectCount;
	 }
	 
	 
	 //check for the dropdownlist data
	 
	 public boolean CheckSubjectNameExists(String text)

	 {
		boolean subjectPresent=false; 
		try
		{
		 WebElement subjectDropDown=driver.findElement((By.cssSelector("#subject")));
		 
		/* JavascriptExecutor executor = (JavascriptExecutor)driver;
		 executor.executeScript("arguments[0].click();", subjectDropDown);
		 WebDriverWait wait=new WebDriverWait(driver,100);
		 wait.until(ExpectedConditions.elementToBeSelected(subjectDropDown));**/
		 Select se=new Select(subjectDropDown);
		 List<WebElement> subjects = se.getOptions();
			for (WebElement subject : subjects)
			{
				if(subject.getText().contentEquals(text))
				{
					subjectPresent=true;
				    break;
				}
			}
			
			
		}
		
		catch(Exception e)
		{
			//elementPresent=false;
			e.printStackTrace();
		}

		 
      return subjectPresent;
        }
	 
	 
	 //checking quiz start page
	 public boolean quizStartPage(String subject)
	 {
		 boolean success=false;
		 
		 try
		 {
		if( CheckSubjectNameExists(subject))
		{
			 WebElement subjectDropDown=driver.findElement((By.cssSelector("#subject")));
			/* Select se=new Select(subjectDropDown);
			 WebDriverWait wait=new WebDriverWait(driver,300);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#subject")));
				
			
			 subjectDropDown.click();
			 se.selectByVisibleText(subject);
			 se.getFirstSelectedOption().click();**/
			 WebDriverWait wait = new WebDriverWait(driver, 300);
			 
			 subjectDropDown.click();
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#subject")));
			 Select select = new Select(subjectDropDown);
			 select.selectByVisibleText(subject);
			success= driver.getPageSource().contains("start");
		}
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return success;
	 }
	 
	 
	 
	  public boolean CheckForQuestionsPage(String userName,String password,String subject)
	 {
		 
		 boolean questionExists=false;
		 _lp.performLogin(userName, password);
		 
		if( quizStartPage(subject))
		{
			WebElement start=driver.findElement(By.cssSelector("#btnAgreement"));
			start.click();
			waitForLoad();
			 WebDriverWait wait = new WebDriverWait(driver, 300);
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#subject")));
			WebElement question=driver.findElement(By.cssSelector(".well>p"));
			if(question.isDisplayed())
			{
				 questionExists=true;
				 return questionExists;
			}
			
		}
		return questionExists;
		 
		 
	 }
	
}
