package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class POsAZCareersPage {

    WebDriver driver;

    Actions actions;

    public String jobSearchBox = "#search_typeahead-homepage";

    public String locationSearchBox = "#location-typeahead-homepage";

    public String jobSearchLocationEntryIdentifier = "tt-option-location-results";

    public String careersSearchButton = "#search-button";

    public POsAZCareersPage(WebDriver webDriver) {
        this.driver = webDriver;
        this.actions = new Actions(webDriver);
    }

    public void verifyJobsSearchInputIsDisplayed() {
        Assert.assertTrue(this.driver.findElement(By.cssSelector(this.jobSearchBox)).isDisplayed());

    }

    public void typeIntoJobSearchInput(String providedText) {
        this.driver.findElement(By.cssSelector(this.jobSearchBox)).sendKeys(providedText);
    }

    public void typeIntoLocationSearchBox(String providedText) {
        this.driver.findElement(By.cssSelector(this.locationSearchBox)).sendKeys(providedText);
    }

    public void clickJobSearchLocationResultByText(String text) {
        this.driver.findElement(By.xpath(
                "//div[contains(@id, 'tt-option-location-results') and normalize-space(.) = '" + text + "']"))
                .click();
    }

    public void clickCareersSearchButton() {

        this.actions.moveToElement(this.driver.findElement(By.cssSelector(this.careersSearchButton))).doubleClick()
                .perform();
    }

}