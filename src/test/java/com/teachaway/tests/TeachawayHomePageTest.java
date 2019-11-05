package com.teachaway.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.teachaway.base.TestBase;
import com.teachaway.pages.TeachawayHomePage;
import com.teachaway.util.TestUtil;

public class TeachawayHomePageTest extends TestBase {
	TestUtil testUtil;
	TeachawayHomePage techawayHomePage;

	public TeachawayHomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();

	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() throws InterruptedException, IOException {
		techawayHomePage = new TeachawayHomePage();
		Reporter.log("[SUCESS] Teachaway site launched.");
		String techawayHomePageTitle = techawayHomePage.verifyHomePageTitle();
		Assert.assertEquals(techawayHomePageTitle, "Teach Abroad - Teach Away - Discover Where Teaching Can Take You",
				"Techaway Home page title not matched");
		Reporter.log("[SUCESS] Verified Teachaway page title.");
		
		techawayHomePage.verifyMenus();
		TestUtil.takeScreenshotAtEndOfTest();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
