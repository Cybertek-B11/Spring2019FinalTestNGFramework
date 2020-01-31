package com.vytrack.tests.components.login_navigation;

<<<<<<< HEAD
import com.vytrack.pages.dashboards.DashboardPage;
=======
>>>>>>> 7d77d446a5fc4d82a75127a249269eec595956dc
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.ExcelUtil;
<<<<<<< HEAD
import com.vytrack.utilities.TestBase;
=======
import com.vytrack.base.TestBase;
>>>>>>> cc973126fa13c3aec4d466b5e1b6a8e62ea57918
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LoginTests extends TestBase {

    @Test
    public void loginTest1() {
        extentLogger = report.createTest("Login as store manager");

        //we are instantiating page class inside a tests class,
        //because for second test, if we run all tests in a row, driver will have null session
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");

        LoginPage loginPage =  pages.loginPage();

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


    @Test(dataProvider = "credentials_info")
//    @Parameters({ "username", "password" }) // get data from data testng.xml
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


<<<<<<< HEAD

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

=======
//    @Test(dataProvider = "credentials_info2") // get data from data provider
//    public void loginWithDataProvider(String execute, String username, String password, String firstname, String lastname, String result) {
//        extentLogger = report.createTest("DDT test" + username);
//        if (execute.equals("y")) {
//            pages.loginPage().login(username, password);
//            String actualFullName = pages.dashboardPage().getUserName();
//            String expectedFullName = firstname+" "+lastname;
//            pages.dashboardPage().waitUntilLoaderScreenDisappear();
//            Assert.assertEquals(actualFullName, expectedFullName);
//            pages.dashboardPage().logOut();
//        }else{
//            throw new SkipException("Test ignored");
//        }
//    }
//
//    @Test
//    public void test() {
//        extentLogger = report.createTest("DDT test");
//        ExcelUtil qa3 = new ExcelUtil("src/test/resources/Vytrack testusers.xlsx", "QA3-short");
//        System.out.println(qa3.rowCount());
//        System.out.println(qa3.columnCount());
//        System.out.println(qa3.getColumnsNames());
//
//        List<Map<String, String>> allRows = qa3.getDataList();
//        System.out.println(allRows);
//
//        for (int i = 0; i < allRows.size(); i++) {
//            Map<String, String> user = allRows.get(i);
//            if (user.get("execute").equals("y")) {
//                String username = user.get("username");
//                String password = user.get("password");
//               pages.loginPage().login(username, password);
//
//                String actualFullName = pages.dashboardPage().getUserName();
//                String expectedFullName = user.get("firstname") + " " + user.get("lastname");
//                if (actualFullName.equals(expectedFullName)) {
//                    qa3.setCellData("pass", "result", i + 1);
//                } else {
//                    qa3.setCellData("fail", "result", i + 1);
//                }
//
//                pages.dashboardPage().logOut();
//            } else {
//                qa3.setCellData("skipped", "result", i + 1);
//            }
//        }
//    }
//
//    @DataProvider(name = "credentials_info2")
//    public static Object[][] credentials2() {
//        ExcelUtil qa3 = new ExcelUtil("src/test/resources/Vytrack testusers.xlsx", "QA3-short");
//        System.out.println( Arrays.deepToString(qa3.getDataArray()));
//        return qa3.getDataArray();
//    }
>>>>>>> cc973126fa13c3aec4d466b5e1b6a8e62ea57918

}
