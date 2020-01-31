package com.vytrack.pages.dashboards;

import com.vytrack.utilities.BasePage;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {
    public DashboardPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }

}
