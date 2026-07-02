package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class POsAZWholeFoodsHeaderPage {

    WebDriver driver;

    public static String identifier = "//img[@alt='Whole Foods']";

    public POsAZWholeFoodsHeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyWholeFoodsIdentifierIsDisplayed() {
        Assert.assertTrue(this.driver.findElement(By.xpath("//img[@alt='Whole Foods']")).isDisplayed());
    }
}