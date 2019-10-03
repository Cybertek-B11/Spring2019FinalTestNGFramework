package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.dashboards.DashboardPage;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.ExcelUtil;
import com.vytrack.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginTest1() {
        extentLogger = report.createTest("Login as store manager");

        //we are instantiating page class inside a tests class,
        //because for second test, if we run all tests in a row, driver will have null session
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        pages.loginPage().clickRememberMe();
        pages.loginPage().login(username, password);
        //to verify that Dashboard page opened
        //Once page name Dashboard displays, means that we are logged successfully
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "Dashboard");
        extentLogger.pass("Verified page name: " + pages.dashboardPage().getPageSubTitle());
    }

    @Test(description = "Login with invalid credentials and verify warning message")
    public void negativeLoginTest1() {
        extentLogger = report.createTest("Negative login test");
        extentLogger.info("Login with wrongusername username and wrongpassword password");
        pages.loginPage().login("wrongusername", "wrongpassword");
        Assert.assertEquals(pages.loginPage().getErrorMessage(), "Invalid user name or password.");
        extentLogger.pass("Verified that Message present: " + pages.loginPage().getErrorMessage());
    }


    @Test
    @Parameters({ "username", "password" }) // get data from data testng.xml
    public void loginWithParameters(String username, String password) {
        extentLogger = report.createTest("Login as store manager");

        //we are instantiating page class inside a tests class,
        //because for second test, if we run all tests in a row, driver will have null session
        pages.loginPage().clickRememberMe();
        pages.loginPage().login(username, password);
        //to verify that Dashboard page opened
        //Once page name Dashboard displays, means that we are logged successfully
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "Dashboard");
        extentLogger.pass("Verified page name: " + pages.dashboardPage().getPageSubTitle());
    }

    @Test(dataProvider = "credentials_info") // get data from data provider
    public void loginWithDataProvider(String username, String password) {
        extentLogger = report.createTest("Login as store manager");
        System.out.println(username+"  ::  "+password);
        //we are instantiating page class inside a tests class,
        //because for second test, if we run all tests in a row, driver will have null session
        pages.loginPage().clickRememberMe();
        pages.loginPage().login(username, password);
        //to verify that Dashboard page opened
        //Once page name Dashboard displays, means that we are logged successfully
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "Dashboard");
        extentLogger.pass("Verified page name: " + pages.dashboardPage().getPageSubTitle());
    }

    @DataProvider(name = "credentials_info")
    public static Object[][] credentials() {
        return new Object[][] { { "storemanager85", "UserUser123" },
                                { "salesmanager110", "UserUser123" }};

    }


    @Test(description = "Login with invalid credentials and verify warning message")
    public void negativeLoginTest2() {
        extentLogger = report.createTest("Negative login test");
        extentLogger.info("Login with wrongusername username and wrongpassword password");
        pages.loginPage().login("wrongusername", "wrongpassword");
        softAssert.assertEquals(pages.loginPage().getErrorMessage(), "Invalid user name or password.");
        extentLogger.pass("Verified that Message present: " + pages.loginPage().getErrorMessage());
    }



    @Test(dataProvider = "credentials_list") // get data from data provider
    public void loginWithDataProvider(String execute, String username, String password, String firstname, String lastname, String result) {
        extentLogger = report.createTest("DDT test" + username);
        if (execute.equals("y")) {
            new LoginPage().login(username, password);
            DashboardPage dashboardPage = new DashboardPage();
            String actualFullName = dashboardPage.userFullName.getText();
            String expectedFullName = firstname+lastname;
            Assert.assertEquals(actualFullName, expectedFullName);
            dashboardPage.logOut();
        }
    }

    @DataProvider(name = "credentials_list")
    public static Object[][] credentials1 () {
        ExcelUtil qa3 = new ExcelUtil("src/test/resources/Vytrack testusers.xlsx", "QA3-short");
        return qa3.getDataArray();
    }


}
