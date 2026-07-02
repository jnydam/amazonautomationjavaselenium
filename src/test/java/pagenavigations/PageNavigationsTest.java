package pagenavigations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import classutilities.ClassExtender;
import pageobjects.POsAZBasicsPage;
import pageobjects.POsAZBestSellersPage;
import pageobjects.POsAZBooksPage;
import pageobjects.POsAZHomePage;
import pageobjects.POsAZNewReleasesPage;
import pageobjects.POsAZWholeFoodsHeaderPage;
import util.Initiator;

public class PageNavigationsTest implements ITest, ClassExtender {

    ChromeOptions chromeOptions;
    WebDriver webDriver;

    POsAZHomePage pOsAZHomePage;
    POsAZBasicsPage pOsAZBasicsPage;
    POsAZWholeFoodsHeaderPage pOsAZWholeFoodsHeaderPage;
    POsAZBestSellersPage pOsAZBestSellersPage;
    POsAZNewReleasesPage pOsAZNewReleasesPage;
    POsAZBooksPage pOsAZBooksPage;

    @DataProvider(name = "headerLinks")
    public Object[][] getSearchData() {
        return new Object[][] {
                { "Amazon Basics" },
                { "Whole Foods" },
                { "Best Sellers" },
                { "New Releases" },
                { "Books" },
        };
    }

    @BeforeMethod
    public void beforeTest() {
        webDriver = Initiator.startDriver(chromeOptions, webDriver);
        pOsAZHomePage = new POsAZHomePage(webDriver);
        pOsAZBasicsPage = new POsAZBasicsPage(webDriver);
        pOsAZWholeFoodsHeaderPage = new POsAZWholeFoodsHeaderPage(webDriver);
        pOsAZBestSellersPage = new POsAZBestSellersPage(webDriver);
        pOsAZNewReleasesPage = new POsAZNewReleasesPage(webDriver);
        pOsAZBooksPage = new POsAZBooksPage(webDriver);

    }

    @Test(dataProvider = "headerLinks")
    public void verifyThatAmazonBasicsPageLoadsAndFunctions(String linkText, ITestContext testContext)
            throws InterruptedException {
        testContext.setAttribute("testDescription",
                "As an amazon.com customer I should be able to login with " + linkText);
        webDriver.get(Initiator.AMAZONURL);
        pOsAZHomePage.clickNavLinkByText(linkText);
        switch (linkText) {
            case "Amazon Basics":
                pOsAZBasicsPage.verifyAzBasicsIdentifierIsDisplayed();
                break;
            case "Whole Foods":
                pOsAZWholeFoodsHeaderPage.verifyWholeFoodsIdentifierIsDisplayed();
                break;
            case "Best Sellers":
                pOsAZBestSellersPage.verifyBestSellersIdentifierIsDisplayed();
                break;
            case "New Releases":
                pOsAZNewReleasesPage.verifyNewReleasesPageIsDisplayed();
                break;
            case "Books":
                pOsAZBooksPage.verifyBooksIdentifierIsDisplayed();
                break;
            default:
                break;
        }
    }

    @Override
    public String getTestName() {
        return testName.get();
    }

    @AfterMethod
    public void afterTest(ITestResult testResult, ITestContext context) {
        Initiator.afterTestHandling(webDriver, testResult, context);
    }

}
