package com.testNg.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testNg.base.TestBase;
import com.testNg.pages.AccountSuccess;
import com.testNg.pages.HomePage;
import com.testNg.pages.RegisterPage;
import com.testNg.utils.Utilities;

public class RegisterTest extends TestBase {

	WebDriver driver;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		homePage.selectRegisterOption();

	}

	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFeilds() throws InterruptedException {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstname"));
		registerPage.entertLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
		registerPage.enterPassword(dataProp.getProperty("password"));
		registerPage.enterConfirmPassword(dataProp.getProperty("password"));
		registerPage.selectRadioBtn();
		registerPage.selectPrivacyPolicyButton();
		registerPage.clickSubmitBtn();
		

		AccountSuccess accountSuccess = new AccountSuccess(driver);
		
		String actualHeading = accountSuccess.accountSuccesPageHeadingStatus();
		Assert.assertEquals(actualHeading, dataProp.getProperty("accountCreated"));
	}

	@Test(priority = 2)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickSubmitBtn();
		
		AccountSuccess accountSuccess = new AccountSuccess(driver);
		
		String actualPrivacyPolicy = accountSuccess.invalidRegisterAlert();
		Assert.assertEquals(actualPrivacyPolicy, dataProp.getProperty("privacyPolicy"));
		String actualFirstNameWarning = accountSuccess.firstNameAlert();
				
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
