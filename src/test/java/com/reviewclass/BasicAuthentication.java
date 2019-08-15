package com.reviewclass;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.testng.annotations.Test;

public class BasicAuthentication {

    @Test
    public void test1() {
        Driver.getDriver().get("http://ad:admin@practice.cybertekschool.com/basic_auth");
        BrowserUtils.waitFor(2);
        Driver.closeDriver();
    }
}
