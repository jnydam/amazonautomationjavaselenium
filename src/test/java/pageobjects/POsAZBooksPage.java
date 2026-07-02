package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class POsAZBooksPage {

    WebDriver driver;

    public static String identifier = "//span[contains(text(), 'What everyone')]";

    public POsAZBooksPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyBooksIdentifierIsDisplayed() {
        Assert.assertTrue(this.driver.findElement(By.xpath(identifier)).isDisplayed());
    }
}