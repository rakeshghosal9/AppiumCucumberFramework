package com.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CommonUtilities {

    public void createLogDirectory() throws IOException {
        Properties props = new PropertyManager().getProps();
        String logDir = "logs/" + props.getProperty("PLATFORM_NAME") + File.separator + props.getProperty("ANDROID_DEVICE_NAME");
        File directory = new File(logDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
