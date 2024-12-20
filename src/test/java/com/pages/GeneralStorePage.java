package com.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GeneralStorePage extends BasePage {

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleRadioButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleRadioButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement btnLetsShop;
    @AndroidFindBy(id = "android:id/text1")
    private WebElement countryNameDropdown;

    public GeneralStorePage() {
    }

    public void setNameField(String name) {
        sendKeys(nameField, name);
        androidHideKeyboard();
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            click(maleRadioButton,"Selecting Gender : "+gender);
        } else {
            click(femaleRadioButton, "Selecting Gender : "+gender);
        }
    }

    public void selectCountry(String countryName) {
        click(countryNameDropdown);
        performScroll(countryName);
        By element = By.xpath("//android.widget.TextView[@text='" + countryName + "']");
        click(element, "Country Selected : " + countryName);
    }

    public void clickOnSubmitButton() {
        click(btnLetsShop,"Clicking Submit Button");
    }

}
