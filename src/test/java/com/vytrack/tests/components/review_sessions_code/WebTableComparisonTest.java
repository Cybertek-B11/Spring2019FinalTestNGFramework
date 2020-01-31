package com.vytrack.tests.components.review_sessions_code;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class WebTableComparisonTest {

    @Test
    public void test(){
        WebDriver driver = Driver.getDriver();
        driver.get("http://practice.cybertekschool.com/tables");

        // get size
        int colNum = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr[1]/td")).size();
        int rowNum = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();

        System.out.println(colNum);
        System.out.println(rowNum);

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 1; j <= colNum; j++) {
                String xpath1 = "//table[@id='table1']/tbody/tr["+i+"]/td["+j+"]";
                String xpath2 = "//table[@id='table2']/tbody/tr["+i+"]/td["+j+"]";

                String val1 = driver.findElement(By.xpath(xpath1)).getText();
                String val2 = driver.findElement(By.xpath(xpath2)).getText();

                System.out.println("val1 = " + val1);
                System.out.println("val2 = " + val2);

                System.out.println();

            }
        }

        BrowserUtils.waitFor(4);
        driver.quit();

    }
}
