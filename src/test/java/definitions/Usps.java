package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.TestContext;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.*;

public class Usps {
    @When("I go to Lookup ZIP page by address")
    public void iGoToLookupZIPPageByAddress() {
        getDriver().findElement(By.xpath("//a[@id='mail-ship-width']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href,'ZipLookupAction!')]")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Find by Address')]")).click();
    }

    @And("I fill out {string} street, {string} city, {string} state")
    public void iFillOutStreetCityState(String street, String city, String state) {
        getDriver().findElement(By.id("tAddress")).sendKeys(street);
        getDriver().findElement(By.id("tCity")).sendKeys(city);
//        getDriver().findElement(By.xpath("//*[@id='tState']/option[@value='" + state + "']")).click();
//        OR you can use selenium class Select to work with the dropdown list. It has mach more options
        WebElement stateElement = getDriver().findElement(By.id("tState")); // find only dropdown list
        new Select(stateElement).selectByValue(state); // 'Select' method that can be used to get option from the list
        getDriver().findElement(By.xpath("//a[@id='zip-by-address']")).click();
    }

    @Then("I validate {string} zip code exists in the result")
    public void iValidateZipCodeExistsInTheResult(String zipCode) {
        // explicit wait we have to use in case if the element on the page but the data not loaded
        WebDriverWait explicitWait = new WebDriverWait(getDriver(), 5);
//        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='zipByAddressDiv']//p")));
//        String results = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']")).getText();
//        or you can use other way:
        WebElement resultsElement = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));
//        explicitWait.until(driver -> !resultsElement.getText().isEmpty());
        //OR
        explicitWait.until(driver -> resultsElement.isDisplayed());
        String results = resultsElement.getText();
        assertThat(results).containsIgnoringCase(zipCode);
    }

    @When("I go to Calculate Price Page")
    public void iGoToCalculatePricePage() {
        getDriver().findElement(By.xpath("//a[@id='mail-ship-width']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href,'calculateretail')]")).click();
    }

    @And("I select {string} with {string} shape")
    public void iSelectWithShape(String country, String shipMethod) {
        WebElement countryElement = getDriver().findElement(By.id("CountryID"));
        new Select(countryElement).selectByVisibleText(country);

        switch (shipMethod) {
            case ("Postcard"):
                getDriver().findElement(By.id("option_1")).click();
                break;
            case ("Envelope"):
                getDriver().findElement(By.id("option_2")).click();
                break;
            case ("Box"):
                getDriver().findElement(By.id("option_3")).click();
                break;
            case ("Custom"):
                getDriver().findElement(By.id("option_4")).click();
                break;
            default:
                throw new RuntimeException("Not supported shipping Method! " + shipMethod);
        }
    }

    @And("I define {string} quantity")
    public void iDefineQuantity(String quantity) {
        getDriver().findElement(By.id("quantity-0")).sendKeys(quantity);
    }

    @Then("I calculate the price and validate cost is {string}")
    public void iCalculateThePriceAndValidateCostIs(String price) {
        getDriver().findElement(By.xpath("//input[@value='Calculate']")).click();
        String totalPrice = getDriver().findElement(By.id("total")).getText();
        assertThat(totalPrice).contains(price);
    }

    @Then("I validate the price is {string}")
    public void iValidateThePriceIs(String price) {
        WebDriverWait explicitWait = new WebDriverWait(getDriver(),5);
        WebElement resultsElement = getDriver().findElement(By.id("mail-services-sm-lg"));
        explicitWait.until(driver -> resultsElement.isDisplayed());

        String totalPrice = resultsElement.getText();
        assertThat(totalPrice).contains(price);
    }
}
