package com.runners;

import com.utils.CommonUtilities;
import com.utils.DriverManager;
import com.utils.PropertyManager;
import com.utils.ServerManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.ThreadContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Properties;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber/report.html",
                "json:target/cucumber-report.json",
                "summary"
//                , "me.jvt.cucumber.report.PrettyReports:target/Pixel3/cucumber-html-reports"
        }
        ,features = {"src/test/resources/features"}
        ,glue = {"com.stepdefs"}
        ,snippets = CAMELCASE
        ,dryRun=false
        ,monochrome=true
        ,tags = "@SWAGLAB_LOGIN_SCENARIO"

)

public class MyRunnerTest {

    @BeforeClass
    public static void initialize() throws Exception {
        Properties props = new PropertyManager().getProps();
        String routingKey = null;
        if (props.getProperty("EXECUTION_TYPE").equalsIgnoreCase("Cloud")) {
            if (props.getProperty("PLATFORM_NAME").equalsIgnoreCase("Android")) {
                routingKey = props.getProperty("PLATFORM_NAME") +
                        File.separator + "Cloud" + File.separator + props.getProperty("BS_ANDROID_DEVICE_NAME");
            } else {
                routingKey = props.getProperty("PLATFORM_NAME") +
                        File.separator + "Cloud" + File.separator + props.getProperty("BS_IOS_DEVICE_NAME");
            }
        } else {
            routingKey = props.getProperty("PLATFORM_NAME") +
                    File.separator + "Local" + File.separator + props.getProperty("ANDROID_DEVICE_NAME");
        }
        ThreadContext.put("ROUTINGKEY", routingKey);
        if (props.getProperty("START_STOP_APPIUM_SERVER_PROGRAMMATICALLY").equalsIgnoreCase("Yes")
                && !props.getProperty("EXECUTION_TYPE").equalsIgnoreCase("Cloud")) {
            new ServerManager().startServer();
        }
        CommonUtilities obj = new CommonUtilities();
        obj.createLogDirectory(routingKey);
        new DriverManager().initializeDriver();
    }

    @AfterClass
    public static void quit(){
        DriverManager driverManager = new DriverManager();
        if(driverManager.getDriver() != null){
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }
        ServerManager serverManager = new ServerManager();
        if(serverManager.getServer() != null){
            System.out.println("Stopping Server");
            serverManager.getServer().stop();
        }
    }
}
