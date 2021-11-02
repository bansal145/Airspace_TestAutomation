package com.herokuapp.automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseDriver extends Wrapper{

	public static Properties prop;

	public static void GetDriver() throws IOException
	{
		try {
			prop = new Properties();
			InputStream input = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\herokuapp\\resources\\config.properties");
			prop.load(input);
			String browserName = prop.getProperty("browsername");

			if(browserName.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\BrowserDrivers\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			capabilities.setCapability("applicationCacheEnabled",false );
			options.addArguments("test-type");
			options.addArguments("--incognito");

			driver =new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		else if (browserName.equalsIgnoreCase("ie")) {

			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "//BrowserDrivers//IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//BrowserDrivers//geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}  catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	driver.get(prop.getProperty("testurl"));
}

public static void StopDriver()
{
	try {
		if(driver!=null)
		{
			driver.close();
			driver.quit();
			driver=null;
		}
	} catch (Exception ignore) {
		// TODO Auto-generated catch block
		System.out.println("Getting exception while closing the driver" +ignore);
	}
}
}