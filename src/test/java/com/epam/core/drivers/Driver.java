package com.epam.core.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static final String DEFAULT_WEB_DRIVER = "DEFAULT_WEB_DRIVER";

    private static DriverTypes defaultDriverType = DriverTypes.CHROME;

    private static HashMap<String, WebDriver> instances;

    static {
        instances = new HashMap<String, WebDriver>();
    }

    private static WebDriver getWebDriverInstance(String name, DriverTypes type) {
        WebDriver driver;
        if (!instances.containsKey(name)) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/driverbinaries/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
            instances.put(name, driver);
        } else {
            driver = instances.get(name);
        }
        return driver;
    }

    public static WebDriver getWebDriverInstance() {
        return getWebDriverInstance(DEFAULT_WEB_DRIVER, defaultDriverType);
    }

    public static void goToURL(String url) {
        getWebDriverInstance().get(url);
        getWebDriverInstance().manage().window().maximize();
    }

    public static void closeDriver() {
        getWebDriverInstance().quit();
    }
}