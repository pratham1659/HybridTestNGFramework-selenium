package com.testNg.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.testNg.base.TestBase;
import com.testNg.utils.Utilities;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends TestBase {

	WebDriver driver;

	public LoginPage() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser();
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.linkText("Login")).click();

	}

	@DataProvider
	public Object[][] supplyTestData() {
		Object[][] dataObject = Utilities.getTestDataFromExcel("Login");
		return dataObject;

	}

	@Test(priority = 1, dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredentials(String email, String password) {

		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String loginCheck = driver.findElement(By.linkText("Edit your account information")).getText();
		String validCheck = dataProp.getProperty("validCheck");

		Assert.assertTrue(loginCheck.contains(validCheck), "Login Not successfully)");
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys("sdf@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String loginCheck = driver
				.findElement(
						By.xpath("//div[contains(text(), 'Warning: No match for E-Mail Address and/or Password.')]"))
				.getText();
		String invalidCheck = dataProp.getProperty("invalidCheck");

		Assert.assertTrue(loginCheck.contains(invalidCheck), "Login Not successfully)");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
