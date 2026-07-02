package toggleleft;

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
import pageobjects.POsAZHomePage;
import pageobjects.POsAZLeftNavigationPage;
import pageobjects.POsAZSkuPage;
import util.Initiator;

public class ToggleLeftTest implements ITest, ClassExtender {

    ChromeOptions chromeOptions;
    WebDriver webDriver;

    POsAZHomePage pOsAZHomePage;
    POsAZLeftNavigationPage pOsAZLeftNavigationPage;
    POsAZSkuPage pOsAZSkuPage;

    @DataProvider(name = "toggleItems")
    public Object[][] getSearchData() {
        return new Object[][] {
                { "Fire Tablets", "Fire 7" },
                { "Fire Tablets", "Fire HD 8" },
                { "Fire Tablets", "Fire Max 11" },
                { "Fire Tablets", "Fire HD 10 Kids Pro" }
        };
    }

    @BeforeMethod
    public void beforeTest() {
        webDriver = Initiator.startDriver(chromeOptions, webDriver);
        pOsAZHomePage = new POsAZHomePage(webDriver);
        pOsAZLeftNavigationPage = new POsAZLeftNavigationPage(webDriver);
        pOsAZSkuPage = new POsAZSkuPage(webDriver);
    }

    @Test(dataProvider = "toggleItems")
    public void verifyThatTheToggleLeftTestCaseAreAsExpected(String outerLinkTitle, String innerLinkTitle,
            ITestContext testContext)
            throws InterruptedException {
        testContext.setAttribute("testDescription",
                "As an amazon.com customer I should be able to verify that I can select the following options from the left 'All' menu: "
                        + outerLinkTitle + " > " + innerLinkTitle);
        webDriver.get(Initiator.AMAZONURL);
        pOsAZHomePage.clickAmazonToggleAllButton();
        Thread.sleep(1000);
        pOsAZLeftNavigationPage.clickGenericLeftSideItemByText(outerLinkTitle);
        Thread.sleep(2000);
        pOsAZLeftNavigationPage.hoverOverGenericLeftSideItemText(innerLinkTitle);
        pOsAZLeftNavigationPage.clickGenericLeftSideItemByText(innerLinkTitle);
        pOsAZSkuPage.verifySkuPageTitleIsDisplayed();

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
