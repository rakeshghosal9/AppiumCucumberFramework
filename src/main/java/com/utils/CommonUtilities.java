package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CommonUtilities {

    public void createLogDirectory(String directoryPath) throws IOException {
        Properties props = new PropertyManager().getProps();
        String logDir = "logs/" + directoryPath;
        File directory = new File(logDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    public static String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
