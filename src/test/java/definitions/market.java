package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import support.TestContext;

import javax.sound.midi.Soundbank;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.*;

public class market {
    @Given("I go to {string} page")
    public void iGoToPage(String page) {
        switch (page) {
            case "quote":
                getDriver().get("https://skryabin.com/market/quote.html");
                break;
            case "google":
                getDriver().navigate().to("https://www.google.com/"); // navigate().to(URL) is the same as .get(URL)
                break;
            case "yahoo":
                getDriver().get("https://www.yahoo.com/");
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
            getDriver().manage().window().setSize(new Dimension(400,768));
        } else if (resolution.equals("desktop")) {
            getDriver().manage().window().setSize(new Dimension(1024,768));
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
}