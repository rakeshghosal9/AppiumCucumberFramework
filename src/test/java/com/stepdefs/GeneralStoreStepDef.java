package com.stepdefs;

import com.pages.GeneralStorePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GeneralStoreStepDef {
    @When("I set the name field as {string}")
    public void iSetTheNameFieldAs(String name) {
        new GeneralStorePage().setNameField(name);
    }
    @When("I select the gender as {string}")
    public void iSelectTheGenderAs(String gender) {
        new GeneralStorePage().selectGender(gender);
    }
    @When("I select the country as {string} from dropdown")
    public void iSelectTheCountryAsIndiaFromDropdown(String country) {
        new GeneralStorePage().selectCountry(country);
    }
    @Then("click on the submit button")
    public void clickOnTheSubmitButton() {
        new GeneralStorePage().clickOnSubmitButton();
    }
}
