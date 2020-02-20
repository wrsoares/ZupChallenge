package gherkin.hook;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import driver.AppWeb;
import io.github.bonigarcia.wdm.DriverManagerType;
import report.Report;

import static driver.AppWeb.BROWSER;
import static driver.AppWeb.quitDriver;
import static driver.Drivers.*;

public class Hook {

    @Before
    public void init(Scenario scenario) {
        AppWeb appWeb = new AppWeb();
        appWeb.setUpDriver(DriverManagerType.CHROME, BROWSER);
        testScenario.set(scenario);
    }

    @After
    public void finish() {
        if (testScenario.get().isFailed()) {
            Report.appendToReport();
        }
        quitDriver();
    }
}