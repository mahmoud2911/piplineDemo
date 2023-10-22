package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;

public class SalesReportsPage extends PageBase {
    final private By salesReports = By.cssSelector("#table_header > div > div > div > div > div.relative > div:nth-child(1) > div > h1");

    public SalesReportsPage(WebDriver driver) {
        super(driver);
    }

    public SalesReportsPage assertThatSalesReportsIsOpened() {
        driver.element().assertThat(salesReports).isVisible().perform();
        return this;
    }
}

