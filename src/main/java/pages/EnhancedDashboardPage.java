package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import utils.JsonReader;
import utils.Wait;

import java.text.DecimalFormat;

public class EnhancedDashboardPage extends PageBase {
    final private By compareToggleLabel = By.cssSelector("main#main>div>div>div:nth-of-type(2)>div>div>div>div>div>div:nth-of-type(2)>span");
    final private By compareToggle = By.className("toggle__handler");
    final private By compareToggleIsChecked = By.tagName("label");
    final private By filterIcon = By.xpath("//div[contains(@class,'flex sm:ms-9')]/following-sibling::button[1]");
    final private By ordersCard = By.xpath("(//div[contains(@class,'w-full lg:w-1/3')])[1]");
    final private By numberOfOrders = By.xpath("(//span[@class='me-1'])[1]");
    final private By ordersCardLabel = By.xpath("(//span[@class='font-semibold'])[1]");
    final private By netSalesCard = By.xpath("(//div[contains(@class,'w-full lg:w-1/3')])[2]");
    final private By amountOfSales = By.xpath("(//span[@class='me-1'])[2]");
    final private By netSalesCardLabel = By.xpath("(//span[@class='font-semibold'])[2]");
    final private By netPaymentsCard = By.xpath("(//div[contains(@class,'w-full lg:w-1/3')])[3]");
    final private By amountOfPayments = By.xpath("(//span[@class='me-1'])[3]");
    final private By netPaymentsCardLabel = By.xpath("(//span[@class='font-semibold'])[3]");
    final private By returnAmountChartValue = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/h3[1]/span[1]");
    final private By returnAmountCard = By.xpath("(//div[contains(@class,'w-full lg:w-1/3')]/following-sibling::div)[3]");
    final private By discountAmountCard = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]");
    final private By discountAmountChartValue = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/h3[1]/span[1]");
    //    final private By ordersPercentage = By.xpath("(//span[@class='text-title-3 leading-8'])[1]");
