package com.runners;

import com.utils.DriverManager;
import com.utils.PropertyManager;
import com.utils.ServerManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.ThreadContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

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
        ,tags = "@GENERAL_STORE"

)

public class MyRunnerTest {

    @BeforeClass
    public static void initialize() throws Exception {
       /* GlobalParams params = new GlobalParams();
        params.initializeGlobalParams();*/
        Properties props = new PropertyManager().getProps();
        ThreadContext.put("ROUTINGKEY", "android" + "_"
                + "emulator");

        if(props.getProperty("START_STOP_APPIUM_SERVER_PROGRAMMATICALLY").equalsIgnoreCase("Yes")) {
            new ServerManager().startServer();
        }
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
