package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class POsAZNewEmailAccountCreationPage {

    public WebDriver driver;

    public String emailPasswordIdentifier = "#ap_email";
    public String passwordIdentifier = "#ap_password";
    public String reEnterPasswordInputIdentifier = "#ap_password_check";
    public String customerNameIdentifier = "#ap_customer_name";
    public String verifyEmailButtonIdentifier = "#continue";
    public String proceedToCreateAccountPath = "//input[@class='a-button-input']";
    public String verifyEmailAddressIdentifier = "//h1[contains(.,'Verify email address')]";
    public String fullNameInputIdentifier = "#ap_customer_name";

    public POsAZNewEmailAccountCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void typeIntoEmailAndUsernameInput(String text) {
        this.driver.findElement(By.cssSelector(this.emailPasswordIdentifier)).sendKeys(text);
    }

    public void typeIntoPasswordInput(String text) {
        this.driver.findElement(By.cssSelector(this.passwordIdentifier)).sendKeys(text);
    }

    public void typeIntoRenterPasswordInput(String text) {
        this.driver.findElement(By.cssSelector(this.reEnterPasswordInputIdentifier)).sendKeys(text);
    }

    public void clickVerifyEmailButton() {
        this.driver.findElement(By.cssSelector(this.verifyEmailButtonIdentifier)).click();
    }

    public void verifyEmailAddressTextIsDisplayed() {
        Assert.assertTrue(this.driver.findElement(By.xpath(verifyEmailAddressIdentifier)).isDisplayed());
    }

    public void typeIntoFullNameIdentifier(String text) {
        this.driver.findElement(By.cssSelector(this.fullNameInputIdentifier)).sendKeys(text);
    }

}
