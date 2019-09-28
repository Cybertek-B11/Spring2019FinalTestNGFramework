package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String browser = ConfigurationReader.getProperty("browser");
    private static final int IMPLICIT_WAIT = Integer.valueOf(ConfigurationReader.getProperty("LONG_WAIT"));
    private static final Logger logger = LogManager.getLogger(Driver.class);

    private Driver(){

    }

    public synchronized static void intiDriver(String... browsers) {
        if(browsers.length>0||browsers[0]!=null){
            browser = browsers[0];
        }
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "chromeHeadless":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "firefoxHeadless":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                break;
            case "ie":
                if (System.getProperty("os.name").toLowerCase().contains("mac"))
                    throw new WebDriverException("You are operating Mac OS which doesn't support Internet Explorer");
                WebDriverManager.iedriver().setup();
                driver.set(new InternetExplorerDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            case "safari":
                if (System.getProperty("os.name").toLowerCase().contains("windows"))
                    throw new WebDriverException("You are operating Windows OS which doesn't support Safari");
                WebDriverManager.getInstance(SafariDriver.class).setup();
                driver.set(new SafariDriver());
                break;
            default:
                logger.error("Illegal browser type :: "+browser);
                throw new RuntimeException("Illegal browser type!");
        }
        driver.get().manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }


    //kill all drivers
    public static void closeDriver() {
        driver.get().quit();
    }

    public static void shutDownDrivers() {
        driver.remove();
    }
}
