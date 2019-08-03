package com.vytrack.tests.components.activities;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarEventsTests extends TestBase {

    @Test
    public void verifyTitleColumn() {
        extentLogger = report.createTest("Verify column that column names are adjustable");
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        pages.loginPage().login(username, password);

        //go to Calendar Events page

        pages.dashboardPage().navigateToModule("Activities", "Calendar Events");

        //deselect title option from grid settings
        pages.calendarEventsPage().selectGridSetting("Title", false);
        BrowserUtils.waitPlease(3);

        //Verify that title column name is not visible any more
        Assert.assertFalse(pages.calendarEventsPage().verifyHeaderExists("Title"), "Title column name still visible.");
        extentLogger.pass("Verified that title column name is not visible any more.");

        //to close grid settings menu
        pages.calendarEventsPage().gridSettingsElement.click();
        extentLogger.info("Click on grid settings button.");

        //select title option again
        pages.calendarEventsPage().selectGridSetting("Title", true);

        //Verify that title column name is visible again
        Assert.assertTrue(pages.calendarEventsPage().verifyHeaderExists("Title"), "Title column is not visible.");
        extentLogger.pass("Verified that title column name is visible again.");
    }

    @Test(description = "Verify that date auto adjustable")
    public void verifyDateTest() {
        extentLogger = report.createTest("Verify column that date is adjustable");

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        pages.loginPage().login(username, password);

        //go to Calendar Events page
        pages.dashboardPage().navigateToModule("Activities", "Calendar Events");

        pages.calendarEventsPage().clickOnCreateCalendarEvent();
        extentLogger.info("Click on create calendar event button.");

        pages.calendarEventsPage().selectStartOrEndDate("8/15/2019", "start");

        //    verify start date is the same as end date
        Assert.assertEquals(pages.calendarEventsPage().getStartDate(), pages.calendarEventsPage().getEndDate());
        extentLogger.pass("Verified start date is the same as end date");


    }

    @Test(description = "Verify that date auto adjustable with today's and tomorrow's date.")
    public void verifyTodaysDateTest() {
        extentLogger = report.createTest("Verify that date auto adjustable with today's and tomorrow's date.");

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        pages.loginPage().login(username, password);

        //go to Calendar Events page
        pages.dashboardPage().navigateToModule("Activities", "Calendar Events");

        //click to create calendar event
        pages.calendarEventsPage().clickOnCreateCalendarEvent();
        extentLogger.info("Click on create calendar event button.");

        //select tomorrow date
        pages.calendarEventsPage().selectTomorrowDay();
        extentLogger.info("Select tomorrows date.");

        Assert.assertEquals(pages.calendarEventsPage().getStartDate(), pages.calendarEventsPage().getEndDate());
        extentLogger.pass("Verified that start date is equals to today's date.");

        //select today's date
        pages.calendarEventsPage().selectTodaysDate();

        //verify that start and end date is the same
        Assert.assertEquals(pages.calendarEventsPage().getStartDate(), pages.calendarEventsPage().getEndDate());
        extentLogger.pass("Verified that start date is equals to today's date.");

    }

    @Test(description = "Verify that end time changes exactly 1 hour later.")
    public void verifyTimeTest() {
        extentLogger = report.createTest("Verify that end time changes exactly 1 hours later");

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        pages.loginPage().login(username, password);

        //go to Calendar Events page
        pages.dashboardPage().navigateToModule("Activities", "Calendar Events");

        //click to create calendar event
        pages.calendarEventsPage().clickOnCreateCalendarEvent();
        extentLogger.info("Click on create calendar event button.");

        //select any time
        pages.calendarEventsPage().selectStartTime("1:00 PM");
        extentLogger.info("Select start time as 1:00 PM");

        //Verify that end time changes exactly 1 hour later
        Assert.assertEquals(pages.calendarEventsPage().differenceBetweenStartTimeAndEndTime(), 1);
        extentLogger.pass("Verified that end time exactly 1 hour later.");

    }

    @Test(description = "Verify that end time is 12:30 AM")
    public void verifyTimeTest2() {
        extentLogger = report.createTest("Verify that end time is 12:30 AM");

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        pages.loginPage().login(username, password);

        //go to Calendar Events page
        pages.dashboardPage().navigateToModule("Activities", "Calendar Events");

        //click to create calendar event
        pages.calendarEventsPage().clickOnCreateCalendarEvent();
        extentLogger.info("Click on create calendar event button.");

        //select 11:30 PM
        pages.calendarEventsPage().selectStartTime("11:30 PM");
        extentLogger.info("Select start time: 1:30 PM");

        //Verify that end time is 12:30 AM
        Assert.assertEquals(pages.calendarEventsPage().getEndTime(), "12:30 AM");
        extentLogger.pass("Verified that end time is 12:30 AM.");
    }

}
