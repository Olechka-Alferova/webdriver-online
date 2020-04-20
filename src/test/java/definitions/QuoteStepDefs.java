package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.QuoteForm;
import pages.QuoteResult;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteStepDefs {
    QuoteForm formPage = new QuoteForm();
    QuoteResult resultPage = new QuoteResult();

    @Given("I go to {string} page oop")
    public void iGoToPageOop(String page) {
        formPage.open();
    }

    @When("I fill out required fields oop")
    public void iFillOutRequiredFieldsOop() {
        formPage.fillUsername("oalferova");
        formPage.fillEmail("test@gmail.com");
        formPage.fillPassword("password1");
        formPage.confirmPassword("password1");
        formPage.fillName("Olga", "Alferova");
        formPage.clickAgreedToPolicy();
    }

    @And("I submit the form oop")
    public void iSubmitTheFormOop() {
        formPage.clickSubmit();
    }

    @Then("I verify required fields oop")
    public void iVerifyRequiredFieldsOop() {
        resultPage.getResult();
        verifyRequiredFields ("oalferova","test@gmail.com",
                "[entered]", "Olga Alferova");
    }

    public void verifyRequiredFields(String username, String email, String password, String name) {
        assertThat(resultPage.getResult()).contains(username);
        assertThat(resultPage.getResult()).contains(email);
        assertThat(resultPage.getResult()).contains(name);
        assertThat(resultPage.getPassword()).isEqualTo(password);
        assertThat(resultPage.getPrivacyStatus()).isEqualTo("true");
    }

    @When("I fill out {string} field with {string}")
    public void iFillOutFieldWith(String fieldName, String fieldValue) {
        switch (fieldName) {
            case "username":
                formPage.fillUsername(fieldValue);
                break;
            case "email":
                formPage.fillEmail(fieldValue);
                break;
            case "password":
                formPage.fillPassword(fieldValue);
                break;
            case "confirmPassword":
                formPage.confirmPassword(fieldValue);
                break;
            default:
                throw new RuntimeException("Unknown field: " + fieldName);
        }
    }

    @Then("I see {string} error message {string}")
    public void iSeeErrorMessage(String fieldName, String errorMessage) {
        String acturalError = formPage.getErrorMessage(fieldName);
        assertThat(acturalError).isEqualTo(errorMessage);
    }

    @Then("I don't see {string} error message")
    public void iDonTSeeErrorMessage(String fieldName) {
        assertThat(formPage.isErrorMessageDisplayed(fieldName)).isFalse();
    }

    @When("I clear {string} field")
    public void iClearField(String fieldName) {
        switch (fieldName) {
            case "username":
                formPage.clearUsername();
                break;
            case "email":
                formPage.clearEmail();
                break;
            case "password":
                formPage.clearPassword();
                break;
            case "confirmPassword":
                formPage.clearConfirmPassword();
                break;
            case "name":
                formPage.clearName();
                break;
            default:
                throw new RuntimeException("Unknown field: " + fieldName);
        }
    }

    @And("I uncheck {string} field")
    public void iUncheckField(String arg0) {
        if (formPage.checkAgreedToPolicyStatus().equals("on")) {
            formPage.clickAgreedToPolicy();
        }
    }

    @When("I fill out name field with first name {string} and last name {string}")
    public void iFillOutNameFieldWithFirstNameAndLastName(String firsName, String lastName) {
        formPage.fillName(firsName,lastName);
    }

    @Then("I verify {string} field value {string}")
    public void iVerifyFieldValue(String fieldName, String fieldValue) {
        switch (fieldName) {
            case "name":
                String actualValue = formPage.getName();
                assertThat(actualValue).isEqualTo(fieldValue);
                break;
            default:
                throw new RuntimeException("Unknown field: " + fieldName);
        }
    }

    @When("I fill out name field with first name {string}, middle name {string}, last name {string}")
    public void iFillOutNameFieldWithFirstNameMiddleNameLastName(String first, String middle, String last) {
        formPage.fillName(first,middle,last);
    }
}
