package com.vytrack.tests.smoketest;

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

        pages.dashboardPage().navigateToModule("Dashboards", "Dashboard");
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "Dashboard");
        extentLogger.pass("Verified that Dashboard page opens");

        pages.dashboardPage().navigateToModule("Dashboards", "Manage Dashboards");
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "All Manage Dashboards");
        extentLogger.pass("Verified that Manage Dashboards page opens");
    }

    @Test(groups= {"smoke"})
    public void verifyFleet() {
        extentLogger = report.createTest("Verify Fleet module");
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        pages.loginPage().login(username, password);

        pages.dashboardPage().navigateToModule("Fleet", "Vehicles");
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "All Cars");
        extentLogger.pass("Verified that Vehicles page opens");

        pages.dashboardPage().navigateToModule("Fleet", "Vehicle Costs");
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "All Vehicle Costs");
        extentLogger.pass("Verified that Vehicle Costs page opens");

        pages.dashboardPage().navigateToModule("Fleet", "Vehicle Contracts");
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "All Vehicle Contract");
        extentLogger.pass("Verified that Vehicle Contracts page opens");

    }

    @Test(groups= {"smoke"})
    public void verifySales() {
        extentLogger = report.createTest("Verify Sales module");
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        pages.loginPage().login(username, password);

        pages.dashboardPage().navigateToModule("Sales", "Opportunities");
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "Open Opportunities");
        extentLogger.pass("Verified that Opportunities page opens");


    }

    @Test(groups= {"smoke"})
    public void verifySystem() {
        extentLogger = report.createTest("Verify System module");
        pages.loginPage().login(); // login with default credentials
        pages.dashboardPage().navigateToModule("System", "Jobs");
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "All Jobs List");
        extentLogger.pass("Verified that Jobs page opens");
    }

    @Test(groups= {"smoke"})
    public void verifyMarketing() {
        extentLogger = report.createTest("Verify Marketing module");
        pages.loginPage().login(); // login with default credentials
        pages.dashboardPage().navigateToModule("Marketing", "Campaigns");
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "All Campaigns");
        extentLogger.pass("Verified that Campaigns page opens");
    }

    @Test(groups= {"smoke"})
    public void verifyCustomers() {
        extentLogger = report.createTest("Verify Customers module");
        pages.loginPage().login(); // login with default credentials
        pages.dashboardPage().navigateToModule("Customers", "Accounts");
        Assert.assertEquals(pages.dashboardPage().getPageSubTitle(), "All Accounts");
        extentLogger.pass("Verified that Accounts page opens");
    }

}
