package support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

import static support.TestContext.getDriver;

public class Hooks {

    @Before(order = 0)
    public void scenarioStart() {
        TestContext.initialize();
        getDriver().manage().deleteAllCookies();
        // this is for pageLoad wait - the browser will wait 1 min  for the full page load and then only failed the case
        getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        // sometimes the page already loaded but some blocks on the page will be re-loaded once you click or do some action  -
        // to solve it you need to use implicitWait - to wait for the element to be loaded.
        // It will NOT increase the waiting time for 5 second it's just max time that it will search for element and then fail.
        getDriver().manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }

    @After(order = 0)
    public void scenarioEnd(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();
            byte[] screenshot = screenshotTaker.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        TestContext.teardown();
    }
}
