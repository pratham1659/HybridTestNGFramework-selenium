package com.testNg.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.testNg.base.TestBase;

import org.testng.annotations.BeforeMethod;

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

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String loginCheck = driver.findElement(By.linkText("Edit your account information")).getText();
		String containCheck = "Edit your account information";

		Assert.assertTrue(loginCheck.contains(containCheck), "Login Not successfully)");
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {

		
		driver.findElement(By.id("input-email")).sendKeys("sdf@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String loginCheck = driver.findElement(By.xpath("//div[contains(text(), 'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String containCheck = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(loginCheck.contains(containCheck), "Login Not successfully)");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
