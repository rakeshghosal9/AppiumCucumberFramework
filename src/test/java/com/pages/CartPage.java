package com.pages;

import com.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice'])[1]")
    private WebElement productPrice1;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice'])[2]")
    private WebElement productPrice2;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/totalAmountLbl']")
    private WebElement totalAmount;

    public CartPage() {
    }
    public void validateTotalCartValue()
    {
        String value1 = getText(productPrice1,"Product Price 1 : ");
        String value2 = getText(productPrice2,"Product Price 2 : ");
        String totalValue = getText(totalAmount,"Total Amount : ");
    }
}
