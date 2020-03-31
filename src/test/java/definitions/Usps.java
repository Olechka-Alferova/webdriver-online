package definitions;

import com.google.common.collect.Iterables;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

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

    @When("I go to {string} tab")
    public void iGoToTab(String arg0) {
        getDriver().findElement(By.xpath("//a[@class='menuitem'][contains(text(),'Help')]")).click();
    }

    @And("I perform {string} help search")
    public void iPerformHelpSearch(String search) {
        getDriver().findElement(By.xpath("//input[contains(@class,'search-field')]")).sendKeys(search);
        getDriver().findElement(By.xpath("//ul[@class='lookup__list  visible']")).click();
    }

    @Then("I verify that no results of {string} available in help search")
    public void iVerifyThatNoResultsOfAvailableInHelpSearch(String result) {

       List<WebElement> resultElements = getDriver().findElements(By.xpath("//div[@class='slds-tile']"));
       getWait().until(ExpectedConditions.visibilityOfAllElements(resultElements));

       for (WebElement element : resultElements) {
          assertThat(element.getText().contains(result)).isFalse();
       }
    }

    @When("I go to {string} under {string}")
    public void iGoToUnder(String dropItem, String menuItem) {
        WebElement menuitemEl = getDriver().findElement(By.xpath("//a[@class='menuitem'][contains(text(),'"+ menuItem +"')]"));
        getActions().moveToElement(menuitemEl).perform();
        getDriver().findElement(By.xpath("//a[contains(text(),'"+ dropItem +"')]")).click();
    }

    @And("I search for {string}")
    public void iSearchFor(String address) {
        getDriver().findElement(By.id("address")).sendKeys(address);
        getDriver().findElement(By.xpath("//fieldset[@class='search-form-field-group']/button[@type='submit']")).click();
    }

    @And("I click {string} on the map")
    public void iClickOnTheMap(String showTable) {
        By element = By.xpath("//a[contains(text(),'" +showTable +"')]");
        getWait(10).until(ExpectedConditions.elementToBeClickable(element));
        getDriver().findElement(element).click();
    }

    @When("I click {string} on the table")
    public void iClickOnTheTable(String selectAll){
        getDriver().findElement(By.xpath("//a[@class='totalsArea']")).click();
    }

    @And("I close modal window")
    public void iCloseModalWindow() {
        getDriver().findElement(By.xpath("//div[@id='modal-box-closeModal']")).click();
    }

    @Then("I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary")
    public void iVerifyThatSummaryOfAllRowsOfCostColumnIsEqualApproximateCostInOrderSummary() {

        WebElement lastRowElement =getDriver().findElement(By.xpath("(//td[@idx='7'])[25]")); // // last visible element on the page
        getActions().moveToElement(lastRowElement).keyDown(Keys.COMMAND).sendKeys(Keys.PAGE_DOWN).perform(); // scroll down

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[@idx='7'])[29]"))); // last element on the page

        List<WebElement> tableRows = getDriver().findElements(By.xpath("//td[@idx='7']")); // list of all elements
        double sumRowAmount = 0;
        for (WebElement row : tableRows) {
            String s = row.getText().substring(1);
            Double number = Double.parseDouble(s);
            System.out.println(number);
            sumRowAmount += number;
        }
        String approxCost = getDriver().findElement(By.xpath("//span[@class='approx-cost']")).getText();
        Double approxCostToDouble = Double.parseDouble(approxCost);
        assertThat(sumRowAmount).isCloseTo(approxCostToDouble,offset(0.2));
    }
}
