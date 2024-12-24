package com.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DriverManager {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        Properties props = new PropertyManager().getProps();

        if (driver == null) {
            try {
                utils.log().info("initializing Appium driver");
                switch (props.getProperty("PLATFORM_NAME").toUpperCase()) {
                    case "ANDROID":
                        if (props.getProperty("EXECUTION_TYPE") != null &&
                                props.getProperty("EXECUTION_TYPE").equalsIgnoreCase("Cloud")) {
                            driver = new AndroidDriver(new URL(props.getProperty("BS_URL")),
                                    new DriverOptionsManager().getUiAutomatorOptions());
                        } else {
                            driver = new AndroidDriver(new URL(props.getProperty("APPIUM_SERVER_URL")),
                                    new DriverOptionsManager().getUiAutomatorOptions());
                        }
                        break;
                    case "IOS":
                        if (props.getProperty("EXECUTION_TYPE") != null &&
                                props.getProperty("EXECUTION_TYPE").equalsIgnoreCase("Cloud")) {
                            driver = new IOSDriver(new URL(props.getProperty("BS_URL")),
                                    new DriverOptionsManager().getXCUITestOptions());
                        } else {
                            System.out.println("To be implemented");
                        }
                        break;
                }
                if (driver == null) {
                    throw new Exception("driver is null. ABORT!!!");
                }
                utils.log().info("Driver is initialized");
                this.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
                throw e;
            }
        }

    }

}
