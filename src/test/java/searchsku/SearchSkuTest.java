package searchsku;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import classutilities.ClassExtender;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestContext;

import pageobjects.POsAZHomePage;
import pageobjects.POsAZSearchResultsPage;
import pageobjects.POsAZSkuPage;
import util.Initiator;

public class SearchSkuTest implements ITest, ClassExtender {

    ChromeOptions chromeOptions;
    WebDriver webDriver;

    POsAZHomePage pOsAZHomePage;
    POsAZSkuPage pOsAZSkuPage;
    POsAZSearchResultsPage pOsAZSearchResultsPage;

    @DataProvider(name = "searchTerms")
    public Object[][] getSearchData() {
        return new Object[][] {
                { "pens" },
                { "paper" },
                { "cables and chargers" },
                { "cooking books" },
                { "rubber bands" },
                { "oven mits" },
                { "brooms" },
                { "trash bags" },
                { "sofas" },
        };
    }

    public SearchSkuTest() {
    }

    @BeforeMethod
    public void beforeTest() {
        webDriver = Initiator.startDriver(chromeOptions, webDriver);
        pOsAZHomePage = new POsAZHomePage(webDriver);
        pOsAZSearchResultsPage = new POsAZSearchResultsPage(webDriver);
        pOsAZSkuPage = new POsAZSkuPage(webDriver);
    }

    @Test(dataProvider = "searchTerms")
    public void verifyThatTheSearchTermsNavigateSuccessfullyToSkuPage(String linkText, ITestContext testContext)
            throws InterruptedException {
        testContext.setAttribute("testDescription",
                "As an amazon.com customer I should be able to verify that I can search for " + linkText
                        + "from Amazon.com home page, and verify that the title of the first search result is same as that on sku page");
        webDriver.get(Initiator.AMAZONURL);
        pOsAZHomePage.typeIntoAmazonSearchBox(linkText);
        pOsAZHomePage.clickSearchTopButton();

        String titleStringSearchPage = pOsAZSearchResultsPage.retrieveTitleProductIdentifierText();
        pOsAZSearchResultsPage.clickFirstSearchResultIdentifier();

        String titleStringSkuPage = pOsAZSkuPage.retrieveProductTitleText();

        Assert.assertEquals(titleStringSearchPage.toLowerCase(), titleStringSkuPage.toLowerCase());

        Thread.sleep(2000);

    }

    @AfterMethod
    public void afterTest(ITestResult testResult, ITestContext context) {
        Initiator.afterTestHandling(webDriver, testResult, context);
    }

    @Override
    public String getTestName() {
        return testName.get();
    }

}
