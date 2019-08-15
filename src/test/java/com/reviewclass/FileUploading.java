package com.reviewclass;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class FileUploading {
    static WebDriver driver = Driver.getDriver();

    @Test
    public void fileUploadTest1(){
        driver.get("http://practice.cybertekschool.com/upload");
        //provide path to the file
        driver.findElement(By.id("file-upload")).sendKeys("/Users/cybertekschool/Desktop/testfile.txt");
        driver.findElement(By.id("file-submit")).click();
        BrowserUtils.waitFor(4);
        driver.quit();
    }
}
