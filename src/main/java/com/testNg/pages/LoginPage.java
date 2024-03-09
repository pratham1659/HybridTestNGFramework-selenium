package com.testNg.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "input-email")
	private WebElement emailAddressFeild;

	@FindBy(id = "input-password")
	private WebElement passwordAddressFeild;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginBtn;
	
	@FindBy(linkText = "Edit your account information")
	private WebElement loginCheckValid;
	
	@FindBy(xpath = "//div[contains(text(), 'Warning: No match for E-Mail Address and/or Password.')]")
	private WebElement loginCheckInvalid;

	public LoginPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void enterEmailAddress(String email) {
		emailAddressFeild.sendKeys(email);
	}
	
	public void enterPasswordAddress(String password) {
		passwordAddressFeild.sendKeys(password);
	}
	
	public void clickOnLoginBtn() {
		loginBtn.click();
	}
	
	public String CheckValidLoginStatus() {
		return loginCheckValid.getText();
		
	}
	
	public String checkInvalidLoginStatus() {
		return loginCheckInvalid.getText();
	}

}
