package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class POsAZHomePage {

    WebDriver driver;
    Actions actions;

    public String homeSearchBoxIdentifier = "//input[@id='twotabsearchtextbox']";

    public String navSearchSubmitButtonIdentifier = "//input[@id='nav-search-submit-button']";

    public String amazonToggleAllButtonIdentifier = "#nav-hamburger-menu";

    public String accountAndListsTabButtonIdentifier = "//a[@data-nav-role='signin' and contains(., 'Account & Lists')]";

    public String careersLinkIdentifier = "// a[@class='nav_a' and contains(text(), 'Careers')]";

    public String startHereLink = "//a[text()='Start here.']";

    public WebElement getNavLinkByText(String text) {
        return this.driver
                .findElement(By.xpath("//div['nav-link']//a[@data-csa-c-type='link' and text()='" + text + "']"));
    }

    public void hoverAccountAndListsTabButtonIdentifier() {
        this.actions.moveToElement(this.driver.findElement(By.xpath(accountAndListsTabButtonIdentifier))).perform();
        ;
    }

    public void clickStartHereLink() {
        this.driver.findElement(By.xpath(startHereLink)).click();
    }

    public void clickAmazonToggleAllButton() {
        this.driver.findElement(By.cssSelector(amazonToggleAllButtonIdentifier)).click();
    }

    public void typeIntoAmazonSearchBox(String providedText) {
        this.driver.findElement(By.xpath(this.homeSearchBoxIdentifier)).sendKeys(providedText);
    }

    public void clickNavLinkByText(String text) {
        this.getNavLinkByText(text).click();
    }

    public void clickCareersLink() {
        this.driver.findElement(By.xpath(careersLinkIdentifier)).click();
    }

    public POsAZHomePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void clickSearchTopButton() {
        this.driver.findElement(By.xpath(this.navSearchSubmitButtonIdentifier)).click();
    }

}