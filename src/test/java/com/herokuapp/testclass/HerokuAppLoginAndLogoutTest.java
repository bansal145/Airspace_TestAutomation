package com.herokuapp.testclass;
import java.io.IOException;

import org.testng.annotations.Test;

import com.herokuapp.automation.BaseDriver;
import com.herokuapp.pages.HomePage;
import com.herokuapp.pages.LoginPage;

public class HerokuAppLoginAndLogoutTest extends BaseDriver{
	
	@Test
	
	public void validateLoginLogoutFunctionality() throws IOException
	{
		try {
			LoginPage login = new LoginPage();
			HomePage home = new HomePage();
			// Test Step 1 -> Opening the browser and navigated to Test Application
			BaseDriver.GetDriver();
			
			// Test Step 2 -> Enter Login information and Validate Login is successful
			login.login_Into_Application();
			
			// Test Step 3 -> Logout and verify that logout is successful.
			home.logout_From_Application();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			// stop the browser
			StopDriver();
		}
	}

}
