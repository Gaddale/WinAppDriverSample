package com.tests;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class NotepadTest {

    public static WindowsDriver driver = null;

    @BeforeMethod
    public void setUp() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
        cap.setCapability("platformName", "Windows");
        cap.setCapability("deviceName", "Windows10");
        try {
            driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @Test(priority = 1)
    public void checkAboutNotepadTest() {
        driver.findElementByName("Help").click();
        driver.findElementByName("About Notepad").click();
        driver.findElementByName("OK").click();
    }

    @Test(priority = 2)
    public void sendTextTest(){
        driver.findElementByName("Text Editor").sendKeys("This is notepad automation");
        driver.findElementByName("Text Editor").clear();
    }

    @Test(priority = 3)
    public void enterTimeDateTest(){
        driver.findElementByName("Edit").click();
        driver.findElementByAccessibilityId("26").click();
        String s = driver.findElementByName("Text Editor").getAttribute("Value.Value");
        System.out.println(s);
    }

}
