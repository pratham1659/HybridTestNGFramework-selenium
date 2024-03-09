package com.testNg.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.testNg.base.TestBase;
import com.testNg.pages.AccountPage;
import com.testNg.pages.HomePage;
import com.testNg.pages.LoginPage;
import com.testNg.utils.Utilities;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginTest extends TestBase {

	WebDriver driver;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser();
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		homePage.selectLoginOption();

	}

	@DataProvider
	public Object[][] supplyTestData() {
		Object[][] dataObject = Utilities.getTestDataFromExcel("Login");
		return dataObject;

	}

	@Test(priority = 1, dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredentials(String email, String password) {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPasswordAddress(password);
		loginPage.clickOnLoginBtn();
		
		
		AccountPage accountPage  = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfAccount());
		

		String loginCheck = loginPage.CheckValidLoginStatus();
		String validCheck = dataProp.getProperty("validCheck");

		Assert.assertTrue(loginCheck.contains(validCheck), "Login Not successfully)");
	}
	
	@DataProvider
	public Object[][] supplyFakeTestData() {
		Object[][] dataObject = { { "pratham@gmail.com", "1234543" }, { "pratham@gmail.com", "1234534" } };
		return dataObject;

	}

	@Test(priority = 2, dataProvider = "supplyFakeTestData")
	public void verifyLoginWithInValidCredentials(String email, String password) {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPasswordAddress(password);
		loginPage.clickOnLoginBtn();
		

		String loginCheck = loginPage.checkInvalidLoginStatus();
		String invalidCheck = dataProp.getProperty("invalidCheck");
		Assert.assertTrue(loginCheck.contains(invalidCheck), "Login Not successfully)");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
