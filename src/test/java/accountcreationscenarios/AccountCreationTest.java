package accountcreationscenarios;

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
import pageobjects.POsAZInitialEmailSignInPage;
import pageobjects.POsAZNewEmailAccountCreationPage;
import util.Initiator;

public class AccountCreationTest implements ITest, ClassExtender {

    ChromeOptions chromeOptions;
    WebDriver webDriver;
    POsAZHomePage pOsAZHomePage;
    POsAZInitialEmailSignInPage pOsAZInitialEmailSignInPage;
    POsAZNewEmailAccountCreationPage pOsAZNewEmailAccountCreationPage;

    @DataProvider(name = "searchKeywords")
    public Object[][] getSearchData() {
        return new Object[][] {
                { "randomemailuser1@test.com", "Jane Smith", "testpassword3" },
                { "leightonkramer@test.com", "Leighton Kramer", "leightonkramerpassword" },
                { "saigefuentes@test.com", "Saige Fuentes", "saigefuentespassword" },
                { "marcelineavila@test.com", "Marceline Avila", "marcelineavilapassword" }
        };
    }

    @BeforeMethod
    public void beforeTest() {
        webDriver = Initiator.startDriver(chromeOptions, webDriver);
        pOsAZHomePage = new POsAZHomePage(webDriver);
        pOsAZInitialEmailSignInPage = new POsAZInitialEmailSignInPage(webDriver);
        pOsAZNewEmailAccountCreationPage = new POsAZNewEmailAccountCreationPage(webDriver);
    }

    @Test(dataProvider = "searchKeywords")
    public void verifyThatAmazonNewAccountCreationIsFunctional(String email, String fullName, String password,
            ITestContext testContext)
            throws InterruptedException {
        testContext.setAttribute("testDescription",
                "As an amazon.com customer I should be able to verify that I can sign up successfully for a new Amazon account");
        webDriver.get(Initiator.AMAZONURL);
        pOsAZHomePage.hoverAccountAndListsTabButtonIdentifier();
        pOsAZHomePage.clickStartHereLink();
        pOsAZInitialEmailSignInPage.typeIntoEmailAndUsernameInput(email);
        pOsAZInitialEmailSignInPage.clickContinueButton();
        Thread.sleep(1000);
        pOsAZInitialEmailSignInPage.clickProceedToCreateAccountButton();
        pOsAZNewEmailAccountCreationPage.typeIntoPasswordInput(password);
        pOsAZNewEmailAccountCreationPage.typeIntoFullNameIdentifier(fullName);
        pOsAZNewEmailAccountCreationPage.typeIntoRenterPasswordInput(password);
        pOsAZNewEmailAccountCreationPage.clickVerifyEmailButton();
    }

    @AfterMethod
    public void afterTest(ITestContext context, ITestResult result) {
        Initiator.afterTestHandling(webDriver, result, context);
    }

    @Override
    public String getTestName() {
        return testName.get();
    }

}
