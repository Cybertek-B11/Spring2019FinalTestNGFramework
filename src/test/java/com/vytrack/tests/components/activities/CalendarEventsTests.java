package com.vytrack.tests.components.activities;

import com.vytrack.pages.activites.CalendarEventsPage;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarEventsTests extends TestBase {

    @Test
    public void verifyTitleColumn() {
        extentLogger = report.createTest("Verify column that column names are adjustable");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //deselect title option from grid settings
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        calendarPage.selectGridSetting("Title", false);
        BrowserUtils.waitPlease(3);

        //Verify that title column name is not visible any more
        Assert.assertFalse(calendarPage.verifyHeaderExists("Title"), "Title column name still visible.");

        //to close grid settings menu
        calendarPage.gridSettingsElement.click();

        //select title option again
        calendarPage.selectGridSetting("Title", true);

        //Verify that title column name is visible again
        Assert.assertTrue(calendarPage.verifyHeaderExists("Title"), "Title column is not visible.");
    }

    @Test(description = "Verify that date auto adjustable")
    public void verifyDateTest(){
        extentLogger = report.createTest("Verify column that date is adjustable");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        calendarPage.clickOnCreateCalendarEvent();

        calendarPage.selectStartOrEndDate("8/15/2019", "start");

    //    verify start date is the same as end date
        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());


    }

    @Test(description = "Verify that date auto adjustable with today's amd tomorrow's date.")
    public void verifyTodaysDateTest(){
        extentLogger = report.createTest("Verify that date auto adjustable with today's amd tomorrow's date.");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //click to create calendar event
        calendarPage.clickOnCreateCalendarEvent();

        //select tomorrow date
        calendarPage.selectTomorrowDay();

        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());

        //select today's date
        calendarPage.selectTodaysDate();

        //verify that start and end date is the same
        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());

    }

    @Test(description = "Verify that end time changes exactly 1 hours later.")
    public void verifyTimeTest(){
        extentLogger = report.createTest("Verify that end time changes exactly 1 hours later");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //click to create calendar event
        calendarPage.clickOnCreateCalendarEvent();

        //select any time
        calendarPage.selectStartTime("1:00 PM");

        //Verify that end time changes exactly 1 hours later
        Assert.assertEquals(calendarPage.differenceBetweenStartTimeAndEndTime(), 1);

    }

    @Test(description = "Verify that end time is 12:30 AM")
    public void verifyTimeTest2(){
        extentLogger = report.createTest("Verify that end time is 12:30 AM");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //click to create calendar event
        calendarPage.clickOnCreateCalendarEvent();

        //select 11:30 PM
        calendarPage.selectStartTime("11:30 PM");

        //Verify that end time is 12:30 AM
        Assert.assertEquals(calendarPage.getEndTime(), "12:30 AM");
    }

}
