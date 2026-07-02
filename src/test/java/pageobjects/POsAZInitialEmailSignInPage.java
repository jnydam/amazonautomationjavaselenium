package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POsAZInitialEmailSignInPage {

    public WebDriver driver;

    public String mobileNumberEmailInputIdentifier = "#ap_email_login";
    public String passwordIdentifier = "#ap_password";
    public String continueButtonPath = "//input[@class='a-button-input']";

    public POsAZInitialEmailSignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void typeIntoEmailAndUsernameInput(String text) {
        this.driver.findElement(By.cssSelector(this.mobileNumberEmailInputIdentifier)).sendKeys(text);
    }

    public void typeIntoPasswordInput(String text) {
        this.driver.findElement(By.cssSelector(this.passwordIdentifier)).sendKeys(text);
    }

    public void clickContinueButton() {
        this.driver.findElement(By.xpath(this.continueButtonPath)).click();
    }

    public void clickProceedToCreateAccountButton() {
        this.driver.findElement(By.xpath(this.continueButtonPath)).click();
    }

}
