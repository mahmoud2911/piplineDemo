package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import utils.JsonReader;

public class PaymentsReportPage extends PageBase {

    final private By paymentsPage = By.cssSelector("#table_header > div > div > div > div > div:nth-child(1) > h1");
    final private By emptyGridMessage = By.xpath("//span[contains(@class,'block mx-auto')]");
    final private By totalNetPayment = By.xpath("//table[@id='listing-table']/tfoot[1]/tr[1]/td[6]/span[1]");
    final private By paymentsReportDatePickerText = By.xpath("//div[@id='table_header']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[4]/button[1]/span[1]");
    final private By paymentReportPageHeader = By.tagName("h1");


    public PaymentsReportPage(WebDriver driver) {
        super(driver);
    }

    public PaymentsReportPage assertPaymentReportPageUrl() {
        assertCurrentPageUrlContains(JsonReader.jsonReader("paymentsReportPageUrl", "urls", "SalesReportData.json"));
        return this;
    }
    public PaymentsReportPage assertPaymentsReportPageTitle(String textAssertion){
        assertCurrentPageTitle(JsonReader.jsonReader("paymentsPageTitle",textAssertion,"SalesReportData.json"));
        return this;
    }

    public PaymentsReportPage assertPaymentsReportEmptyMessageText(String textLocalization) {
        assertElementText(emptyGridMessage, JsonReader.jsonReader("emptyPaymentsReportMessage", textLocalization, "SalesReportData.json"));
        return this;
    }
    public PaymentsReportPage assertPaymentsReportPageHeaderText(String textLocalization) {
        assertElementText(paymentReportPageHeader, JsonReader.jsonReader("paymentsPageHeader", textLocalization, "SalesReportData.json"));
        return this;
    }

    public PaymentsReportPage assertTotalNetPaymentsEqualsValue(String amountOfNetSales) {
        assertElementText(totalNetPayment, amountOfNetSales);
        return this;
    }

    public PaymentsReportPage assertPaymentsReportDatePickerTextEqualsValue(String date) {
        assertElementText(paymentsReportDatePickerText, date);
        return this;
    }

    public PaymentsReportPage assertThatPaymentsPageIsOpened() {
        assertVisibilityOfElement(paymentsPage);
        return this;
    }
}
