package com.utils;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverOptionsManager {
    TestUtils utils = new TestUtils();

    public UiAutomator2Options getAndroidOptions() throws IOException {
        Properties props = new PropertyManager().getProps();
        try {
            utils.log().info("getting options");
            UiAutomator2Options options = new UiAutomator2Options();
            options.autoGrantPermissions();
            options.setAppWaitDuration(Duration.ofSeconds(20));
            options.setPlatformName("Android");
            if (props.getProperty("ANDROID_AUTOMATION_NAME") != null && !props.getProperty("ANDROID_AUTOMATION_NAME").isEmpty()) {
                options.setAutomationName(props.getProperty("ANDROID_AUTOMATION_NAME"));
            }
            if (props.getProperty("ANDROID_DEVICE_NAME") != null && !props.getProperty("ANDROID_DEVICE_NAME").isEmpty()) {
                options.setDeviceName(props.getProperty("ANDROID_DEVICE_NAME"));
            }
            if (props.getProperty("ANDROID_APP_PACKAGE") != null && !props.getProperty("ANDROID_APP_PACKAGE").isEmpty()) {
                options.setAppPackage(props.getProperty("ANDROID_APP_PACKAGE"));
            }
            if (props.getProperty("ANDROID_APP_ACTIVITY") != null && !props.getProperty("ANDROID_APP_ACTIVITY").isEmpty()) {
                options.setAppActivity(props.getProperty("ANDROID_APP_ACTIVITY"));
            }
            if (props.getProperty("ANDROID_APP_LOCATION") != null && !props.getProperty("ANDROID_APP_LOCATION").isEmpty()) {
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
}
