package com.utils;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class DriverOptionsManager {
    TestUtils utils = new TestUtils();

    public UiAutomator2Options getUiAutomatorOptions() throws IOException {
        Properties props = new PropertyManager().getProps();
        try {
            utils.log().info("getting android options");
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("android");
            if (props.getProperty("EXECUTION_TYPE").equalsIgnoreCase("Cloud")) {
                options.setPlatformVersion(props.getProperty("BS_ANDROID_PLATFORM_VERSION"));
                options.setDeviceName(props.getProperty("BS_ANDROID_DEVICE_NAME"));
                options.setApp(props.getProperty("BS_ANDROID_APP_NAME"));
                options.setAppPackage(props.getProperty("ANDROID_APP_PACKAGE"));
                options.setAppActivity(props.getProperty("ANDROID_APP_ACTIVITY"));
                options.setCapability("bstack:options", getBStackOptions(props));
            } else {
                options.autoGrantPermissions();
                options.setAppWaitDuration(Duration.ofSeconds(20));
                    options.setAutomationName(props.getProperty("ANDROID_AUTOMATION_NAME"));
                    options.setDeviceName(props.getProperty("ANDROID_DEVICE_NAME"));
                    options.setAppPackage(props.getProperty("ANDROID_APP_PACKAGE"));
                    options.setAppActivity(props.getProperty("ANDROID_APP_ACTIVITY"));
                    options.setApp(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
                            File.separator + "resources" + File.separator + props.getProperty("ANDROID_APP_LOCATION"));
            }
            utils.log().info("Options Returned");
            return options;
        } catch (Exception e) {
            e.printStackTrace();
            utils.log().fatal("Failed to load options. ABORT!!" + e);
            throw e;
        }
    }

    public XCUITestOptions getXCUITestOptions() throws IOException {
        Properties props = new PropertyManager().getProps();
        try {
            XCUITestOptions iosOptions = new XCUITestOptions();
            iosOptions.setPlatformName("ios");
            if (props.getProperty("EXECUTION_TYPE").equalsIgnoreCase("Cloud")) {
                iosOptions.setAutomationName("XCUITest");
                iosOptions.setBundleId(props.getProperty("BS_IOS_BUNDLE_ID"));
                iosOptions.setPlatformVersion(props.getProperty("BS_IOS_PLATFORM_VERSION"));
                iosOptions.setDeviceName(props.getProperty("BS_IOS_DEVICE_NAME"));
                iosOptions.setApp(props.getProperty("BS_IOS_APP_NAME"));
                iosOptions.setCapability("bstack:options", getBStackOptions(props));
            }
            utils.log().info("Options Returned");
            return iosOptions;
        } catch (Exception e) {
            e.printStackTrace();
            utils.log().fatal("Failed to load options. ABORT!!" + e);
            throw e;
        }
    }

    public HashMap<String, Object> getBStackOptions(Properties props) {
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
        bstackOptions.put("userName", props.getProperty("BS_USERNAME"));
        bstackOptions.put("accessKey", props.getProperty("BS_ACCESS_KEY"));
        bstackOptions.put("appiumVersion", props.getProperty("BS_APPIUM_VERSION"));
        return bstackOptions;
    }
}
