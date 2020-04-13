package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;
import support.TestRunner;

import javax.sound.midi.Soundbank;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.*;

public class Market {
    @Given("I go to {string} page")
    public void iGoToPage(String page) {
        switch (page) {
            case "usps":
                getDriver().get("https://www.usps.com/");
                break;
            case "quote":
                getDriver().get("https://skryabin.com/market/quote.html");
                break;
            case "google":
                getDriver().navigate().to("https://www.google.com/"); // navigate().to(URL) is the same as .get(URL)
                break;
            case "yahoo":
                getDriver().get("https://www.yahoo.com/");
                break;
            case "converter":
                getDriver().get("https://www.unitconverters.net/");
                break;
            case "calculator":
                getDriver().get("http://www.calculator.net/");
                break;
            case "ecosia":
                getDriver().get("https://www.ecosia.org");
                break;
            default:
                throw new RuntimeException("Unsupported page: " + page);
        }
    }

    @And("I print page details")
    public void iPrintPageDetails() {
        System.out.println(getDriver().getTitle());
        System.out.println(getDriver().getCurrentUrl());
        System.out.println(getDriver().getWindowHandles());
        System.out.println(getDriver().getPageSource());
    }

    @And("I go back and forward")
    public void iGoBackAndForward() {
        getDriver().navigate().back();
        getDriver().navigate().forward();
    }

    @Then("I refresh the page")
    public void iRefreshThePage() {
        getDriver().navigate().refresh();
    }

    @And("I validate the conlose log")
    public void iValidateTheConloseLog() {
        getDriver().manage().logs().get("browser");
    }

    //    @And("I change resolution to {string}")
    public void iChangeResolutionTo(String resolution) {
        if (resolution.equals("phone")) {
            int widthA = 400;
            int height = 768;
            Dimension dimension = new Dimension(widthA, height);
            getDriver().manage().window().setSize(dimension);
        } else if (resolution.equals("desktop")) {
            int widthA = 1024;
            int height = 768;
            Dimension dimension = new Dimension(widthA, height);
            getDriver().manage().window().setSize(dimension);
        } else {
            throw new RuntimeException("Unsupported resolution: " + resolution);
        }
    }

    // Or better use Selenium class 'Dimension'
    @And("I change resolution to {string}")
    public void iChangeResolutionToA(String resolution) {
        if (resolution.equals("phone")) {
            getDriver().manage().window().setSize(new Dimension(400, 768));
        } else if (resolution.equals("desktop")) {
            getDriver().manage().window().setSize(new Dimension(1024, 768));
        } else {
            throw new RuntimeException("Unsupported resolution: " + resolution);
        }
    }

    @When("I fill out required fields")
    public void iFillOutRequiredFields() {
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("Testuser");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("test@gmail.com");
        getDriver().findElement(By.id("password")).sendKeys("password1");
        getDriver().findElement(By.id("confirmPassword")).sendKeys("password1");

        getDriver().findElement(By.xpath("//input[@id='name']")).click();
        getDriver().findElement(By.xpath("//input[@id='firstName']")).sendKeys("Olga");
        getDriver().findElement(By.xpath("//input[@id='lastName']")).sendKeys("Alferova");
        getDriver().findElement(By.xpath("//span[text()='Save']")).click();

        getDriver().findElement(By.xpath("//input[@name='agreedToPrivacyPolicy']")).click();
    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        getDriver().findElement(By.xpath("//*[@id='formSubmit']")).click();
    }

    @Then("I verify required fields")
    public void iVerifyRequiredFields() {
        String result = getDriver().findElement(By.xpath("//div[@id='quotePageResult']")).getText();
        assertThat(result).contains("Testuser");
        assertThat(result).contains("test@gmail.com");
        assertThat(result).contains("Olga Alferova");
        assertThat(result).doesNotContain("password1");

        String password = getDriver().findElement(By.xpath("//b[@name='password']")).getText();
        assertThat(password).isEqualTo("[entered]");

        String privacy = getDriver().findElement(By.xpath("//b[@name='agreedToPrivacyPolicy']")).getText();
        assertThat(privacy).isEqualTo("true");
    }

    @And("I verify email field behavior")
    public void iVerifyEmailFieldBehavior() {
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("test.gmail.com");
        getDriver().findElement(By.id("formSubmit")).click();
        assertThat(getDriver().findElement(By.xpath("//label[@id='email-error']")).isDisplayed()).isTrue();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(Keys.BACK_SPACE);
        getDriver().findElement(By.xpath("//input[@name='email']")).clear();

        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("Te_st12a.te-ST@test.US");
        getDriver().findElement(By.id("formSubmit")).click();
        assertThat(getDriver().findElement(By.xpath("//label[@id='email-error']")).isDisplayed()).isFalse();
    }

