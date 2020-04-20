package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.*;

public class QuoteResult extends BasePage {
    @FindBy(xpath = "//div[@id='quotePageResult']")
    private WebElement result;
    @FindBy(xpath = "//b[@name='password']")
    private WebElement password;
    @FindBy(xpath = "//b[@name='agreedToPrivacyPolicy']")
    private WebElement privacy;


    public String getResult() {
        return result.getText();
    }
    public String getPassword() {
        return password.getText();
    }
    public String getPrivacyStatus() {
        return privacy.getText();
    }


}
