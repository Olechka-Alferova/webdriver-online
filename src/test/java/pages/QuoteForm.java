package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static support.TestContext.getDriver;

public class QuoteForm extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;
    @FindBy(xpath = "//button[@id='formSubmit']")
    private WebElement submitButton;
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "firstName")
    private WebElement firstName;
    @FindBy(id ="middleName")
    private WebElement middleName;
    @FindBy(id = "lastName")
    private WebElement lastName;
    @FindBy(xpath = "//span[text()='Save']")
    private WebElement saveButton;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;
    @FindBy(xpath = "//input[@name='agreedToPrivacyPolicy']")
    private WebElement agreedToPolicy;

    public WebElement errorElement (String fieldName) {
        return getDriver().findElement(By.id(fieldName + "-error"));
    }
    public QuoteForm() {
        url = "https://skryabin.com/market/quote.html";
        title = "Get a Quote";
    }

    public void fillUsername(String value) {
        username.sendKeys(value);
    }
    public void clearUsername() { username.clear(); }

    public String getTextFromUsername() {

        return username.getAttribute("value");
    }

    public void fillEmail(String value) {
        email.sendKeys(value);
    }
    public void clearEmail() { email.clear(); }

    public void fillPassword(String value) {
        password.sendKeys(value);
    }

    public void clearPassword() { password.clear(); }

    public void confirmPassword(String value) {
        confirmPassword.sendKeys(value);
    }

    public void clearConfirmPassword() { confirmPassword.clear(); }

    public void clickAgreedToPolicy() { agreedToPolicy.click(); }

    public String checkAgreedToPolicyStatus() {
        return agreedToPolicy.getAttribute("value");
    }

    public void fillName(String first, String last) {
        name.click();
        firstName.sendKeys(first);
        lastName.sendKeys(last);
        saveButton.click();
    }
    public void fillName (String first,String middle, String last) {
        name.click();
        firstName.sendKeys(first);
        middleName.sendKeys(middle);
        lastName.sendKeys(last);
        saveButton.click();
    }

    public void clearName() { name.clear(); }

    public String getName() {
        return name.getAttribute("value");
    }

    public String getErrorMessage(String fieldName) {
        return errorElement(fieldName).getText();
    }
    public boolean isErrorMessageDisplayed (String fieldName) {
        return errorElement(fieldName).isDisplayed();
    }
    public void clickSubmit() {
        submitButton.click();
    }


}