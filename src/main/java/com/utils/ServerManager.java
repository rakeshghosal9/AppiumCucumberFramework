package com.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ServerManager {
    private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriverLocalService getServer(){
        return server.get();
    }

    public void startServer() throws IOException {
        utils.log().info("starting appium server");
        AppiumDriverLocalService server = windowsGetAppiumService();
        server.start();
        if(server == null || !server.isRunning()){
            utils.log().fatal("Appium server not started. ABORT!!!");
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
        }
        server.clearOutPutStreams(); // -> Comment this if you want to see server logs in the console
        this.server.set(server);
        utils.log().info("Appium server started");
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    public AppiumDriverLocalService windowsGetAppiumService() throws IOException {
        Properties props = new PropertyManager().getProps();
        return new AppiumServiceBuilder()
                .withAppiumJS(new File(props.getProperty("MAIN_JS_FILE_LOCATION")))
                .withIPAddress(props.getProperty("APPIUM_IP_ADDRESS"))
                .usingPort(Integer.parseInt(props.getProperty("APPIUM_PORT")))
                .build();

        //GlobalParams params = new GlobalParams();
        /*return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE));*/
                        //.withLogFile(new File(System.getProperty("user.dir")+File.separator+"Android"+"_"+"Appium_1"+ File.separator + "Server.log")));

                /*.withLogFile(new File(params.getPlatformName() + "_"
                        + params.getDeviceName() + File.separator + "Server.log")));*/
    }

/*    public AppiumDriverLocalService MacGetAppiumService() {
        GlobalParams params = new GlobalParams();
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "enter_your_path_here" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "enter_your_android_home_path");
        environment.put("JAVA_HOME", "enter_your_java_home_path");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogFile(new File(params.getPlatformName() + "_"
                        + params.getDeviceName() + File.separator + "Server.log")));
    }*/
}
