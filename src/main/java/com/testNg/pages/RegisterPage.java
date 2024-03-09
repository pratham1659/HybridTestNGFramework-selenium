package com.testNg.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(name = "firstname")
	private WebElement firstNameInput;

	@FindBy(name = "lastname")
	private WebElement lastNameInput;

	@FindBy(id = "input-email")
	private WebElement emailInput;

	@FindBy(id = "input-telephone")
	private WebElement telephoneInput;

	@FindBy(id = "input-password")
	private WebElement passwordInput;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordInput;

	@FindBy(xpath = "//input[@name='newsletter' and @type='radio' and @value=1]")
	private WebElement newsletterInput;

	@FindBy(name = "agree")
	private WebElement agreeBtn;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitBtn;


	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstName) {
		firstNameInput.sendKeys(firstName);
	}

	public void entertLastName(String lastName) {
		lastNameInput.sendKeys(lastName);
	}

	public void enterEmailAddress(String emailAddress) {
		emailInput.sendKeys(emailAddress);

	}

	public void enterTelephone(String telephone) {
		telephoneInput.sendKeys(telephone);
	}

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);

	}

	public void enterConfirmPassword(String password) {
		confirmPasswordInput.sendKeys(password);

	}

	public void selectRadioBtn() {
		newsletterInput.click();
	}

	public void selectPrivacyPolicyButton() {
		agreeBtn.click();
	}

	public void clickSubmitBtn() {
		submitBtn.click();

	}

}
