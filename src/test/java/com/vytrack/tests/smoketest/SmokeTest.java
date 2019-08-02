package com.vytrack.tests.smoketest;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends TestBase {

    @Test
    public void verifyDashboards(){
        extentLogger = report.createTest("Verify Dashboards module");
        LoginPage loginPage = new LoginPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        VYTrackUtils.navigateToModule("Dashboards", "Dashboard");
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "Dashboard");

        VYTrackUtils.navigateToModule("Dashboards", "Manage Dashboards");
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "All Manage Dashboards");
    }

    @Test
    public void verifyFleet(){
        extentLogger = report.createTest("Verify Fleet module");
        LoginPage loginPage = new LoginPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        VYTrackUtils.navigateToModule("Fleet", "Vehicles");
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "All Cars");


        VYTrackUtils.navigateToModule("Fleet", "Vehicle Costs");
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "All Vehicle Costs");

        VYTrackUtils.navigateToModule("Fleet", "Vehicle Contracts");
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "All Vehicle Contract");

    }

    @Test
    public void verifySales(){
        extentLogger = report.createTest("Verify Sales module");
        LoginPage loginPage = new LoginPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        VYTrackUtils.navigateToModule("Sales", "Opportunities");
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "Open Opportunities");


    }

    @Test
    public void verifySystem(){
        extentLogger = report.createTest("Verify System module");
        LoginPage loginPage = new LoginPage();
        loginPage.login(); // login with default credentials
        VYTrackUtils.navigateToModule("System", "Jobs");
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "All Jobs List");
    }

    @Test
    public void verifyMarketing(){
        extentLogger = report.createTest("Verify Marketing module");
        LoginPage loginPage = new LoginPage();
        loginPage.login(); // login with default credentials
        VYTrackUtils.navigateToModule("Marketing", "Campaigns");
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "All Campaigns");
    }

    @Test
    public void verifyCustomers(){
        extentLogger = report.createTest("Verify Customers module");
        LoginPage loginPage = new LoginPage();
        loginPage.login(); // login with default credentials
        VYTrackUtils.navigateToModule("Customers", "Accounts");
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "All Accounts");
    }

}
