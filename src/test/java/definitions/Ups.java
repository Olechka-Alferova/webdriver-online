package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.*;

public class Ups {

    @When("I go to Create Shipment page")
    public void iGoToCreateShipmentPage() {
        getDriver().findElement(By.xpath("//a[@id='ups-menuLinks1']")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Create a Shipment:')]")).click();
    }

    @And("I fill out shipping page with country {string} name {string} address {string} zip {string} email {string} phone {string} city {string}")
    public void iFillOutShippingPageWithCountryNameAddressZipEmailPhone(String country, String name,
                                                                        String address, String zip,
                                                                        String email, String phone, String city) {
        getDriver().findElement(By.xpath("//button[@class='close_btn_thick']")).click(); // close cookies window

        WebElement countryList = getDriver().findElement(By.xpath("//select[@id='origincountry']"));
        new Select(countryList).selectByVisibleText(country);

        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys(name);
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys(address);
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys(zip);
        getWait().until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@id='origincity']"),city));
        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys(email);
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys(phone);

    }

    @Then("I submit the shipping form")
    public void iSubmitTheShippingForm() {
        getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']")).click();
    }

    @And("I verify submitted data: country {string} name {string} address {string} zip {string} email {string} phone {string}")
    public void iVerifySubmittedDataCountryNameAddressZipEmailPhone(String country, String name,
                                                                    String address, String zip,
                                                                    String email, String phone) {
        assertThat(getDriver().findElement(By.id("originnbsAgentSummaryCountryCode")).getText()).isEqualTo(country);
        assertThat(getDriver().findElement(By.id("originnbsAgentSummaryName")).getText()).isEqualTo(name);
        assertThat(getDriver().findElement(By.id("originnbsAgentSummaryAddressLine1")).getText()).isEqualTo(address);
        assertThat(getDriver().findElement(By.id("originnbsAgentSummaryPostalCode")).getText()).isEqualTo(zip);
        assertThat(getDriver().findElement(By.id("originnbsAgentSummaryEmail")).getText()).isEqualTo(email);
        assertThat(getDriver().findElement(By.xpath("//span[@id='originnbsAgentSummaryPhone']/..")).getText()).contains(phone);
    }

    @And("I cancel the form")
    public void iCancelTheForm() {
        getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationCancelShipmentButton']")).click();
        getDriver().findElement(By.xpath("//button[@id='nbsCancelShipmentWarningYes']")).click();
    }

    @Then("I verify form reset successfully")
    public void iVerifyFormResetSuccessfully() {
        iSubmitTheShippingForm();
        String validationMessage = getDriver().findElement(By.xpath("//ul[@class='ups-icons-error']")).getText();
        assertThat(validationMessage).contains("Name is required");
        assertThat(validationMessage).contains("Email is required");
        assertThat(validationMessage).contains("Address Line 1 is required");
    }
}
