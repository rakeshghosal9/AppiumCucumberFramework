package com.pages;

import com.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class GeneralStoreProductDetailsPage extends BasePage {

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart'])[1]")
    private WebElement firstProduct;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart'])[2]")
    private WebElement secondProduct;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.androidsample.generalstore:id/appbar_btn_cart']")
    private WebElement cartButton;

    public GeneralStoreProductDetailsPage() {
    }
    public void addFirstProduct() {
        click(firstProduct,"Selecting first Product");
    }
    public void addSecondProduct() {
        click(secondProduct,"Selecting second Product");
    }
    public void clickCartButton()
    {
        click(cartButton);
    }
}
