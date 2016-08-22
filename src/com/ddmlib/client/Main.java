package com.ddmlib.client;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.TimeoutException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        IDevice device;
        AndroidDebugBridge.init(false);
        AndroidDebugBridge bridge = AndroidDebugBridge.createBridge(
                "/Users/qfpay/Downloads/adt-bundle-mac-x86_64-20131030/sdk/platform-tools/adb", false);
        waitForDevice(bridge);
        IDevice devices[] = bridge.getDevices();
        device = devices[0];

        takeScreenshot(device);
    }

    private static void waitForDevice(AndroidDebugBridge bridge) {
        int count = 0;
        while (!bridge.hasInitialDeviceList()) {
            try {
                Thread.sleep(100);
                count++;
            } catch (InterruptedException ignored) {
            }
            if (count > 300) {
                System.err.print("Time out");
                break;
            }
        }
    }

    /**
     * 截屏
     * @param device
     */
    private static void takeScreenshot(IDevice device) {
        try {
            RawImage rawScreen = device.getScreenshot();
            if (rawScreen != null) {
                int width = rawScreen.width;
                int height = rawScreen.height;
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                int index = 0;
                int indexInc = rawScreen.bpp >> 3;
                for (int y = 0; y < rawScreen.height; y++) {
                    for (int x = 0; x < rawScreen.width; x++, index += indexInc) {
                        int value = rawScreen.getARGB(index);
                        image.setRGB(x, y, value);
                    }
                }
                File file = new File("/Users/qfpay/Downloads/temp");
                if (!file.exists()) {
                    file.mkdirs();
                }
                ImageIO.write(image, "PNG", new File(file.getAbsoluteFile() + "/test.png"));
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (AdbCommandRejectedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}