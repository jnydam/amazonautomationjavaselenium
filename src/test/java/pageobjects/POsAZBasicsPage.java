package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class POsAZBasicsPage {

    WebDriver driver;

    public static String identifier = "//input[@type='search']";

    public POsAZBasicsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyAzBasicsIdentifierIsDisplayed() {
        Assert.assertTrue(this.driver.findElement(By.xpath(identifier)).isDisplayed());
    }
}