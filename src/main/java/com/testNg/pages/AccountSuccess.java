package com.testNg.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccess {

	WebDriver driver;

	@FindBy(xpath = "//div[@id='content']/h1")
	WebElement accountSuccesPageHeading;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement warningAlert;

	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameAlert;

	public AccountSuccess(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String accountSuccesPageHeadingStatus() {
		return accountSuccesPageHeading.getText();
	}

	public String invalidRegisterAlert() {
		return warningAlert.getText();
	}

	public String firstNameAlert() {
		return firstNameAlert.getText();
	}
}
