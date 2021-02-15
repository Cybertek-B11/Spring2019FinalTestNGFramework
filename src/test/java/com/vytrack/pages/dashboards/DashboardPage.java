package com.vytrack.pages.dashboards;


import com.vytrack.utilities.Driver;
import org.openqa.selenium.support.PageFactory;
import com.vytrack.base.BasePage;


public class DashboardPage extends BasePage {
    public DashboardPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }

}
