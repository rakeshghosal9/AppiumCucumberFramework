package com.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SwagLabsLoginPage extends BasePage {

    @AndroidFindBy (accessibility = "test-Username")
    @iOSXCUITFindBy(id = "test-Username")
    private WebElement usernameTxtFld;

    @AndroidFindBy (accessibility = "test-Password")
    @iOSXCUITFindBy (id = "test-Password")
    private WebElement passwordTxtFld;

    @AndroidFindBy (accessibility = "test-LOGIN")
    @iOSXCUITFindBy (id = "test-LOGIN")
    private WebElement loginBtn;

    public SwagLabsLoginPage() {
    }

    public SwagLabsLoginPage enterUserName(String username) throws InterruptedException {
        clear(usernameTxtFld);
        sendKeys(usernameTxtFld, username, "login with " + username);
        return this;
    }

    public SwagLabsLoginPage enterPassword(String password) {
        clear(passwordTxtFld);
        sendKeys(passwordTxtFld, password, "password is " + password);
        return this;
    }

    public void pressLoginBtn() {
        click(loginBtn, "press login button");
    }
}
