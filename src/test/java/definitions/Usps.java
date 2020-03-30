package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.TestContext;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.*;

public class Usps {
    @When("I go to Lookup ZIP page by address")
    public void iGoToLookupZIPPageByAddress() {
        WebElement mailAndShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        new Actions(getDriver()).moveToElement(mailAndShip).perform(); // mouseover
        getDriver().findElement(By.xpath("//li[@class='tool-zip']")).click();
//        getDriver().findElement(By.xpath("//a[contains(@href,'ZipLookupAction!')]")).click();
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
//        explicitWait.until(driver -> resultsElement.isDisplayed());
        explicitWait.until(ExpectedConditions.textToBePresentInElement(resultsElement, zipCode));
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
        WebElement resultsElement = getDriver().findElement(By.id("mail-services-sm-lg"));
        getWait().until(driver -> resultsElement.isDisplayed());

        String totalPrice = resultsElement.getText();
        assertThat(totalPrice).contains(price);
    }

    @And("I verify each row has {string} zip code")
    public void iVerifyEachRowHasZipCode(String zipCode) {
        WebElement resultElements = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));
        getWait().until(driver -> resultElements.isDisplayed());

        List<WebElement> zipCoderesults = getDriver().findElements(By.xpath("//div[@id='zipByAddressDiv']//strong"));
        for (WebElement element : zipCoderesults) {
            assertThat(element.getText()).contains(zipCode);
        }
    }

    @And("I print browser log in the console")
    public void iPrintBrowserLogInTheConsole() {
//        LogEntries logs = getDriver().manage().logs().get(LogType.DRIVER); // or just .get("driver")
        LogEntries logs = getDriver().manage().logs().get(LogType.BROWSER); // or just .get("browser")
        System.out.println(">>>>>>> Browser logs: Begin");
        for (LogEntry entry : logs) {
            System.out.println(entry);
        }
        System.out.println(">>>>>>> Browser logs: End");
    }

    @When("I go to MailAndShip tab")
    public void iGoToMailAndShipTab() {
        WebElement mailAndShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        getActions().moveToElement(mailAndShip).perform();
        getDriver().findElement(By.xpath("//a[@id='navmailship']/../div[@class='repos']")).isDisplayed();
    }

    @Then("I go to QuickTools tab")
    public void iGoToQuickToolsTab() {
        WebElement QuickTools = getDriver().findElement(By.xpath("//a[@class='nav-first-element menuitem']"));
        getActions().moveToElement(QuickTools).perform();
        getDriver().findElement(By.xpath("//a[@id='navquicktools']/..//ul")).isDisplayed();
    }

    @And("I validate mouseover for header tabs")
    public void iValidateMouseoverForHeaderTabs() {
        iGoToMailAndShipTab();
        iGoToQuickToolsTab();
        assertThat(getDriver().findElement(By.xpath("//a[@id='navmailship']/../div[@class='repos']")).isDisplayed()).isFalse();
    }

    @When("I go to Find a Location Page")
    public void iGoToFindALocationPage() {
        iGoToMailAndShipTab();
        getDriver().findElement(By.xpath("//li[@class='tool-find']")).click();
    }

    @And("I filter by {string} Location Types, {string} Services, {string} Available Services")
    public void iFilterByLocationTypesServicesAvailableServices(String locationTypes, String services, String availableServices) {
        getDriver().findElement(By.xpath("//button[@id='post-offices-select']")).click();
        getDriver().findElement(By.xpath("//a[string()='" + locationTypes + "']")).click();
        // 'string' in Xpath can be used if we need to get data from 'sup' tag

        getDriver().findElement(By.xpath("//button[@id='service-type-select']")).click();
        getDriver().findElement(By.xpath("//a[string()='" + services + "']")).click();

        getDriver().findElement(By.xpath("//button[@id='available-service-select']")).click();
        getDriver().findElement(By.xpath("//a[string()='" + availableServices + "']")).click();
    }

    @And("I fill in {string} street, {string} city, {string} state")
    public void iFillInStreetCityState(String street, String city, String state) {
        getDriver().findElement(By.id("search-input")).click();

        WebElement streetAddress = getDriver().findElement(By.xpath("//input[@id='addressLineOne']"));
        getWait(2).until(ExpectedConditions.visibilityOf(streetAddress));
        streetAddress.sendKeys(street);

//        if (!streetAddress.getAttribute("value").equals(streetAddress)) {
//            streetAddress.clear();
//            streetAddress.sendKeys(street);
//        }
        for (int i = 0; !streetAddress.getAttribute("value").equals(street) && i < 5; i++) {
            // getAttribute("value") - all inputs have "value" as default even it's not in DOM !!!
            streetAddress.clear();
            streetAddress.sendKeys(street);
        }

        getDriver().findElement(By.id("cityOrZipCode")).sendKeys(city);

        WebElement stateSelect = getDriver().findElement(By.xpath("//select[@id='servicesStateSelect']"));
        new Select(stateSelect).selectByValue(state);

        getDriver().findElement(By.xpath("//a[text()='Go to Results']")).click();
    }

    @Then("I print the phone number and validate it is {string}")
    public void iPrintThePhoneNumberAndValidateItIs(String phone) {
        WebElement detailRow = getDriver().findElement(By.xpath("//div[@id='1440608']"));
        getWait().until(ExpectedConditions.elementToBeClickable(detailRow));
        detailRow.click();
        String phoneResult = getDriver().findElement(By.xpath("//p[@id='detailTollFree']")).getText();
        assertThat(phoneResult).contains(phone);
    }
}
