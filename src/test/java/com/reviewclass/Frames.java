package com.reviewclass;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Frames {

    static WebDriver driver = Driver.getDriver();

    @Test
    public void test1(){
        driver.get("http://practice.cybertekschool.com/iframe");
        driver.switchTo().frame("mce_0_ifr"); // switch to the frame
        driver.findElement(By.id("tinymce")).clear();
        driver.findElement(By.id("tinymce")).sendKeys("testing frames");
        BrowserUtils.waitFor(2);
        driver.switchTo().defaultContent();//to exit from the frame
        driver.quit();
    }

    @Test
    public void test2(){
        driver.get("http://practice.cybertekschool.com/nested_frames");
        driver.switchTo().frame(0);
        driver.switchTo().frame(1);
        driver.switchTo().defaultContent();
        System.out.println(driver.getPageSource());
        driver.quit();
    }
}
