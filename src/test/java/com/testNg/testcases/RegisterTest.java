package com.testNg.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testNg.base.TestBase;
import com.testNg.pages.AccountSuccessPage;
import com.testNg.pages.HomePage;
import com.testNg.pages.RegisterPage;
import com.testNg.utils.Utilities;

public class RegisterTest extends TestBase {

	WebDriver driver;
	HomePage homePage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();

	}

	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFeilds() throws InterruptedException {

		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephoneNumber"), dataProp.getProperty("validPassword"));

		String actualHeading = accountSuccessPage.accountSuccesPageHeadingStatus();
		Assert.assertEquals(actualHeading, dataProp.getProperty("accountSuccessfullyCreated"), "Account Success page is not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		registerPage.clickOnContinueButton();

		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"),
				dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"),
				dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"),
				dataProp.getProperty("passwordWarning")));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