    @When("I fill out optional fields")
    public void iFillOutOptionalFields() {
        getDriver().findElement(By.xpath("//input[@name='phone']")).sendKeys("+19165559090");
        getDriver().findElement(By.xpath("//select[@name='countryOfOrigin']/option[@value='Russia']")).click();
        getDriver().findElement(By.xpath("//input[@value='female']")).click();
        getDriver().findElement(By.xpath("//input[@name='allowedToContact']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='address']"))
                .sendKeys("2201 Test street 34, Test City, CA, 95999");
        getDriver().findElement(By.xpath("//select[@name='carMake']/option[@value='BMW']")).click();
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        getDriver().switchTo().alert().accept();
        getDriver().findElement(By.xpath("//input[@id='dateOfBirth']")).click();
        getDriver().findElement(By.xpath("(//select[@data-handler='selectMonth']/option)[3]")).click();
        getDriver().findElement(By.xpath("//*[@data-handler='selectYear']/option[@value='1988']")).click();
        getDriver().findElement(By.xpath("//a[text()='19']")).click();
    }

    @Then("I verify that fields values recorded correctly")
    public void iVerifyThatFieldsValuesRecordedCorrectly() {
        String result = getDriver().findElement(By.xpath("//div[@id='quotePageResult']")).getText();
        assertThat(result).contains("female");
        assertThat(result).contains("2201 Test street 34, Test City, CA, 95999");
        assertThat(result).contains("BMW");
        assertThat(result).contains("03/19/1988");

        String phoneNumber = getDriver().findElement(By.xpath("//b[@name='phone']")).getText();
        assertThat(phoneNumber).isEqualTo("19165559090");

        String allowToContact = getDriver().findElement(By.xpath("//b[@name='allowedToContact']")).getText();
        assertThat(allowToContact).isEqualTo("true");

        String thirdPartyAgreed = getDriver().findElement(By.xpath("//b[@name='thirdPartyAgreement']")).getText();
        assertThat(thirdPartyAgreed).isEqualTo("accepted");

        String countryOfOrigin = getDriver().findElement(By.xpath("//b[@name='countryOfOrigin']")).getText();
        assertThat(countryOfOrigin).isEqualTo("Russia");
    }

    @And("I fill multi-select using Action class")
    public void iFillMultiSelectUsingActionClass() throws InterruptedException {
        WebElement ford = getDriver().findElement(By.xpath("//select[@name='carMake']/option[@value='Ford']"));
        WebElement bmw = getDriver().findElement(By.xpath("//select[@name='carMake']/option[@value='BMW']"));
        getActions().
                click(ford).
                keyDown(Keys.COMMAND). // press Command key on the keyboard
                click(bmw).
                perform();
        Thread.sleep(2000);
    }

    @Then("I fill multi-select using Select class")
    public void iFillMultiSelectUsingSelectClass() {
        WebElement carMake = getDriver().findElement(By.xpath("//select[@name='carMake']"));
        Select carSelect = new Select(carMake); // keep in mind that Select class can be used only for the elements with Select Tag
        carSelect.selectByValue("Ford");
        carSelect.selectByValue("BMW");
        carSelect.deselectAll();
    }

    @And("fill out additional info with name {string} and phone {string}")
    public void fillOutAdditionalInfoWithNameAndPhone(String name, String phone) {
        getDriver().switchTo().frame("additionalInfo"); // switch to iFrame by tag name (or ID)
        getDriver().findElement(By.id("contactPersonName")).sendKeys(name);
        getDriver().findElement(By.id("contactPersonPhone")).sendKeys(phone);
        getDriver().switchTo().defaultContent(); // switch back to main content from iFrame
    }

    @And("I verify that iFrame fields values recorded correctly with name {string} and phone {string}")
    public void iVerifyThatIFrameFieldsValuesRecordedCorrectlyWithNameAndPhone(String name, String phone) {
        String resultName = getDriver().findElement(By.xpath("//b[@name='contactPersonName']")).getText();
        String resultPhone = getDriver().findElement(By.xpath("//b[@name='contactPersonPhone']")).getText();
        assertThat(resultName).isEqualTo(name);
        assertThat(resultPhone).isEqualTo(phone);
    }

    @And("I verify {string} present on related docs page")
    public void iVerifyPresentOnRelatedDocsPage(String document) {
        String originalWindow = getDriver().getWindowHandle(); // get original window handle
        getDriver().findElement(By.xpath("//button[contains(@onclick,'window.open')]")).click();

        Set<String> allWindows = getDriver().getWindowHandles(); // get set of all current handles for open windows

        for (String handle : allWindows) {
        getDriver().switchTo().window(handle);  // always will finally switch to last window
        }

        assertThat(getDriver().getTitle()).isEqualTo("Documents Page");
        String result= getDriver().findElement(By.xpath("//html//body//ul")).getText();
        assertThat(result).contains(document);

        getDriver().close(); // optional - to close the current window
        getDriver().switchTo().window(originalWindow); // go back to initial window
    }

    @And("I fill out search engine field with {string} and search")
    public void iFillOutSearchEngineFieldWithAndSearch(String search) {
        getDriver().findElement(By.name("q")).sendKeys(search);
        WebElement submitButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
       // covered submit button so you  can use ENTER button of JS click
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click",submitButton);
    }
}