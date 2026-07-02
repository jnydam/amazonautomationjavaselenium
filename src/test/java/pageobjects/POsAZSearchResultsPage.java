package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POsAZSearchResultsPage {

    WebDriver driver;

    public String firstSearchResultIdentifier = "//img[@class='s-image']";

    public String titleProductIdentifier = "//div[@data-cy='title-recipe']//h2";

    public POsAZSearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstSearchResultIdentifier() {
        this.driver.findElement(By.xpath(this.firstSearchResultIdentifier)).click();
    }

    public String retrieveTitleProductIdentifierText() {
        return this.driver.findElement(By.xpath(titleProductIdentifier)).getText();
    }

}