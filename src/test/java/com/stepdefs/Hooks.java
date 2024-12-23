package com.stepdefs;

import com.pages.BasePage;
import com.utils.DriverManager;
import com.utils.PropertyManager;
import com.utils.TestUtils;
import com.utils.VideoManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;

import java.io.IOException;
import java.util.Properties;

public class Hooks {

    TestUtils utils = new TestUtils();
    @Before
    public void initialize(Scenario scenario) throws Exception {
        utils.log().info("-------------------------"+"Starting of scenario : "+scenario.getName()+"------------------------------");
        Properties props = new PropertyManager().getProps();
        BasePage basePage = new BasePage();
        basePage.closeApp();
        basePage.launchApp();
        if(props.getProperty("ENABLE_VIDEO_RECORDING")!=null &&
                props.getProperty("ENABLE_VIDEO_RECORDING").equalsIgnoreCase("Yes")) {
            new VideoManager().startRecording();
        }
    }

    @After
    public void quit(Scenario scenario) throws IOException {
        utils.log().info("-------------------------"+"Ending of scenario : "+scenario.getName()+"------------------------------");
        Properties props = new PropertyManager().getProps();
        if(scenario.isFailed()){
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        if(props.getProperty("ENABLE_VIDEO_RECORDING")!=null &&
                props.getProperty("ENABLE_VIDEO_RECORDING").equalsIgnoreCase("Yes")) {
            new VideoManager().stopRecording(scenario.getName());
        }
    }
}
