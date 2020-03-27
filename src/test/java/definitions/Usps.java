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
        WebDriverWait explicitWait = new WebDriverWait(getDriver(), 5);
        WebElement resultsElement = getDriver().findElement(By.id("mail-services-sm-lg"));
        explicitWait.until(driver -> resultsElement.isDisplayed());

        String totalPrice = resultsElement.getText();
        assertThat(totalPrice).contains(price);
    }

    @And("I verify each row has {string} zip code")
    public void iVerifyEachRowHasZipCode(String zipCode) {
        WebDriverWait explicitWait = new WebDriverWait(getDriver(), 5);
        WebElement resultElements = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));
        explicitWait.until(driver -> resultElements.isDisplayed());

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
        new Actions(getDriver()).moveToElement(mailAndShip).perform();
        getDriver().findElement(By.xpath("//a[@id='navmailship']/../div[@class='repos']")).isDisplayed();
    }

    @Then("I go to QuickTools tab")
    public void iGoToQuickToolsTab() {
        WebElement QuickTools = getDriver().findElement(By.xpath("//a[@class='nav-first-element menuitem']"));
        new Actions(getDriver()).moveToElement(QuickTools).perform();
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
        if (locationTypes.equals("Post Offices™")) {
            getDriver().findElement(By.xpath("//a[string()='Post Offices™']")).click();
            // 'string' in Xpath can be used if we need to get data from 'sup' tag
        } else if (locationTypes.equals("Post Offices™ and Approved Postal Providers®")) {
            getDriver().findElement(By.xpath("//a[contains(string(),'Post Offices™ and Approved')]")).click();
        } else throw new RuntimeException("Not supported Location Type: " + locationTypes);

        getDriver().findElement(By.xpath("//button[@id='service-type-select']")).click();
        if (services.equals("Pickup Services")) {
            getDriver().findElement(By.id("pickupPo")).click();
        } else throw new RuntimeException("Not supported service Type: " + services);

        getDriver().findElement(By.xpath("//button[@id='available-service-select']")).click();
        if (availableServices.equals("Accountable Mail")) {
            getDriver().findElement(By.xpath("//*[@id='available-service-select']/..//a[@data-value='accountable']")).click();
        } else throw new RuntimeException("Not supported available service Type: " + availableServices);
    }

    @And("I fill in {string} street, {string} city, {string} state")
    public void iFillInStreetCityState(String street, String city, String state) {
        getDriver().findElement(By.id("search-input")).click();
        getDriver().findElement(By.id("cityOrZipCode")).sendKeys(city);
        getDriver().findElement(By.xpath("//input[@id='addressLineOne']")).sendKeys(street);

        WebElement stateSelect = getDriver().findElement(By.xpath("//select[@id='servicesStateSelect']"));
        new Select(stateSelect).selectByValue(state);

        getDriver().findElement(By.xpath("//a[text()='Go to Results']")).click();
    }

    @Then("I print the phone number and validate it is {string}")
    public void iPrintThePhoneNumberAndValidateItIs(String phone) {
        WebDriverWait explicitWait = new WebDriverWait(getDriver(), 5);
        WebElement detailRow = getDriver().findElement(By.xpath("//div[@id='1440608']"));
        explicitWait.until(ExpectedConditions.elementToBeClickable(detailRow));
        detailRow.click();
        String phoneResult = getDriver().findElement(By.xpath("//p[@id='detailTollFree']")).getText();
        assertThat(phoneResult).contains(phone);
    }
}
