package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;

	@FindBy(xpath = "//a[contains(text(),'HP LP3065')]")
	private WebElement SearchTextFound;

	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement noSearchFound;

	public SearchPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSearchText() {
		SearchTextFound.click();
	}

	public String verfiySearchResults() {
		return SearchTextFound.getText();
	}

	public String noProductFound() {
		return noSearchFound.getText();
	}
}
