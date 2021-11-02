package com.herokuapp.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.herokuapp.automation.BaseDriver;
import com.herokuapp.automation.Wrapper;

public class LoginPage extends BaseDriver{
	Wrapper baseobj = new Wrapper();	

	public void login_Into_Application()
	{
	    
		try {
			String username = prop.getProperty("username");
			String password =prop.getProperty("password");
			
			baseobj.getDriver().findElement(By.id("username")).sendKeys(username);
			baseobj.getDriver().findElement(By.id("password")).sendKeys(password);
			baseobj.getDriver().findElement(By.xpath("//button[@type='submit']")).click();
			baseobj.waitForBrowserToLoadCompletely();
			String success_message = baseobj.getDriver().findElement(By.id("flash")).getText();
			Assert.assertTrue(success_message.contains(prop.getProperty("login_success_message")));
			System.out.println("Login functionality is working properly !!");
		} catch (Exception e) {
			System.out.println("Login functionality is not working properly !!");
			e.printStackTrace();
		}
        
	}
}
