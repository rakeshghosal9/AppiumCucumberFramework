package com.utils;

import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class VideoManager {
    TestUtils utils = new TestUtils();

    public static String videoDateTime = null;
    public void startRecording() {
        ((CanRecordScreen) new DriverManager().getDriver()).startRecordingScreen();
        if(videoDateTime==null) {
            videoDateTime = CommonUtilities.dateTime();
        }
    }
    public void stopRecording(String scenarioName) throws IOException {
        Properties props = new PropertyManager().getProps();
        String media = ((CanRecordScreen) new DriverManager().getDriver()).stopRecordingScreen();
        String dirPath = System.getProperty("user.dir")+props.getProperty("VIDEO_RECORDING_STORAGE_LOCATION")
                +File.separator+props.getProperty("PLATFORM_NAME")+File.separator+props.get("ANDROID_DEVICE_NAME")
                +File.separator+videoDateTime;
        File videoDir = new File(dirPath);
        synchronized (videoDir) {
            if (!videoDir.exists()) {
                videoDir.mkdirs();
            }
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(videoDir + File.separator + scenarioName + ".mp4");
            stream.write(Base64.decodeBase64(media));
            stream.close();
            utils.log().info("video path: " + videoDir + File.separator + scenarioName + ".mp4");
        } catch (Exception e) {
            utils.log().error("error during video capture" + e.toString());
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