//    final private By netSalesPercentage = By.xpath("(//span[@class='text-title-3 leading-8'])[2]");
//    final private By netPaymentsPercentage = By.xpath("(//span[@class='text-title-3 leading-8'])[3]");
    final private By returnAmountPercentage = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/h3[1]/span[2]");
    final private By discountAmountPercentage = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/h3[1]/span[2]");
    final private By ordersPercentage = By.xpath("(//span[@class='me-1']/following-sibling::span)[1]");
    final private By netSalesPercentage = By.xpath("(//span[@class='me-1']/following-sibling::span)[2]");
    final private By netPaymentsPercentage = By.xpath("(//span[@class='me-1']/following-sibling::span)[3]");

    final private By ordersMainComparisonDatesAndTotals = By.xpath("(//div[contains(@class,'flex flex-wrap')])[3]");
    final private By netSalesMainComparisonDatesAndTotals = By.xpath("(//div[contains(@class,'px-2 pb-5')]/following-sibling::div)[2]");
    final private By netPaymentsMainComparisonDatesAndTotals = By.xpath("(//div[contains(@class,'px-2 pb-5')]/following-sibling::div)[3]");
    final private By returnAmountMainComparisonDatesAndTotals = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[3]");
    final private By discountAmountMainComparisonDatesAndTotals = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[3]");
    final private By ordersChartMainDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/span[1]");
    final private By ordersChartComparisonDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/span[1]");
    final private By netSalesChartMainDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[3]/div[2]/span[1]");
    final private By netSalesChartComparisonDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[3]/div[1]/span[1]");
    final private By netPaymentsChartMainDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[3]/div[2]/span[1]");
    final private By netPaymentsChartComparisonDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[3]/div[1]/span[1]");
    final private By returnAmountChartMainDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[3]/div[2]/span[1]");
    final private By returnAmountChartComparisonDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[3]/div[1]/span[1]");
    final private By discountAmountChartMainDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[3]/div[2]/span[1]");
    final private By discountAmountChartComparisonDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[3]/div[1]/span[1]");
    final private By orderTypesChartMainDate = By.xpath("(//div[@class='date font-bold'])[1]");
    final private By orderTypesChartComparisonDate = By.xpath("(//div[@class='date font-bold'])[2]");
    final private By topProductByNetSalesChartMainDate = By.xpath("(//div[@class='font-bold flex']//div)[2]");
    final private By topProductByNetSalesChartComparisonDate = By.xpath("(//div[@class='font-bold flex']//div)[3]");
    final private By topPaymentsChartMainDate = By.xpath("(//div[@class='w-1/12']/following-sibling::div)[3]");
    final private By topPaymentsChartComparisonDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]");
    final private By topBranchesByNetSalesChartMainDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]");
    final private By topBranchesByNetSalesChartComparisonDate = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]");
    final private By mainDateDeliveryOrderType = By.xpath("(//div[contains(@class,'w-1/2 mb-2')])[1]");
    final private By mainDateDineInOrderType = By.xpath("(//div[contains(@class,'w-1/2 mb-2')])[2]");
    final private By mainDatePickUpOrderType = By.xpath("(//div[contains(@class,'w-1/2 mb-2')])[3]");
    final private By mainDateDriveThruOrderType = By.xpath("(//div[contains(@class,'w-1/2 mb-2')]/following-sibling::div)[3]");
    final private By comparisonDateDeliveryOrderType = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]/div[1]/div[3]/div[2]/div[2]/div[1]");
    final private By comparisonDateDineInOrderType = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]/div[1]/div[3]/div[2]/div[2]/div[2]");
    final private By comparisonDatePickUpOrderType = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]/div[1]/div[3]/div[2]/div[2]/div[3]");
    final private By comparisonDateDriveThruOrderType = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]/div[1]/div[3]/div[2]/div[2]/div[4]");
    final private By orderMainDateTotalValue = By.xpath("(//span[@class='font-bold'])[2]");
    final private By netSalesMainDateTotalValue = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[3]/div[2]/span[2]");
    final private By netPaymentMainDateTotalValue = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[3]/div[2]/span[2]");
    final private By returnAmountMainDateTotalValue = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[3]/div[2]/span[2]");
    final private By discountAmountMainDateTotalValue = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[3]/div[2]/span[2]");
    final private By orderComparisonDateTotalValue = By.xpath("(//span[@class='font-bold'])[1]");
    final private By netSalesComparisonDateTotalValue = By.xpath("(//span[@class='font-bold'])[3]");
    final private By netPaymentComparisonDateTotalValue = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[3]/div[1]/span[2]");
    final private By returnAmountComparisonDateTotalValue = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[3]/div[1]/span[2]");
    final private By discountAmountComparisonDateTotalValue = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[3]/div[1]/span[2]");

    Wait wait = new Wait(driver);


    public EnhancedDashboardPage(WebDriver driver) {
        super(driver);
    }


    public EnhancedDashboardPage clickOnCompareToggle() {
        clickOnElement(compareToggle);
        wait.waitInVisibilityOfProgressBar(50);
        return this;
    }

    public EnhancedDashboardPage clickOnFilter() {
        clickOnElement(filterIcon);
        return this;
    }

    public EnhancedDashboardPage clickOnMainDateOrderType(String orderType) {
        if (orderType.equals("Delivery")) {
            clickOnElement(mainDateDeliveryOrderType);
        } else if (orderType.equals("Dine In")) {
            clickOnElement(mainDateDineInOrderType);
        } else if (orderType.equals("Pick Up")) {
            clickOnElement(mainDatePickUpOrderType);
        } else if (orderType.equals("Drive Thru")) {
            clickOnElement(mainDateDriveThruOrderType);
        }
        return this;
    }

    public EnhancedDashboardPage clickOnComparisonDateOrderType(String orderType) {
        if (orderType.equals("Delivery")) {
            clickOnElement(comparisonDateDeliveryOrderType);
        } else if (orderType.equals("Dine In")) {
            clickOnElement(comparisonDateDineInOrderType);
        } else if (orderType.equals("Pick Up")) {
            clickOnElement(comparisonDatePickUpOrderType);
        } else if (orderType.equals("Drive Thru")) {
            clickOnElement(comparisonDateDriveThruOrderType);
        }
        return this;
    }

    public String getOrdersNumber() {
        return getElementText(numberOfOrders);
    }

    public String getNetSalesAmount() {
        return getElementText(amountOfSales);
    }

    public String getNetSalesAmountWithoutCurrency() {return getElementText(amountOfSales).replaceAll("[^0-9]", "");
    }
    public String getNetPaymentAmount() {
        return getElementText(amountOfPayments);
    }

    public String getReturnAmount() {
        return getElementText(returnAmountChartValue);
    }

    public String getDiscountAmount() {
        return getElementText(discountAmountChartValue);
    }

    /**
     * function that take main date and comparison date total values
     * Using this formula ((main date total value - comparison date total values)/ comparison date total values)*100
     * @param mainDateTotal locator for the total of main date
     * @param comparisonDateTotal locator for the total of comparison date
     * @return
     */
    private String calculatePercentage(By mainDateTotal, By comparisonDateTotal) {
        DecimalFormat df = new DecimalFormat("#.#");
        if (getElementText(comparisonDateTotal).replaceAll("[^0-9]", "").equals("0")) {
            return "(N/A%)";
        } else if (getElementText(mainDateTotal).equals(getElementText(comparisonDateTotal))) {
            return "(0%)";
        } else {
            double orderChartMainDateTotal = Double.parseDouble(getElementText(mainDateTotal).replaceAll("[^0-9]", ""));
            double orderChartComparisonDateTotal = Double.parseDouble(getElementText(comparisonDateTotal).replaceAll("[^0-9]", ""));
            double doublePercentage = ((orderChartMainDateTotal - orderChartComparisonDateTotal) / orderChartComparisonDateTotal) * 100;
            return "(" + df.format(doublePercentage) + "%)";
        }
    }


    public EnhancedDashboardPage setOrdersNumberInJson() {
        JsonReader.updateJsonKey("Dashboard.json", "ordersCount", getOrdersNumber());
        return this;
    }

    public EnhancedDashboardPage setNetSalesAmountInJson() {
        JsonReader.updateJsonKey("Dashboard.json", "netSalesAmount", getNetSalesAmount());
        return this;
    }

    public EnhancedDashboardPage setNetSalesAmountWithoutCurrencyInJson() {
        JsonReader.updateJsonKey("Dashboard.json", "netSalesAmountCurrency", getNetSalesAmountWithoutCurrency());
        return this;
    }

    public EnhancedDashboardPage setNetPaymentsAmountInJson() {
        JsonReader.updateJsonKey("Dashboard.json", "netPaymentsAmount", getNetPaymentAmount());
        return this;
    }

    public EnhancedDashboardPage assertEnhancedDashboardPageUrl() {
        assertCurrentPageUrl(JsonReader.jsonReader("enhancedDashboardPageUrl", "urls", "Dashboard.json"));
        return this;
    }


    public EnhancedDashboardPage assertCompareToggleLabelText(String textLocalization) {
        assertElementText(compareToggleLabel, JsonReader.jsonReader("compareToggleLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public EnhancedDashboardPage assertOrderMainDateTotalEqualsChartOrderCount() {
        assertElementText(orderMainDateTotalValue, getOrdersNumber());
        return this;
    }

    public EnhancedDashboardPage assertNetSalesMainDateTotalEqualsChartNetSales() {
        assertElementText(netSalesMainDateTotalValue, getNetSalesAmount());
        return this;
    }

    public EnhancedDashboardPage assertNetPaymentMainDateTotalEqualsChartNetPayment() {
        assertElementText(netPaymentMainDateTotalValue, getNetPaymentAmount());
        return this;
    }

    public EnhancedDashboardPage assertReturnAmountMainDateTotalEqualsChartReturnAmount() {
        assertElementText(returnAmountMainDateTotalValue, getReturnAmount());
        return this;
    }

    public EnhancedDashboardPage assertDiscountAmountMainDateTotalEqualsChartDiscountAmount() {
        assertElementText(discountAmountMainDateTotalValue, getDiscountAmount());
        return this;
    }

    public EnhancedDashboardPage assertOrderChartPercentageIsCorrect() {
        assertElementText(ordersPercentage, calculatePercentage(orderMainDateTotalValue, orderComparisonDateTotalValue));
        return this;
    }

    public EnhancedDashboardPage assertNetSalesChartPercentageIsCorrect() {
        assertElementText(netSalesPercentage, calculatePercentage(netSalesMainDateTotalValue, netSalesComparisonDateTotalValue));
        return this;
    }

    public EnhancedDashboardPage assertNetPaymentChartPercentageIsCorrect() {
        assertElementText(netPaymentsPercentage, calculatePercentage(netPaymentMainDateTotalValue, netPaymentComparisonDateTotalValue));
        return this;
    }

    public EnhancedDashboardPage assertReturnAmountChartPercentageIsCorrect() {
        assertElementText(returnAmountPercentage, calculatePercentage(returnAmountMainDateTotalValue, returnAmountComparisonDateTotalValue));
        return this;
    }

    public EnhancedDashboardPage assertDiscountAmountChartPercentageIsCorrect() {
        assertElementText(discountAmountPercentage, calculatePercentage(discountAmountMainDateTotalValue, discountAmountComparisonDateTotalValue));
        return this;
    }

    public EnhancedDashboardPage assertOrderChartMainDateText(String mainDate) {
        assertElementText(ordersChartMainDate, mainDate);
        return this;
    }

    public EnhancedDashboardPage assertOrderChartComparisonDateText(String comparisonDate) {
        assertElementText(ordersChartComparisonDate, comparisonDate);
        return this;
    }

    public EnhancedDashboardPage assertNetSalesChartMainDateText(String mainDate) {
        assertElementText(netSalesChartMainDate, mainDate);
        return this;
    }

    public EnhancedDashboardPage assertNetSalesChartComparisonDateText(String comparisonDate) {
        assertElementText(netSalesChartComparisonDate, comparisonDate);
        return this;
    }

    public EnhancedDashboardPage assertNetPaymentsChartMainDateText(String mainDate) {
        assertElementText(netPaymentsChartMainDate, mainDate);
        return this;
    }

    public EnhancedDashboardPage assertNetPaymentsChartComparisonDateText(String comparisonDate) {
        assertElementText(netPaymentsChartComparisonDate, comparisonDate);
        return this;
    }

    public EnhancedDashboardPage assertReturnAmountChartMainDateText(String mainDate) {
        assertElementText(returnAmountChartMainDate, mainDate);
        return this;
    }

    public EnhancedDashboardPage assertReturnAmountChartComparisonDateText(String comparisonDate) {
        assertElementText(returnAmountChartComparisonDate, comparisonDate);
        return this;
    }

    public EnhancedDashboardPage assertDiscountAmountChartMainDateText(String mainDate) {
        assertElementText(discountAmountChartMainDate, mainDate);
        return this;
    }

    public EnhancedDashboardPage assertDiscountAmountChartComparisonDateText(String comparisonDate) {
        assertElementText(discountAmountChartComparisonDate, comparisonDate);
        return this;
    }

    public EnhancedDashboardPage assertOrderTypesChartMainDateText(String mainDate) {
        assertElementText(orderTypesChartMainDate, mainDate);
        return this;
    }

    public EnhancedDashboardPage assertOrderTypesChartComparisonDateText(String comparisonDate) {
        assertElementText(orderTypesChartComparisonDate, comparisonDate);
        return this;
    }

    public EnhancedDashboardPage assertTopProductsByNetSalesChartMainDateText(String mainDate) {
        assertElementText(topProductByNetSalesChartMainDate, mainDate);
        return this;
    }

    public EnhancedDashboardPage assertTopProductByNetSalesChartComparisonDateText(String comparisonDate) {
        assertElementText(topProductByNetSalesChartComparisonDate, comparisonDate);
        return this;
    }

    public EnhancedDashboardPage assertTopPaymentsChartMainDateText(String mainDate) {
        assertElementText(topPaymentsChartMainDate, mainDate);
        return this;
    }

    public EnhancedDashboardPage assertTopPaymentsChartComparisonDateText(String comparisonDate) {
        assertElementText(topPaymentsChartComparisonDate, comparisonDate);
        return this;
    }

    public EnhancedDashboardPage assertTopBranchesByNetSalesChartMainDateText(String mainDate) {
        assertElementText(topBranchesByNetSalesChartMainDate, mainDate);
        return this;
    }

    public EnhancedDashboardPage assertTopBranchesByNetSalesChartComparisonDateText(String comparisonDate) {
        assertElementText(topBranchesByNetSalesChartComparisonDate, comparisonDate);
        return this;
    }

    public EnhancedDashboardPage assertOrdersCardLabelText(String textLocalization) {
        assertElementText(ordersCardLabel, JsonReader.jsonReader("ordersCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public EnhancedDashboardPage assertNetSalesCardLabelText(String textLocalization) {
        assertElementContainsText(netSalesCardLabel, JsonReader.jsonReader("netSalesCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public EnhancedDashboardPage assertNetPaymentsCardLabelText(String textLocalization) {
        assertElementContainsText(netPaymentsCardLabel, JsonReader.jsonReader("netPaymentsCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public EnhancedDashboardPage assertMainDateOrderTypeText(String orderType) {
        if (orderType.equals("Delivery")) {
            assertElementText(mainDateDeliveryOrderType, orderType);
        } else if (orderType.equals("Dine In")) {
            assertElementText(mainDateDineInOrderType, orderType);
        } else if (orderType.equals("Pick Up")) {
            assertElementText(mainDatePickUpOrderType, orderType);
        } else if (orderType.equals("Drive Thru")) {
            assertElementText(mainDateDriveThruOrderType, orderType);
        }
        return this;
    }

    public EnhancedDashboardPage assertComparisonDateOrderTypeText(String orderType) {
        if (orderType.equals("Delivery")) {
            assertElementText(comparisonDateDeliveryOrderType, orderType);
        } else if (orderType.equals("Dine In")) {
            assertElementText(comparisonDateDineInOrderType, orderType);
        } else if (orderType.equals("Pick Up")) {
            assertElementText(comparisonDatePickUpOrderType, orderType);
        } else if (orderType.equals("Drive Thru")) {
            assertElementText(comparisonDateDriveThruOrderType, orderType);
        }
        return this;
    }

    public EnhancedDashboardPage assertThatFilterIconIsVisible() {
        assertVisibilityOfElement(filterIcon);
        return this;
    }

    public EnhancedDashboardPage assertThatOrderCardIsVisible() {
        assertVisibilityOfElement(ordersCard);
        return this;
    }

    public EnhancedDashboardPage assertThatNetSalesCardIsVisible() {
        assertVisibilityOfElement(netSalesCard);
        return this;
    }

    public EnhancedDashboardPage assertThatNetPaymentsCardIsVisible() {
        assertVisibilityOfElement(netPaymentsCard);
        return this;
    }

    public EnhancedDashboardPage assertThatReturnAmountCardIsVisible() {
        assertVisibilityOfElement(returnAmountCard);
        return this;
    }

    public EnhancedDashboardPage assertThatDiscountAmountCardIsVisible() {
        assertVisibilityOfElement(discountAmountCard);
        return this;
    }


    public EnhancedDashboardPage assertThatCompareToggleIsVisible() {
        assertVisibilityOfElement(compareToggle);
        return this;
    }

    public EnhancedDashboardPage assertThatOrdersPercentageIsVisible() {
        assertVisibilityOfElement(ordersPercentage);
        return this;
    }

    public EnhancedDashboardPage assertThatNetSalesPercentageIsVisible() {
        assertVisibilityOfElement(netSalesPercentage);
        return this;
    }

    public EnhancedDashboardPage assertThatNetPaymentsPercentageIsVisible() {
        assertVisibilityOfElement(netPaymentsPercentage);
        return this;
    }

    public EnhancedDashboardPage assertThatReturnAmountPercentageIsVisible() {
        assertVisibilityOfElement(returnAmountPercentage);
        return this;
    }

    public EnhancedDashboardPage assertThatDiscountAmountPercentageIsVisible() {
        assertVisibilityOfElement(discountAmountPercentage);
        return this;
    }

    public EnhancedDashboardPage assertOrdersMainComparisonDatesAndTotalsAreVisible() {
        assertVisibilityOfElement(ordersMainComparisonDatesAndTotals);
        return this;
    }

    public EnhancedDashboardPage assertNetSalesMainComparisonDatesAndTotalsAreVisible() {
        assertVisibilityOfElement(netSalesMainComparisonDatesAndTotals);
        return this;
    }

    public EnhancedDashboardPage assertNetPaymentsMainComparisonDatesAndTotalsAreVisible() {
        assertVisibilityOfElement(netPaymentsMainComparisonDatesAndTotals);
        return this;
    }

    public EnhancedDashboardPage assertReturnAmountMainComparisonDatesAndTotalsAreVisible() {
        assertVisibilityOfElement(returnAmountMainComparisonDatesAndTotals);
        return this;
    }

    public EnhancedDashboardPage assertDiscountAmountMainComparisonDatesAndTotalsAreVisible() {
        assertVisibilityOfElement(discountAmountMainComparisonDatesAndTotals);
        return this;
    }


    public EnhancedDashboardPage assertThatCompareToggleIsChecked() {
        assertElementAttributeEqualText(compareToggleIsChecked, "class", JsonReader.jsonReader("compareLabelIsCheckedClassValue", "elementAttributesValueText", "Dashboard.json"));
        return this;
    }

    public EnhancedDashboardPage assertThatCompareToggleIsUnChecked() {
        assertElementAttributeEqualText(compareToggleIsChecked, "class", JsonReader.jsonReader("compareLabelUncheckedClassValue", "elementAttributesValueText", "Dashboard.json"));
        return this;
    }

    public EnhancedDashboardPage assertThatOrderTypeIsStruckThrough(String orderType) {
        String lineThroughClass = JsonReader.jsonReader("orderTypeLineThroughClass", "elementAttributesValueText", "Dashboard.json");
        if (orderType.equals("Delivery")) {
            assertElementContainClass(mainDateDeliveryOrderType, lineThroughClass);
            assertElementContainClass(comparisonDateDeliveryOrderType, lineThroughClass);
        } else if (orderType.equals("Dine In")) {
            assertElementContainClass(mainDateDineInOrderType, lineThroughClass);
            assertElementContainClass(comparisonDateDineInOrderType, lineThroughClass);
        } else if (orderType.equals("Pick Up")) {
            assertElementContainClass(mainDatePickUpOrderType, lineThroughClass);
            assertElementContainClass(comparisonDatePickUpOrderType, lineThroughClass);
        } else if (orderType.equals("Drive Thru")) {
            assertElementContainClass(mainDateDriveThruOrderType, lineThroughClass);
            assertElementContainClass(comparisonDateDriveThruOrderType, lineThroughClass);
        }
        return this;
    }

}
