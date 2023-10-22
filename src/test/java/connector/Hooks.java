/*
 * in this class we use cucumber hooks @Before & @After so that feature files can detect it and run as scenarios
 * initiate the webDriver and open the browser @Before
 * terminate the webDriver @After
 */
package connector;

import com.shaft.driver.SHAFT;
import com.shaft.driver.SHAFT.GUI.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    public static WebDriver driver;

    /**
     * start browser session
     * navigate to URL
     */
    @Before
    public void startDriver() {
        driver = new WebDriver();
        driver.browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }
    /**
     * close browser after executing test scenario
     */
    @After
    public void tearDown() {
        driver.quit();
    }

}
