package com.shubham.projects.AppTest.PageObjects;
import java.util.ArrayList;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.shubham.projects.AppTest.utility.AndroidAction;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Settings extends AndroidAction {
	
	AndroidDriver driver;
	
	
	@AndroidFindBy(id="com.android.settings:id/homepage_title")
	private WebElement SettingsTitle;
	
	@AndroidFindBy(className = "android.view.ViewGroup")
	private WebElement Search;
	 
	@AndroidFindBy(id = "com.google.android.settings.intelligence:id/open_search_view_edit_text")
	private WebElement SearchFieldInput;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/title\"]")
	private ArrayList<WebElement> SearchedList;
	
	
	public Settings(AndroidDriver driver){
		super(driver);
	    this.driver = driver;
	    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public String getSettingsTitle() {
	    String title =  SettingsTitle.getText();
	    return title;
	}
	
	
	public ArrayList<WebElement> searchSettings(String settingName) {
		Search.click();
		SearchFieldInput.sendKeys(settingName);
		return SearchedList;
	}
	

}
