package com.stepdefs;

import com.pages.CartPage;
import com.pages.GeneralStoreHomePage;
import com.pages.GeneralStoreProductDetailsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GeneralStoreStepDef {
    @When("I set the name field as {string}")
    public void iSetTheNameFieldAs(String name) {
        new GeneralStoreHomePage().setNameField(name);
    }

    @When("I select the gender as {string}")
    public void iSelectTheGenderAs(String gender) {
        new GeneralStoreHomePage().selectGender(gender);
    }

    @When("I select the country as {string} from dropdown")
    public void iSelectTheCountryAsIndiaFromDropdown(String country) {
        new GeneralStoreHomePage().selectCountry(country);
    }

    @Then("click on the submit button")
    public void clickOnTheSubmitButton() {
        new GeneralStoreHomePage().clickOnSubmitButton();
    }

    @Then("add the first two products to cart")
    public void addFirstTwoProducts() {
        new GeneralStoreProductDetailsPage().addFirstProduct();
        new GeneralStoreProductDetailsPage().addSecondProduct();
    }

    @Then("click on the cart symbol")
    public void clickOnCartButton() {
        new GeneralStoreProductDetailsPage().clickCartButton();
    }

    @Then("validate the total value of the cart")
    public void validateTotalValue() {
        new CartPage().validateTotalCartValue();
    }

}
