package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginTest1(){
        extentLogger = report.createTest("Login as store manager");

        //we are instantiating page class inside a tests class,
        //because for second test, if we run all tests in a row, driver will have null session
        LoginPage loginPage = new LoginPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.clickRememberMe();
        loginPage.login(username, password);
        //to verify that Dashboard page opened
        //Once page name Dashboard displays, means that we are logged successfully
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "Dashboard");
        extentLogger.pass("Verified page name: "+VYTrackUtils.getPageSubTitle());
    }

    @Test(description = "Login with invalid credentials and verify warning message")
    public void negativeLoginTest1(){
        extentLogger = report.createTest("Negative login test");

        LoginPage loginPage = new LoginPage();
        extentLogger.info("Login with wrongusername username and wrongpassword password");
        loginPage.login("wrongusername", "wrongpassword");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid user name or password.");
        extentLogger.pass("Verified that Message present: "+loginPage.getErrorMessage());
    }


}
