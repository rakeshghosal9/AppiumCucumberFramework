package com.utils;

import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class DriverOptionsManager {
    TestUtils utils = new TestUtils();

    public UiAutomator2Options getOptions() throws IOException {
        /*GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();*/

        try{
            utils.log().info("getting options");
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("AppiumAndroidDevice");
            options.setApp(System.getProperty("user.dir")+"\\src\\test\\resources\\apps\\General-Store.apk");
            utils.log().info("Options Returned");
            return options;
        } catch(Exception e){
            e.printStackTrace();
            utils.log().fatal("Failed to load options. ABORT!!" + e.toString());
            throw e;
        }
    }
}
