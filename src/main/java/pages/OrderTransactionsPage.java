package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import utils.JsonReader;

public class OrderTransactionsPage extends PageBase {

    final private By orderTransactionsPage = By.cssSelector("#table_header > div > div > div > div > div:nth-child(1) > h1");
    final private By emptyOrdersGridMessage = By.xpath("//span[contains(@class,'block mx-auto')]");
    final private By ordersPageHeader = By.tagName("h1");
    final private By ordersBusinessDateFilter = By.xpath("//input[contains(@class,'input p-2')]");
    final private By filterButton = By.xpath("(//button[contains(@class,'btn btn-white')]/following-sibling::button)[2]");
    final private By businessDateFilterField = By.xpath("(//div[@class='relative']//input)[2]");

    public OrderTransactionsPage(WebDriver driver) {
        super(driver);
    }


    public OrderTransactionsPage assertOrdersPageUrl() {
        assertCurrentPageUrlContains(JsonReader.jsonReader("ordersPageUrl", "urls", "Orders.json"));
        return this;
    }

    public OrderTransactionsPage assertOrdersPageTitle(String textLocalization) {
        assertCurrentPageTitle(JsonReader.jsonReader("ordersPageTitle", textLocalization, "Orders.json"));
        return this;
    }

    public OrderTransactionsPage assertOrdersReportEmptyMessageText(String textLocalization) {
        assertElementText(emptyOrdersGridMessage, JsonReader.jsonReader("emptyOrdersReportMessage", textLocalization, "Orders.json"));
        return this;
    }

    public OrderTransactionsPage assertOrdersPageHeaderText(String textLocalization) {
        assertElementText(ordersPageHeader, JsonReader.jsonReader("ordersPageHeader", textLocalization, "Orders.json"));
        return this;
    }

    public OrderTransactionsPage assertBusinessDateFilterText(String date,String view) {
        String tempDate = date;
        if(view.equals("Day")){
            tempDate = date + " - " + date;
        }
        assertElementText(ordersBusinessDateFilter, tempDate);
        return this;
    }

    public OrderTransactionsPage assertThatOrderTransactionsPageIsOpened() {
        assertVisibilityOfElement(orderTransactionsPage);
        return this;
    }
}
