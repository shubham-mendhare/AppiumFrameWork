package com.shubham.projects.AppTest;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.shubham.projects.AppTest.utility.AndroidAction;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SettingsTest extends BaseTest {
	
	
	@Test(priority = 1)
	public void SettingsTitleCheck() {
		String actual = setting.getSettingsTitle();
		Assert.assertEquals(actual, "Settings");
	}
	
	@Test(priority = 2)
	public void SearchTest() {
		ArrayList<WebElement> li = setting.searchSettings("Default Apps");
		for(int i=0; i<li.size();i++){
			if(li.get(i).getText().contains("Default home app")) {
				System.out.println("Search is working properly!");
				break;
			}
		}
		driver.hideKeyboard();
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		}
	
	@Test(priority = 3)
	public void GoogleClickByScroll() {
		setting.scrollToText("Tips & support").click();
		System.out.println("Successfully scrolled!");
	}
		
	}
