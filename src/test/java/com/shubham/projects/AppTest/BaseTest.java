package com.shubham.projects.AppTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.google.common.collect.ImmutableMap;
import com.shubham.projects.AppTest.PageObjects.Settings;
import com.shubham.projects.AppTest.utility.AppiumUtils;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.android.options.app.IntentOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class BaseTest extends AppiumUtils {
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public Settings setting;
	

	
	public void intialize() throws URISyntaxException, IOException, InterruptedException {	
		InitconfigFile();
		String AppiumJSPath = prop.getProperty("AppiumJSPath");
		System.out.println(AppiumJSPath);
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		System.out.println(ipAddress);
		//String ipAddress = prop.getProperty("ipAddress");
		int port = Integer.parseInt(prop.getProperty("port"));
		
		if (isServerRunning(port)) {
            System.out.println("Server is running. Stopping the server.");
          //  stopServer(pid);
            killProcess(prop.getProperty("PID"));
            Thread.sleep(5000);
        }
		
		service = startAppiumServer(ipAddress, port,AppiumJSPath);
			if(!service.isRunning()) {
			    service.start();
			}

		
		UiAutomator2Options options = 	new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		options.setAppPackage(prop.getProperty("AppPackage"));
		options.setAppActivity(prop.getProperty("AppActivity"));
		driver = new AndroidDriver(service.getUrl(), options);
	//	driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws URISyntaxException, IOException, InterruptedException {
		intialize();
		setting = new Settings(driver);
	}
	
	@BeforeMethod (alwaysRun = true)
	public void preSetup() throws InterruptedException
	{
	//	Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
	//	driver.startActivity(activity);
		driver.executeScript("mobile:startActivity", ImmutableMap.of("intent","com.android.settings/com.android.settings.Settings"));
		Thread.sleep(2000);
		
	}
	
	@AfterClass (alwaysRun = true)
	public void TearDown() throws InterruptedException, IOException {
		Thread.sleep(Duration.ofSeconds(10));
		driver.quit();
		if(service.isRunning()) {
		service.stop();
		}
		
		
		System.out.println("Starting the server.");
		 startServer();
		 exitConfigFile();

    }
		
	}


