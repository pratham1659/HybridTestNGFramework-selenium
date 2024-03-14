package com.qa.pages;

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

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement loginCheckInvalid;
	
	
	//Selenium Specific Data
	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstNameFeild;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastNameFeild;

	@FindBy(xpath = "//input[@name='username']")
	WebElement userNameFeild;

	@FindBy(xpath = "//input[@name='email']")
	WebElement emailNameFeild;

	@FindBy(xpath = "//input[@name='dob']")
	WebElement dateOfBirthFeild;

	@FindBy(xpath = "//input[@name='contact']")
	WebElement contactFeild;
	
	
	

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

	public AccountPage clickOnLoginBtn() {
		loginBtn.click();
		return new AccountPage(driver);
	}

	public AccountPage login(String emailText, String passwordText) {
		emailAddressFeild.sendKeys(emailText);
		passwordAddressFeild.sendKeys(passwordText);
		loginBtn.click();
		return new AccountPage(driver);
	}


	public String CheckValidLoginStatus() {
		return loginCheckValid.getText();

	}

	public String checkInvalidLoginStatus() {
		return loginCheckInvalid.getText();
	}
	
	//Selenium Specific Data
	public void ContactFormTest(String firstName, String lastName, String userName, String email, String dob,
			String contact) {
		firstNameFeild.sendKeys(firstName);
		lastNameFeild.sendKeys(lastName);
		userNameFeild.sendKeys(userName);
		emailNameFeild.sendKeys(email);
		dateOfBirthFeild.sendKeys(dob);
		contactFeild.sendKeys(contact);
	}

}
