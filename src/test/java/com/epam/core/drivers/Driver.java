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

    public static WebDriver getWebDriverInstance(String name, DriverTypes type) {
        WebDriver driver;
        if (!instances.containsKey(name)) {
            switch (type) {
                case FIREFOX: {
                    System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                }
                case IE: {
                    System.setProperty("webdriver.ie.driver", "src/test/resources/driverbinaries/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;
                }
                case CHROME: {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/driverbinaries/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                }
                default:
                    driver = new FirefoxDriver();
                    break;
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            instances.put(name, driver);
        } else {
            driver = instances.get(name);
        }
        return driver;
    }

    public static WebDriver getWebDriverInstance(String name) {
        return getWebDriverInstance(name, defaultDriverType);
    }

    public static WebDriver getWebDriverInstance() {
        return getWebDriverInstance(DEFAULT_WEB_DRIVER, defaultDriverType);
    }

    public static void closeDriver() {
        getWebDriverInstance().quit();
    }
}