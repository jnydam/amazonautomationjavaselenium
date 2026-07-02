package jobsearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.POsAZCareersPage;
import pageobjects.POsAZCareersSearchResultsPage;
import pageobjects.POsAZHomePage;
import util.Initiator;
import classutilities.ClassExtender;

public class JobSearchTest implements ITest, ClassExtender {

    ChromeOptions chromeOptions;
    WebDriver webDriver;

    POsAZCareersPage pOsAZCareersPage;
    POsAZCareersSearchResultsPage pOsAZCareersSearchResultsPage;
    POsAZHomePage pOsAZHomePage;

    @DataProvider(name = "minijobsearchparameters")
    public Object[][] getMiniJobSearchParameters() {
        return new Object[][] {
                { "product manager", "Boston, MA, United States" }
        };
    }

    @DataProvider(name = "fulljobsearchparameters")
    public Object[][] getFullJobSearchParamters() {
        return new Object[][] {
                { "software engineer", "Boston, MA, United States", "4-6 years", "Software Development" },
                { "senior product manager", "New York, NY, United States", "7+ years",
                        "Marketing" }
        };
    }

    @BeforeMethod
    public void beforeTest() {
        webDriver = Initiator.startDriver(chromeOptions, webDriver);
        pOsAZCareersPage = new POsAZCareersPage(webDriver);
        pOsAZHomePage = new POsAZHomePage(webDriver);
        pOsAZCareersSearchResultsPage = new POsAZCareersSearchResultsPage(webDriver);
    }

    @Test()
    public void verifyCareersPageIsAbleToLoadCorrectly(ITestContext testContext)
            throws InterruptedException {
        testContext.setAttribute("testDescription",
                "As an amazon.com customer I should be able to verify that the Careers Page loads correctly");
        webDriver.get(Initiator.AMAZONURL);
        pOsAZHomePage.clickCareersLink();
        pOsAZCareersPage.verifyJobsSearchInputIsDisplayed();
    }

    public void handleInitialJobAndLocationSearch(String jobText, String locationText) {
        pOsAZCareersPage.typeIntoJobSearchInput(jobText);
        pOsAZCareersPage.typeIntoLocationSearchBox(locationText);
        pOsAZCareersPage.clickJobSearchLocationResultByText(locationText);
        pOsAZCareersPage.clickCareersSearchButton();
        pOsAZCareersPage.clickCareersSearchButton();
        pOsAZCareersSearchResultsPage.verifySearchResultsTitleIdentifierIsDisplayed();
    }

    @Test(dataProvider = "minijobsearchparameters")
    public void verifyThatJobTitleAndLocationRendersCorrectly(String jobText,
            String locationText, ITestContext testContext)
            throws InterruptedException {
        testContext.setAttribute("testDescription",
                "As an amazon.com customer I should be able to verify that I can go to the Careers page search for "
                        + jobText + "jobs with location " + locationText);
        webDriver.get(Initiator.CAREERSPAGEURL);
        handleInitialJobAndLocationSearch(jobText, locationText);
    }

    @Test(dataProvider = "fulljobsearchparameters")
    public void verifyThatJobTitleAndLocationRendersCorrectly(String jobText,
            String locationText,
            String industryExperience, String category, ITestContext testContext) throws InterruptedException {
        testContext.setAttribute("testDescription",
                "As an amazon.com customer I should be able to verify that I can go to the Careers page search for "
                        + jobText + "jobs with location " + locationText + " with " + industryExperience
                        + "of experience with the"
                        + category + " category selected");
        webDriver.get(Initiator.CAREERSPAGEURL);
        handleInitialJobAndLocationSearch(jobText, locationText);
        Thread.sleep(1000);
        pOsAZCareersSearchResultsPage.clickIndustryExperienceOptionByText(industryExperience);
        pOsAZCareersSearchResultsPage.clickAcceptAllCookiesButton();
        pOsAZCareersSearchResultsPage.clickCategoryOptionByText(category);
        Thread.sleep(1000);
    }

    @AfterMethod
    public void afterTest(ITestResult result, ITestContext context) {
        Initiator.afterTestHandling(webDriver, result, context);
    }

    @Override
    public String getTestName() {
        return testName.get();
    }
}
