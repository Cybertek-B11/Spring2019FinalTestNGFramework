package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VYTrackUtils {
    //we don't want to access these variables outside
    private static String usernamelocator = "prependedInput";
    private static String passwordLocator = "prependedInput2";
    private static String loaderMaskLocator = "div[class='loader-mask shown']";
    private static String pageSubTitleLocator = "h1[class='oro-subtitle']";


    /**
     * Login into vytrack application
     * @param driver
     * @param username
     * @param password
     */
    public static void login(WebDriver driver, String username, String password){
        driver.findElement(By.id(usernamelocator)).sendKeys(username);
        //Keys.ENTER means click enter after entering password
        //in this way, we don't need to click login button
        driver.findElement(By.id(passwordLocator)).sendKeys(password, Keys.ENTER);
        BrowserUtils.waitPlease(3);
    }

    /**
     * This method will navigate user to the specific module in vytrack application.
     * For example: if tab is equals to Activities, and module equals to Calls,
     * Then method will navigate user to this page: http://qa2.vytrack.com/call/
     *
     * @param driver
     * @param tab
     * @param module
     */
    public static void navigateToModule(WebDriver driver, String tab, String module){
        String tabLocator = "//span[contains(text(),'"+tab+"') and contains(@class, 'title title-level-1')]";
        String moduleLocator = "//span[contains(text(),'"+module+"') and contains(@class, 'title title-level-2')]";
        BrowserUtils.clickWithWait(driver, By.xpath(tabLocator), 5);
        driver.findElement(By.xpath(moduleLocator)).click();
    }

    /**
     * This method will navigate user to the specific module in vytrack application.
     * For example: if tab is equals to Activities, and module equals to Calls,
     * Then method will navigate user to this page: http://qa2.vytrack.com/call/
     *
     *
     * @param tab
     * @param module
     */
    public static void navigateToModule(String tab, String module){
        String tabLocator = "//span[normalize-space()='"+tab+"' and contains(@class, 'title title-level-1')]";
        String moduleLocator = "//span[normalize-space()='"+module+"' and contains(@class, 'title title-level-2')]";
       try{
           BrowserUtils.waitForClickablility(By.xpath(tabLocator), 5);
           WebElement tabElement = Driver.getDriver().findElement(By.xpath(tabLocator));
           new Actions(Driver.getDriver()).moveToElement(tabElement).pause(200).doubleClick(tabElement).build().perform();
       }catch (Exception e){
           BrowserUtils.clickWithWait(By.xpath(tabLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
       }
        BrowserUtils.waitForPresenceOfElement(By.xpath(moduleLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
        BrowserUtils.waitForVisibility(By.xpath(moduleLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
        Driver.getDriver().findElement(By.xpath(moduleLocator)).click();
    }

    /**
     * Waits until loader screen present. If loader screen will not pop up at all,
     * NoSuchElementException will be handled  bu try/catch block
     * Thus, we can continue in any case.
     * @param driver
     */
    public static void waitUntilLoaderScreenDisappear(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(loaderMaskLocator))));
        }catch (Exception e){
            System.out.println(e+" :: Loader mask doesn't present.");
        }
    }

    /**
     * Waits until loader screen present. If loader screen will not pop up at all,
     * NoSuchElementException will be handled  bu try/catch block
     * Thus, we can continue in any case.
     *
     */
    public static void waitUntilLoaderScreenDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
            wait.until(ExpectedConditions.invisibilityOf(Driver.getDriver().findElement(By.cssSelector(loaderMaskLocator))));
        }catch (Exception e){
            System.out.println("Loader mask doesn't present.");
        }
    }

    /**
     *
     * @return page name, for example: Dashboard
     */
    public static String getPageSubTitle(){
        //ant time we are verifying page name, or page subtitle, loader mask appears
        waitUntilLoaderScreenDisappear(Driver.getDriver());
        return Driver.getDriver().findElement(By.cssSelector(pageSubTitleLocator)).getText();
    }
}
