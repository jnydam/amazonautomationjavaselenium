package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class POsAZCareersSearchResultsPage {

    WebDriver driver;
    Actions actions;

    public String searchResultsTitleIdentifier = "//h1[text()='Search results']";
    public String acceptAllCookiesIdentifier = "#btn-accept-cookies";

    public POsAZCareersSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void clickIndustryExperienceOptionByText(String text) {
        this.actions.moveToElement(this.driver.findElement(By.xpath(
                "//button[@name='desktopFilter_industry_experience' and contains(., '" + text + "')]"))).click()
                .perform();
        ;
    }

    public void clickAcceptAllCookiesButton() {
        this.driver.findElement(By.cssSelector(acceptAllCookiesIdentifier)).click();
    }

    public void clickCategoryOptionByText(String text) {

        WebElement givenElement = this.driver
                .findElement(
                        By.xpath(
                                "//button[@name='desktopFilter_job_category' and contains(., '" + text + "')]"));

        givenElement.findElement(By.xpath("div[@class='checkbox-icon-container']")).click();
        ;
    }

    public boolean isSearchResultsPresence() {
        return this.driver.findElement(By.xpath(searchResultsTitleIdentifier)).isDisplayed();
    }

    public void verifySearchResultsTitleIdentifierIsDisplayed() {
        Assert.assertTrue(this.driver.findElement(By.xpath(searchResultsTitleIdentifier)).isDisplayed());
    }

}
