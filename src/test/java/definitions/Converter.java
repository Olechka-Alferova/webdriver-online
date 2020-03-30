package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.*;

public class Converter {
    @When("I click on {string}")
    public void iClickOn(String convertedType) {
        getDriver().findElement(By.xpath("//li[@id='menuon']/..//a[contains(@href,'" + convertedType + "')]")).click();
    }

    @And("I set {string} to {string}")
    public void iSetTo(String calFrom, String calTo) {
        WebElement selectFrom = getDriver().findElement(By.xpath("//select[@id='calFrom']"));
        WebElement selectTo = getDriver().findElement(By.xpath("//select[@id='calTo']"));
        new Select(selectFrom).selectByVisibleText(calFrom);
        new Select(selectTo).selectByVisibleText(calTo);
    }

    @Then("I enter into From field {string} and verify {string} result")
    public void iEnterIntoFromFieldAndVerifyResult(String valueFrom, String valueTo) {
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys(valueFrom);
        String resultValueTo = getDriver().findElement(By.xpath("//input[@name='toVal']")).getAttribute("value");
        assertThat(resultValueTo).contains(valueTo);
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String autoLoanPage) {
        getDriver().findElement(By.xpath("//a[contains(text(),'"+ autoLoanPage +"')]")).click();
    }

    @And("I clear all calculator fields")
    public void iClearAllCalculatorFields() {
        getDriver().findElement(By.id("cloanamount")).clear();
        getDriver().findElement(By.id("cloanterm")).clear();
        getDriver().findElement(By.id("cinterestrate")).clear();
        getDriver().findElement(By.id("cdownpayment")).clear();
        getDriver().findElement(By.id("ctradeinvalue")).clear();
        getDriver().findElement(By.id("csaletax")).clear();
        getDriver().findElement(By.id("ctitlereg")).clear();
    }

    @And("I calculate")
    public void iCalculate() {
        getDriver().findElement(By.xpath("//input[@value='Calculate']")).click();
    }

    @Then("I verify {string} calculator error")
    public void iVerifyCalculatorError(String errorMessage) {
        String errorMessageResults = getDriver().findElement(By.xpath("//a[@name='autoloanresult']/..")).getText();
        assertThat(errorMessageResults).contains(errorMessage);
    }

    @And("I enter {string} price, {string} months, {string} interest, {string} downpayment, " +
            "{string} trade-in, {string} state, {string} percent tax, {string} fees")
    public void iEnterPriceMonthsInterestDownpaymentTradeInStatePercentTaxFees(
            String price, String months, String interest, String downpayment, String radeIn,
            String state, String percentTax, String fees) {
        getDriver().findElement(By.id("cloanamount")).sendKeys(price);
        getDriver().findElement(By.id("cloanterm")).sendKeys(months);
        getDriver().findElement(By.id("cinterestrate")).sendKeys(interest);
        getDriver().findElement(By.id("cdownpayment")).sendKeys(downpayment);
        getDriver().findElement(By.id("ctradeinvalue")).sendKeys(radeIn);
        WebElement stateSelect = getDriver().findElement(By.xpath("//select[@name='cstate']"));
        new Select(stateSelect).selectByVisibleText(state);
        getDriver().findElement(By.id("csaletax")).sendKeys(percentTax);
        getDriver().findElement(By.id("ctitlereg")).sendKeys(fees);
    }

    @Then("I verify monthly pay is {string}")
    public void iVerifyMonthlyPayIs(String monthlyPay) {
        String result = getDriver().findElement(By.xpath("//h2[@class='h2result']")).getText();
        assertThat(result).contains(monthlyPay);
    }
}
