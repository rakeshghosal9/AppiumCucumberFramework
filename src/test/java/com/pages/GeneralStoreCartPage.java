package com.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class GeneralStoreCartPage extends BasePage {

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice'])[1]")
    private WebElement productPrice1;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice'])[2]")
    private WebElement productPrice2;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/totalAmountLbl']")
    private WebElement totalAmount;

    public GeneralStoreCartPage() {
    }
    public void validateTotalCartValue() throws IOException {
        String value1 = getText(productPrice1,"Product Price 1 : ");
        String value2 = getText(productPrice2,"Product Price 2 : ");
        String totalValue = getText(totalAmount,"Total Amount : ");
    }
}
