package com.testNg.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	// Objects
	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement LoginOption;
	
	@FindBy(xpath = "//a[contains(text(),'Register')]")
	private WebElement RegisterOption;
	
	

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void clickOnMyAccountDropMenu() {
		myAccountDropMenu.click();
	}

	public void selectLoginOption() {
		LoginOption.click();
	}
	
	public void selectRegisterOption() {
		RegisterOption.click();
	}
}
