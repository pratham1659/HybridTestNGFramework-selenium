package com.testNg.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.testNg.base.TestBase;

public class SearchTest extends TestBase {

	WebDriver driver;

	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() throws InterruptedException {

		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//button[@type='button']//i[contains(@class, 'fa-search')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'HP LP3065')]")).click();

		String availProduct = driver.findElement(By.xpath("//h1[contains(text(),'HP LP3065')]")).getText();

		Assert.assertEquals(availProduct, dataProp.getProperty("productName1"), "Product Available");
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys("ASUS");
		driver.findElement(By.xpath("//button[@type='button']//i[contains(@class, 'fa-search')]")).click();

		String noProductString = driver
				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]"))
				.getText();

		Assert.assertEquals(noProductString, dataProp.getProperty("noProductMatch"),
				dataProp.getProperty("noProductMatch"));

	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {

		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//button[@type='button']//i[contains(@class, 'fa-search')]")).click();

		String noProductString = driver
				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]"))
				.getText();

		Assert.assertEquals(noProductString, dataProp.getProperty("noProductMatch"),
				dataProp.getProperty("noProductMatch"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
