package com.pages;

import com.utils.DriverManager;
import com.utils.PropertyManager;
import com.utils.TestUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class BasePage {
    private AppiumDriver driver;
    TestUtils utils = new TestUtils();


    public BasePage(){
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForVisibility(By e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(e));
    }

    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void click(WebElement e, String msg) {
        waitForVisibility(e);
        utils.log().info(msg);
        e.click();
    }

    public void click(By e, String msg) {
        waitForVisibility(e);
        utils.log().info(msg);
        driver.findElement(e).click();
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
        utils.log().info("Entered value : "+txt);
    }

    public void sendKeys(WebElement e, String txt, String msg) {
        waitForVisibility(e);
        utils.log().info(msg);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getAttribute(By e, String attribute) {
        waitForVisibility(e);
        return driver.findElement(e).getAttribute(attribute);
    }

    public String getText(WebElement e, String msg) throws IOException {
        String txt;
        Properties props = new PropertyManager().getProps();
        switch(props.getProperty("PLATFORM_NAME").toUpperCase()){
            case "ANDROID":
                txt = getAttribute(e, "text");
                break;
            case "IOS":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + props.getProperty("PLATFORM_NAME"));
        }
        utils.log().info(msg + txt);
        return txt;
    }

    public String getText(By e, String msg) throws IOException {
        String txt;
        Properties props = new PropertyManager().getProps();
        switch(props.getProperty("PLATFORM_NAME").toUpperCase()){
            case "ANDROID":
                txt = getAttribute(e, "text");
                break;
            case "IOS":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + props.getProperty("PLATFORM_NAME"));
        }
        utils.log().info(msg + txt);
        return txt;
    }

    public void closeApp() throws IOException {
        Properties props = new PropertyManager().getProps();
        switch (props.getProperty("PLATFORM_NAME").toUpperCase()) {
            case "ANDROID":
                ((InteractsWithApps) driver).terminateApp(driver.getCapabilities().
                        getCapability("appPackage").toString());
                break;
            case "IOS":
                try {
                    ((InteractsWithApps) driver).terminateApp(driver.getCapabilities().
                            getCapability("bundleId").toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
        }
    }

    public void launchApp() throws IOException {
        Properties props = new PropertyManager().getProps();
        switch(props.getProperty("PLATFORM_NAME").toUpperCase()){
            case "ANDROID":
                ((InteractsWithApps) driver).activateApp(driver.getCapabilities().
                        getCapability("appPackage").toString());
                break;
            case "IOS":
                ((InteractsWithApps) driver).activateApp(driver.getCapabilities().
                        getCapability("bundleId").toString());
        }
    }

    public WebElement andScrollToElementUsingUiScrollable(String childLocAttr, String childLocValue) {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector()."+ childLocAttr +"(" + childLocValue + "));"));
    }

    public WebElement iOSScrollToElementUsingMobileScroll(WebElement e) {
        RemoteWebElement element = ((RemoteWebElement) e);
        String elementID = element.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "sdfnjksdnfkld");
        driver.executeScript("mobile:scroll", scrollObject);
        return e;
    }


    public boolean performScroll(String text) {
        try {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred while performing scroll : " + e);
            return false;
        }
    }

    public boolean androidHideKeyboard() {
        try {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.hideKeyboard();
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
