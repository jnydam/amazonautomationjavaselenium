package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class POsAZLeftNavigationPage {

    WebDriver driver;
    Actions actions;

    public static String identifier = "//h1[text()='Amazon Best Sellers']";

    public POsAZLeftNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void clickGenericLeftSideItemByText(String text) {
        actions.moveToElement(this.driver.findElement(By.xpath("//a[normalize-space(.) = '" + text + "']"))).click()
                .perform();
    }

    public boolean verifyBestSellersIdentifierIsDisplayed() {
        return this.driver.findElement(By.xpath(identifier)).isDisplayed();
    }

    public void hoverOverGenericLeftSideItemText(String text) {
        actions.moveToElement(
                this.driver.findElement(By.xpath("//a[normalize-space(.) = '" + text + "']")))
                .perform();
    }
}
