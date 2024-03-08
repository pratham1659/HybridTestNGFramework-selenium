package com.testNg.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testNg.base.TestBase;
import com.testNg.utils.Utilities;

public class RegisterPage extends TestBase {

	WebDriver driver;

	public RegisterPage() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();

	}

	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFeilds() throws InterruptedException {

		driver.findElement(By.name("firstname")).sendKeys(dataProp.getProperty("firstname"));
		driver.findElement(By.name("lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));

		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("password"));
		driver.findElement(By.id("input-confirm")).sendKeys(dataProp.getProperty("password"));
		driver.findElement(By.xpath("//input[@name='newsletter' and @type='radio' and @value=1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		

		String actualHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualHeading, dataProp.getProperty("accountCreated"));
	}

	@Test(priority = 2)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualPrivacyPolicy = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		Assert.assertEquals(actualPrivacyPolicy, dataProp.getProperty("privacyPolicy"));
		String actualFirstNameWarning = driver
				.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"))
				.getText();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
