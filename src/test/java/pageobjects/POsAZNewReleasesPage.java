package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class POsAZNewReleasesPage {

    WebDriver driver;

    public static String identifier = "//h1[contains(text(), 'Amazon Hot New Releases')]";

    public POsAZNewReleasesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyNewReleasesPageIsDisplayed() {
        Assert.assertTrue(this.driver.findElement(By.xpath(identifier)).isDisplayed());
    }
}