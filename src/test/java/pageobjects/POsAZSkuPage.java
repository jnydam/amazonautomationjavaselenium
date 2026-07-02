package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POsAZSkuPage {

    WebDriver driver;

    public static String productTitleIdentifier = "#productTitle";

    public POsAZSkuPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSkuPageTitle() {
        return this.driver.findElement(By.cssSelector(productTitleIdentifier));
    }

    public String retrieveProductTitleText() {
        return this.driver.findElement(By.cssSelector(productTitleIdentifier)).getText();
    }

    public boolean verifySkuPageTitleIsDisplayed() {
        return this.getSkuPageTitle().isDisplayed();
    }
}