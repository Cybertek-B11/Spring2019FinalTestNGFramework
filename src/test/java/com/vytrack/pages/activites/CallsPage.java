package com.vytrack.pages.activites;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class CallsPage {


    public CallsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
