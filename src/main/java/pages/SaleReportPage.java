package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import utils.JsonReader;

public class SaleReportPage extends PageBase {
    final private By salesPage = By.cssSelector("#sidebar_reports_sales");
    final private By totalOrderCount = By.xpath("//table[@id='listing-table']/tfoot[1]/tr[1]/td[11]/span[1]");
    final private By totalReturnAmount = By.xpath("//table[@id='listing-table']/tfoot[1]/tr[1]/td[18]/span[1]");
    final private By totalDiscountAmount = By.xpath("//table[@id='listing-table']/tfoot[1]/tr[1]/td[7]/span[1]");
    final private By totalNetSales = By.xpath("//table[@id='listing-table']/tfoot[1]/tr[1]/td[9]/span[1]");
    final private By emptySalesReportGridMessage = By.xpath("//span[contains(@class,'block mx-auto')]");
    final private By salesReportDatePickerText = By.xpath("//div[@id='table_header']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[4]/button[1]/span[1]");
    final private By saleReportPageHeader = By.tagName("h1");
    final private By paymentsLink = By.cssSelector("#main > div > div.px-8.md\\:px-10.py-8 > div > div:nth-child(2) > a");
    final private By salesReportsLink = By.cssSelector("#main > div > div.px-8.md\\:px-10.py-8 > div > div:nth-child(1) > a");

    public SaleReportPage(WebDriver driver) {
        super(driver);
    }

    public SaleReportPage clickOnSalesReportsLink() {
        clickOnElement(salesReportsLink);
        return this;
    }

    public SaleReportPage clickOnPaymentsLink() {
        clickOnElement(paymentsLink);
        return this;
    }

    public SaleReportPage assertSalesByBranchReportPageUrl() {
        assertCurrentPageUrlContains(JsonReader.jsonReader("salesByBranchReportPageUrl", "urls", "SalesReportData.json"));
        return this;
    }

    public SaleReportPage assertSalesByProductReportPageUrl() {
        assertCurrentPageUrlContains(JsonReader.jsonReader("salesByProductReportPageUrl", "urls", "SalesReportData.json"));
        return this;
    }

    public SaleReportPage assertSalesByDiscountReportPageUrl() {
        assertCurrentPageUrlContains(JsonReader.jsonReader("salesByDiscountReportPageUrl", "urls", "SalesReportData.json"));
        return this;
    }

    public SaleReportPage assertSalesByBranchReportPageTitle(String textAssertion) {
        assertCurrentPageTitle(JsonReader.jsonReader("salesByBranchPageTitle", textAssertion, "SalesReportData.json"));
        return this;
    }

    public SaleReportPage assertSalesByProductReportPageTitle(String textAssertion) {
        assertCurrentPageTitle(JsonReader.jsonReader("salesByProductPageTitle", textAssertion, "SalesReportData.json"));
        return this;
    }

    public SaleReportPage assertSalesByDiscountReportPageTitle(String textAssertion) {
        assertCurrentPageTitle(JsonReader.jsonReader("salesByDiscountPageTitle", textAssertion, "SalesReportData.json"));
        return this;
    }

    public SaleReportPage assertSalesByBranchReportPageHeader(String textAssertion) {
        assertElementText(saleReportPageHeader,JsonReader.jsonReader("salesByBranchPageHeader", textAssertion, "SalesReportData.json"));
        return this;
    }

    public SaleReportPage assertSalesByProductReportPageHeader(String textAssertion) {
        assertElementText(saleReportPageHeader,JsonReader.jsonReader("salesByProductPageHeader", textAssertion, "SalesReportData.json"));
        return this;
    }

    public SaleReportPage assertSalesByDiscountReportPageHeader(String textAssertion) {
        assertElementText(saleReportPageHeader,JsonReader.jsonReader("salesByDiscountPageHeader", textAssertion, "SalesReportData.json"));
        return this;
    }


    public SaleReportPage assertTotalOrderCountEqualsValue(String numberOfOrders) {
        assertElementText(totalOrderCount, numberOfOrders);
        return this;
    }

    public SaleReportPage assertTotalReturnAmountEqualsValue(String amountOfReturns) {
        assertElementText(totalReturnAmount, amountOfReturns);
        return this;
    }

    public SaleReportPage assertTotalDiscountAmountEqualsValue(String amountOfDiscount) {
        assertElementText(totalDiscountAmount, amountOfDiscount);
        return this;
    }

    public SaleReportPage assertTotalNetSalesEqualsValue(String amountOfNetSales) {
        assertElementText(totalNetSales, amountOfNetSales);
        return this;
    }

    public SaleReportPage assertSalesReportDatePickerTextEqualsValue(String date) {
        assertElementText(salesReportDatePickerText, date);
        return this;
    }

    public SaleReportPage assertSalesReportEmptyMessageText(String textLocalization) {
        assertElementText(emptySalesReportGridMessage, JsonReader.jsonReader("emptySalesReportMessage", textLocalization, "SalesReportData.json"));
        return this;
    }

    public SaleReportPage assertThatSalesPageIsOpened() {
        driver.element().assertThat(salesPage).isVisible().perform();
        return this;
    }
}
