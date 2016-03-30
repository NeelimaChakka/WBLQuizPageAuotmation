package com.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	

	public boolean performLogin(String userName,String password)
	{
		//String user="";
		boolean user=false;
		
		try
		{
		WebElement loginbtn=driver.findElement(By.cssSelector("#loginButton"));
		
		/*new addition of two lines 
		WebDriverWait wait=new WebDriverWait(driver,60);
		loginbtn=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#loginButton")));
		
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", loginbtn);**/
		
		loginbtn.click();
	
	
	
		WebElement userID=driver.findElement(By.cssSelector("#username"));
		WebElement pwd=driver.findElement(By.cssSelector("#password"));
		WebElement submitBtn=driver.findElement(By.cssSelector("#login"));
	
		userID.clear();
		pwd.clear();
		userID.sendKeys(userName);
		pwd.sendKeys(password);
		submitBtn.click();
	    
			//Thread.sleep(2000);
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	 //  user= driver.findElement( By.xpath("//*[@id='body']/tr[1]/td[2]")).getText();
		user=driver.findElement( By.xpath("//*[@id='body']/tr[1]/td[2]")).getText().contains(userName);
	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			user=false;
			e.printStackTrace();
		}
		return user;
	}
	
	

}
