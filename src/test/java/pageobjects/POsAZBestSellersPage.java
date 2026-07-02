package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class POsAZBestSellersPage {

    WebDriver driver;

    public static String identifier = "//h1[text()='Amazon Best Sellers']";

    public POsAZBestSellersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyBestSellersIdentifierIsDisplayed() {
        Assert.assertTrue(this.driver.findElement(By.xpath(identifier)).isDisplayed());
    }
}