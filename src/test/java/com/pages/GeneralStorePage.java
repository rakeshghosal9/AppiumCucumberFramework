package com.pages;

import com.utils.DriverManager;
import com.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class GeneralStorePage extends BasePage {
    TestUtils utils = new TestUtils();
    private AppiumDriver driver;
    private AndroidDriver androidDriver;

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
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        this.androidDriver = (AndroidDriver) this.driver;
    }

    public void setNameField(String name) {
		sendKeys(nameField,name);
        androidDriver.hideKeyboard();

    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            maleRadioButton.click();
        } else {
            femaleRadioButton.click();
        }
    }

    public void selectCountry(String countryName) {
        countryNameDropdown.click();
        performScroll(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
    }

    public void clickOnSubmitButton() {
        btnLetsShop.click();
    }

}
