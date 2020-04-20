package pages;

import org.openqa.selenium.support.PageFactory;

import static support.TestContext.getDriver;

public class BasePage {
    protected String url;
    protected String title;

    public BasePage() {
        PageFactory.initElements(getDriver(),this);
    }

    public void open() {
        getDriver().get(url);
    }
}
