package com.testNg.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testNg.base.TestBase;
import com.testNg.pages.AccountPage;
import com.testNg.pages.HomePage;
import com.testNg.pages.LoginPage;
import com.testNg.utils.Utilities;

public class LoginTest extends TestBase {

	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser();
		homePage = new HomePage(driver);
		loginPage = homePage.naviageToLoginPage();

	}

	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] dataObject = Utilities.getTestDataFromExcel("Login");
		return dataObject;

	}

	@Test(priority = 1, dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {

		accountPage = loginPage.login(email, password);
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

		loginPage.login(Utilities.generateEmailWithTimeStamp(), password);
		Assert.assertTrue(loginPage.checkInvalidLoginStatus().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
				"Login Not successfully)");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
