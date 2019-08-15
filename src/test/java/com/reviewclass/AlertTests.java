package com.reviewclass;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTests {
    static WebDriver driver = Driver.getDriver();



    @Test
    public void alertTest1(){
        driver.get("http://practice.cybertekschool.com/javascript_alerts");
        driver.findElement(By.cssSelector("[onclick='jsAlert()']")).click();
        Alert alert = driver.switchTo().alert(); // this is how we create instance of alert
        //in order to handle js alerts in selenium, we have to switch to alert
        System.out.println(alert.getText());
        BrowserUtils.waitFor(3);
        alert.accept(); // to click ok
        BrowserUtils.waitFor(3);
        String actualMessage = driver.findElement(By.id("result")).getText();
        String expectedMessage = "You successfuly clicked an alert";
        Assert.assertEquals(actualMessage, expectedMessage);
        driver.quit();
    }

    @Test
    public void alertTest2(){
        driver.get("http://practice.cybertekschool.com/javascript_alerts");
        driver.findElement(By.cssSelector("[onclick='jsConfirm()']")).click();
        Alert alert = driver.switchTo().alert(); // this is how we create instance of alert
        //in order to handle js alerts in selenium, we have to switch to alert

        System.out.println(alert.getText());
        BrowserUtils.waitFor(1);

        alert.dismiss(); // to click cancel

        BrowserUtils.waitFor(1);


        String actualMessage = driver.findElement(By.id("result")).getText();
        String expectedMessage = "You clicked: Cancel";


        Assert.assertEquals(actualMessage, expectedMessage);
        driver.quit();
    }

    @Test
    public void alertTest3(){
        driver.get("http://practice.cybertekschool.com/javascript_alerts");
        driver.findElement(By.cssSelector("[onclick='jsPrompt()']")).click();
        //to wait for alert
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().sendKeys("Automation"); // to enter some text into pop up
        driver.switchTo().alert().accept(); // to click ok
        System.out.println(driver.findElement(By.id("result")).getText());
        driver.quit();
    }
}
