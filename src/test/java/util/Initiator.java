package util;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class Initiator {

    public static final String AMAZONURL = "https://www.amazon.com";
    public static final String CAREERSPAGEURL = "https://www.amazon.jobs/en/";

    public static WebDriver startDriver(ChromeOptions chromeOptions, WebDriver webDriver) {

        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--ignore-certificate-errors");

        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return webDriver;
    };

    public static void afterTestHandling(WebDriver driver, ITestResult result, ITestContext context) {
        if (result.getMethod().getDataProviderMethod() != null) {
            String customName = (String) context.getAttribute("testDescription");
            if (customName != null) {
                result.getMethod().setDescription(customName);
            }
        }
        System.out.println("Test has ended");
        driver.quit();
    };

}
