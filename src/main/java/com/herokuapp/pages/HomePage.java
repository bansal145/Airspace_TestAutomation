package com.herokuapp.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.herokuapp.automation.BaseDriver;
import com.herokuapp.automation.Wrapper;

public class HomePage extends BaseDriver{
	Wrapper baseobj = new Wrapper();	


	public void logout_From_Application()
	{

		try {
			baseobj.getDriver().findElement(By.xpath("//a[@href='/logout']")).click();
			baseobj.waitForBrowserToLoadCompletely();
			String success_message = baseobj.getDriver().findElement(By.id("flash")).getText();
			Assert.assertTrue(success_message.contains(prop.getProperty("logout_success_message")));
			System.out.println("Logout functionality is working properly !!");
		} catch (Exception e) {
			System.out.println("Logout functionality is not working properly !!");
			e.printStackTrace();
		}

	}
}
