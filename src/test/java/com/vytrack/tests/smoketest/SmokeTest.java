package com.vytrack.tests.smoketest;

import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends TestBase {

    @Test(groups= {"smoke"})
    public void verifyDashboards() {
        extentLogger = report.createTest("Verify Dashboards module");
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        pages.loginPage().login(username, password);

        pages.loginPage().navigateToModule("Dashboards", "Dashboard");
        Assert.assertEquals(pages.loginPage().getPageSubTitle(), "Dashboard");
        extentLogger.pass("Verified that Dashboard page opens");

        pages.loginPage().navigateToModule("Dashboards", "Manage Dashboards");
        Assert.assertEquals(pages.loginPage().getPageSubTitle(), "All Manage Dashboards");
        extentLogger.pass("Verified that Manage Dashboards page opens");
    }

    @Test(groups= {"smoke"})
    public void verifyFleet() {
        extentLogger = report.createTest("Verify Fleet module");
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        pages.loginPage().login(username, password);

        pages.loginPage().navigateToModule("Fleet", "Vehicles");
        Assert.assertEquals(pages.loginPage().getPageSubTitle(), "All Cars");
        extentLogger.pass("Verified that Vehicles page opens");

        pages.loginPage().navigateToModule("Fleet", "Vehicle Costs");
        Assert.assertEquals(pages.loginPage().getPageSubTitle(), "All Vehicle Costs");
        extentLogger.pass("Verified that Vehicle Costs page opens");

        pages.loginPage().navigateToModule("Fleet", "Vehicle Contracts");
        Assert.assertEquals(pages.loginPage().getPageSubTitle(), "All Vehicle Contract");
        extentLogger.pass("Verified that Vehicle Contracts page opens");

    }

    @Test(groups= {"smoke"})
    public void verifySales() {
        extentLogger = report.createTest("Verify Sales module");
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        pages.loginPage().login(username, password);

        pages.loginPage().navigateToModule("Sales", "Opportunities");
        Assert.assertEquals(pages.loginPage().getPageSubTitle(), "Open Opportunities");
        extentLogger.pass("Verified that Opportunities page opens");


    }

    @Test(groups= {"smoke"})
    public void verifySystem() {
        extentLogger = report.createTest("Verify System module");
        pages.loginPage().login(); // login with default credentials
        pages.loginPage().navigateToModule("System", "Jobs");
        Assert.assertEquals(pages.loginPage().getPageSubTitle(), "All Jobs List");
        extentLogger.pass("Verified that Jobs page opens");
    }

    @Test(groups= {"smoke"})
    public void verifyMarketing() {
        extentLogger = report.createTest("Verify Marketing module");
        pages.loginPage().login(); // login with default credentials
        pages.loginPage().navigateToModule("Marketing", "Campaigns");
        Assert.assertEquals(pages.loginPage().getPageSubTitle(), "All Campaigns");
        extentLogger.pass("Verified that Campaigns page opens");
    }

    @Test(groups= {"smoke"})
    public void verifyCustomers() {
        extentLogger = report.createTest("Verify Customers module");
        pages.loginPage().login(); // login with default credentials
        pages.loginPage().navigateToModule("Customers", "Accounts");
        Assert.assertEquals(pages.loginPage().getPageSubTitle(), "All Accounts");
        extentLogger.pass("Verified that Accounts page opens");
    }

}
