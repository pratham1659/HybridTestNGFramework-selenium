package com.test.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.SearchPage;

public class SearchTest extends TestBase {

	public WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;

	public SearchTest() {
		super();
	}


	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		homePage = new HomePage(driver);
	}

	@Test(priority = 1, enabled = false)
	public void verifySearchWithValidProduct() throws InterruptedException {

		searchPage = homePage.selectSearchInput(dataProp.getProperty("validProduct"));

		String availProduct = searchPage.verfiySearchResults();
		Assert.assertEquals(availProduct, dataProp.getProperty("availProduct"), "Product Available");
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		searchPage =  homePage.selectSearchInput(dataProp.getProperty("invalidProduct"));

		String noProductString = searchPage.noProductFound();
		Assert.assertEquals(noProductString, dataProp.getProperty("noProductMatch"),
				dataProp.getProperty("noProductMatch"));

	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {


		searchPage = homePage.selectSearchInput("");

		String noProductString = searchPage.noProductFound();
		Assert.assertEquals(noProductString, dataProp.getProperty("noProductMatch"),
				dataProp.getProperty("noProductMatch"));

	}


	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
