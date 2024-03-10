package com.qa.pages;

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
	private WebElement loginOption;

	@FindBy(xpath = "//a[contains(text(),'Register')]")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchInput;

	@FindBy(xpath = "//button[@type='button']//i[contains(@class, 'fa-search')]")
	private WebElement searchBtn;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void clickOnMyAccountDropMenu() {
		myAccountDropMenu.click();
	}

	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}

	public LoginPage naviageToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);

	}

	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);

	}

	public RegisterPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}

	public SearchPage selectSearchInput(String input) {
		searchInput.sendKeys(input);
		searchBtn.click();
		return new SearchPage(driver);
	}

	public SearchPage clickOnSearchButton() {
		searchBtn.click();
		return new SearchPage(driver);
	}

}
