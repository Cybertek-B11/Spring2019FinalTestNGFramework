package com.vytrack.base;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public abstract class BasePage {
    //we don't want to access these variables outside
    private static final Logger logger = LogManager.getLogger();

    @FindBy(css = "#user-menu>a")
    public WebElement userFullName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(css = "div[class='loader-mask shown']")
    @CacheLookup
    protected WebElement loaderMask;

    @FindBy(css = "h1[class='oro-subtitle']")
    protected WebElement pageSubTitle;

    @FindBy(css = "#user-menu > a")
    protected WebElement userName;


    @FindBy(linkText = "My User")
    public WebElement myUser;

    protected WebElement usersFullName;

    @FindBy(linkText = "Logout")
    protected WebElement logout;



    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    /**
     * @return page name, for example: Dashboard
     */
    public String getPageSubTitle() {
        //ant time we are verifying page name, or page subtitle, loader mask appears
        waitUntilLoaderScreenDisappear();
        BrowserUtils.waitForStaleElement(pageSubTitle);
        return pageSubTitle.getText();
    }


    /**
     * Waits until loader screen present. If loader screen will not pop up at all,
     * NoSuchElementException will be handled  bu try/catch block
     * Thus, we can continue in any case.
     */
    public void waitUntilLoaderScreenDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        } catch (Exception e) {
            logger.error("Loader mask doesn't present.");
            System.out.println("Loader mask doesn't present.");
        }
    }

    /**
     * This method will navigate user to the specific module in vytrack application.
     * For example: if tab is equals to Activities, and module equals to Calls,
     * Then method will navigate user to this page: http://qa2.vytrack.com/call/
     *
     * @param tab
     * @param module
     */
    public void navigateToModule(String tab, String module) {
        String tabLocator = "//span[normalize-space()='" + tab + "' and contains(@class, 'title title-level-1')]";
        String moduleLocator = "//span[normalize-space()='" + module + "' and contains(@class, 'title title-level-2')]";
        try {
            BrowserUtils.waitForClickablility(By.xpath(tabLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
            WebElement tabElement = Driver.getDriver().findElement(By.xpath(tabLocator));
            new Actions(Driver.getDriver()).moveToElement(tabElement).pause(200).doubleClick(tabElement).build().perform();
        } catch (Exception e) {
            logger.error("Failed to click on :: "+tab);
            logger.error(e);
            BrowserUtils.clickWithWait(By.xpath(tabLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
        }
        try {
            BrowserUtils.waitForPresenceOfElement(By.xpath(moduleLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
            BrowserUtils.waitForVisibility(By.xpath(moduleLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
            BrowserUtils.scrollToElement(Driver.getDriver().findElement(By.xpath(moduleLocator)));
            Driver.getDriver().findElement(By.xpath(moduleLocator)).click();
        } catch (Exception e) {
            logger.error("Failed to click on :: "+module);
            logger.error(e);
            BrowserUtils.waitForStaleElement(Driver.getDriver().findElement(By.xpath(moduleLocator)));
            BrowserUtils.clickWithTimeOut(Driver.getDriver().findElement(By.xpath(moduleLocator)),  Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
        }
    }


    public void logOut(){
        BrowserUtils.waitFor(2);
        BrowserUtils.clickWithJS(userFullName);
        BrowserUtils.clickWithJS(logOutLink);
    }

    public String getUserName(){
        waitUntilLoaderScreenDisappear();
        BrowserUtils.waitForVisibility(userName, 5);
        return userName.getText();
    }



   // public void logOut(){
  //      BrowserUtils.waitFor(2);
  //      BrowserUtils.clickWithJS(userName);
 //       BrowserUtils.clickWithJS(logOutLink);
//    }
    public void goToMyUser(){
        waitUntilLoaderScreenDisappear();
        BrowserUtils.waitForClickablility(userName, 5).click();
        BrowserUtils.waitForClickablility(myUser, 5).click();

    }

    public String getUsersFullName(){
        waitUntilLoaderScreenDisappear();
        BrowserUtils.waitForVisibility(usersFullName, Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
        return usersFullName.getText();
    }

    public void logout(){
        BrowserUtils.waitForStaleElement(usersFullName);
        usersFullName.click();
        logout.click();
    }

}
