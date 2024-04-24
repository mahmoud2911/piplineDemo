/*
 * in this class we use cucumber hooks @Before & @After so that feature files can detect it and run as scenarios
 * initiate the webDriver and open the browser @Before
 * terminate the webDriver @After
 */
package connector;

import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.shaft.driver.SHAFT.GUI.WebDriver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.IOException;
import java.util.Set;

public class Hooks {
    public static WebDriver driver;
    /**
     * start browser session
     * navigate to URL
     */
    @Before
    public void startDriver() throws IOException {
        Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
        String userDataDirectory = "C:/Users/Foodics/AppData/Local/Google/Chrome/User Data";
        String profileDirectory = "Profile 5";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=" + userDataDirectory);
        chromeOptions.addArguments("profile-directory=" + profileDirectory);
        driver = new WebDriver(DriverFactory.DriverType.CHROME, chromeOptions);
        driver.browser().navigateToURL("https://console.foodics.dev/login");
    }

    /**
     * close browser after executing test scenario
     */
    @After
    public void tearDown() {
        driver.quit();
    }
}