package com.stepdefs;

import com.pages.SwagLabsLoginPage;
import com.pages.SwagLabsProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;

public class SwagLabsLogin {

    @When("I enter username as {string}")
    public void iEnterUsernameAs(String username) throws InterruptedException {
        new SwagLabsLoginPage().enterUserName(username);
    }

    @When("I enter password as {string}")
    public void iEnterPasswordAs(String password) {
        new SwagLabsLoginPage().enterPassword(password);
    }

    @When("I login")
    public void iLogin() {
        new SwagLabsLoginPage().pressLoginBtn();
    }

    @Then("I should see Products page with title {string}")
    public void iShouldSeeProductsPageWithTitle(String title) throws IOException {
        Assert.assertEquals(new SwagLabsProductsPage().getTitle(), title);
    }

}
