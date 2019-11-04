package com.teachaway.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.teachaway.base.TestBase;
import com.teachaway.util.ReadExcel;

public class TeachawayHomePage extends TestBase {
	WebDriverWait wait = new WebDriverWait(driver, 30);

	@FindBy(css = "span.dropdown-toggle.tb-megamenu-no-link")
	@CacheLookup
	WebElement listMainMenus;

	@FindBy(xpath = "//span[@class='dropdown-toggle tb-megamenu-no-link'][contains(text(),'Jobs')]")
	@CacheLookup
	WebElement jobMainMenu;

	@FindBy(xpath = "//span[contains(text(),'Tefl')]")
	@CacheLookup
	WebElement teflMainMenu;

	@FindBy(xpath = "//span[@class='dropdown-toggle tb-megamenu-no-link'][contains(text(),'Teacher Certification')]")
	@CacheLookup
	WebElement teacherSubscriptionMainMenu;

	@FindBy(xpath = "//li[@class='tb-megamenu-item level-1 mega open']//a[contains(text(),'Courses')]")
	@CacheLookup
	WebElement coursesMainMenu;

	@FindBy(xpath = "//a[@class='dropdown-toggle hire-teachers']")
	@CacheLookup
	WebElement hireTeachersMainMenu;

	public TeachawayHomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public void verifyMenus() throws InterruptedException {

		ReadExcel menudata = new ReadExcel(
				System.getProperty("user.dir") + "/src/main/java/com/teachaway/testdata/TestData.xlsx");

		List<WebElement> MainMenusLevelOne = driver.findElements(By.cssSelector("li.tb-megamenu-item.level-1.mega"));
		for (WebElement menuLevelOne : MainMenusLevelOne) {
			System.out.println(menuLevelOne.getText() + " Menu is present.");
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class='dropdown-toggle tb-megamenu-no-link'][contains(text(),'Jobs')]")));

		Actions action = new Actions(driver);
		action.moveToElement(jobMainMenu).build().perform();
		Thread.sleep(3000);
		if (jobMainMenu.isDisplayed()) {
			System.out.println("Job Main Menu is dispalyed");
		}
		verifyMainMenusLink(menudata.getData(0, 0, 0));
		for (int i = 1; i <= 5; i++) {
			verifyMainMenus(menudata.getData(0, i, 0));
		}
		Thread.sleep(3000);
		if (teflMainMenu.isDisplayed()) {
			System.out.println("TEFL Main Menu is displayed");
		}
		action.moveToElement(teflMainMenu).build().perform();
		verifyMainMenusLink(menudata.getData(1, 0, 0));
		verifyMainMenusLink(menudata.getData(1, 1, 0));
		Thread.sleep(3000);
		if (teacherSubscriptionMainMenu.isDisplayed()) {
			System.out.println("Teacher Subscription Main Menu is displayed");
		}
		action.moveToElement(teacherSubscriptionMainMenu).build().perform();
		verifyMainMenusLink(menudata.getData(2, 0, 0));
		verifyMainMenusLink(menudata.getData(2, 1, 0));
		Thread.sleep(3000);
		if (hireTeachersMainMenu.isDisplayed()) {
			System.out.println("Hire Teacher Main Menu is displayed");
		}
		action.moveToElement(hireTeachersMainMenu).build().perform();

	}

	private void verifyMainMenus(String menuOption) throws InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + menuOption + "')]")));
		WebElement jobBoardLink = driver.findElement(By.xpath("//span[contains(text(),'" + menuOption + "')]"));
		if (jobBoardLink.isDisplayed()) {
			Reporter.log("[SUCESS] Verified " + menuOption + " Menu is displayed.");
		} else {
			Reporter.log("[ERROR] " + menuOption + " link is not present.");
		}

	}

	private void verifyMainMenusLink(String linkText) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + linkText + "')]")));
		WebElement jobBoardLink = driver.findElement(By.xpath("//a[contains(text(),'" + linkText + "')]"));
		Assert.assertTrue(jobBoardLink.isDisplayed(), "" + linkText + " link is not present");
		Reporter.log("[SUCESS] Verified " + linkText + " link is displayed.");
	}

}
