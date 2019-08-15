package com.reviewclass;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Set;

public class SwitchWindows {
    static WebDriver driver = Driver.getDriver();

    @Test
    public void test1() {
        driver.get("http://practice.cybertekschool.com/windows");
        String window1 = driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();
        Set<String> windows = driver.getWindowHandles(); // will return ids of all windows
        BrowserUtils.waitFor(1);
        for(String window: windows){
            if(!window.equals(window1)){
                driver.switchTo().window(window);
            }
        }
        BrowserUtils.waitFor(3);
        String text = driver.findElement(By.tagName("h3")).getText();
        System.out.println(text);
        driver.quit();
    }

    @Test
    public void test2() {
        driver.get("http://practice.cybertekschool.com/windows");
        driver.findElement(By.linkText("Click Here")).click();
        //window = tab in the browser
        //windowhandle it's like an id of window
        //we can get current window handle with a method driver.getWindowHandle();
        //if we need all - .getWindowHandles()
        Set<String> windows = driver.getWindowHandles(); // will return ids of all windows
        BrowserUtils.waitFor(1);
        //jump among windows
        for(String window: windows){
           driver.switchTo().window(window);
           //once you find window(tab)
            //that you are looking for based on the title
           if(driver.getTitle().equals("New Window")){
               //stop jumping, just break
               break;
           }
        }
        BrowserUtils.waitFor(3);
        String text = driver.findElement(By.tagName("h3")).getText();
        System.out.println(text);
        driver.quit();
    }
}
