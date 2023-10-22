package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import utils.JsonReader;
import utils.Wait;

public class CommonNormalAndEnhancedDashboard extends PageBase {
    final private By dashboardPageLink = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]");
    final private By dayView = By.xpath("(//div[contains(@class,'py-1 px-2')])[1]");
    final private By weekView = By.xpath("(//div[contains(@class,'py-1 px-2')])[2]");
    final private By monthView = By.xpath("(//div[contains(@class,'py-1 px-2')])[3]");
    final private By viewOrdersReport = By.xpath("(//span[contains(@class,'text-secondary-300 text-callout')])[1]");
    final private By ordersCanvasChart = By.xpath("(//div[contains(@class,'px-2 pb-5')]//canvas)[1]");
    final private By viewNetSalesReport = By.xpath("(//span[contains(@class,'text-secondary-300 text-callout')])[2]");
    final private By netSalesCanvasChart = By.xpath("(//div[contains(@class,'px-2 pb-5')]//canvas)[2]");
    final private By viewNetPaymentsReport = By.xpath("(//span[contains(@class,'text-secondary-300 text-callout')])[3]");
    final private By netPaymentsCanvasChart = By.xpath("(//div[contains(@class,'px-2 pb-5')]//canvas)[3]");
    final private By amountOfReturn = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/h3[1]/span[1]");
    final private By returnAmountCardLabel = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/span[1]");
    final private By viewReturnAmountReport = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/span[2]");
    final private By returnAmountCanvasChart = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/canvas[1]");
    final private By amountOfDiscount = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/h3[1]/span[1]");
    final private By discountAmountCardLabel = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/div[1]/span[1]");
    final private By viewDiscountAmountReport = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/div[1]/span[2]");
    final private By discountAmountCanvasChart = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[2]/canvas[1]");
    final private By orderTypesCard = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]");
    final private By orderTypesCardLabel = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]/div[1]/div[1]/div[1]/span[1]");
    final private By viewOrderTypesReport = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]/div[1]/div[1]/div[1]/span[2]");
    final private By orderTypesCanvasChart = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]/div[1]/div[2]/canvas[1]");
    final private By hourlySalesCard = By.xpath("//div[contains(@class,'card block')]");
    final private By hourlySalesCardLabel = By.xpath("//div[contains(@class,'card block')]//h3[1]");
    final private By hourlySalesCanvasChart = By.xpath("//div[@class='relative']//canvas[1]");
    final private By topProductByNetSalesCard = By.xpath("(//div[contains(@class,'w-full xl:w-1/3')])[1]");
    final private By topProductByNetSalesCardLabel = By.xpath("(//div[contains(@class,'text-black font-semibold')])[1]");
    final private By viewTopProductByNetSalesReport = By.xpath("(//div[contains(@class,'text-black font-semibold')]/following-sibling::span)[1]");
    final private By topProductByNetSaleDescription = By.xpath("(//div[@class='px-8 my-3']//span)[1]");
    final private By topProductByNetSaleCanvas = By.xpath("(//div[contains(@class,'flex-col p-4')]//canvas)[1]");
    final private By topPaymentsCard = By.xpath("(//div[contains(@class,'w-full xl:w-1/3')])[2]");
    final private By topPaymentsCardLabel = By.xpath("(//div[contains(@class,'text-black font-semibold')])[2]");
    final private By viewTopPaymentsReport = By.xpath("(//div[contains(@class,'text-black font-semibold')]/following-sibling::span)[2]");
    final private By topPaymentsCardDescription = By.xpath("(//div[@class='px-8 my-3']//div)[2]");
    final private By topPaymentsCardCanvas = By.xpath("(//div[contains(@class,'flex-col p-4')]//canvas)[2]");
    final private By topBranchesByNetSalesCard = By.xpath("(//div[contains(@class,'w-full xl:w-1/3')])[3]");
    final private By topBranchesByNetSalesCardLabel = By.xpath("(//div[contains(@class,'text-black font-semibold')])[3]");
    final private By viewTopBranchesByNetSalesReport = By.xpath("(//div[contains(@class,'text-black font-semibold')]/following-sibling::span)[3]");
    final private By topBranchesByNetSalesDescription = By.xpath("(//div[@class='px-8 my-3']//div)[3]");
    final private By topBranchesByNetSalesCanvas = By.xpath("(//div[contains(@class,'flex-col p-4')]//canvas)[3]");
    final private By branchFilterLabel = By.xpath("//label[@class='text-footnote']//span[1]");
    final private By selectByFilterLabel = By.xpath("//div[@class='text-footnote']//span[1]");
    final private By tagsBranchesSwitchLink = By.xpath("//a[contains(@class,'text-secondary-300 text-callout')]");
    final private By branchFilterField = By.xpath("(//div[@class='relative font-normal']//div)[2]");
    final private By chartLoadingIcon = By.xpath("//div[contains(@class,'inset-0 absolute')]");
    final private By canvasCharts = By.xpath("//div[contains(@class,'px-2 pb-5')]//canvas");

    Wait wait = new Wait(driver);
    public CommonNormalAndEnhancedDashboard(WebDriver driver) {
        super(driver);
    }
    public CommonNormalAndEnhancedDashboard clickOnDayView() {
        clickOnElement(dayView);
        wait.waitInVisibilityOfProgressBar(50);
        return this;
    }

    public CommonNormalAndEnhancedDashboard clickOnWeekView() {
        clickOnElement(weekView);
        wait.waitInVisibilityOfProgressBar(50);
        return this;
    }

    public CommonNormalAndEnhancedDashboard clickOnMonthView() {
        clickOnElement(monthView);
        wait.waitInVisibilityOfProgressBar(50);
        return this;
    }
    public CommonNormalAndEnhancedDashboard viewOrdersReport() {
        clickOnElement(viewOrdersReport);
        return this;
    }

    public CommonNormalAndEnhancedDashboard viewNetSaleReport() {
        clickOnElement(viewNetSalesReport);
        return this;
    }

    public CommonNormalAndEnhancedDashboard viewNetPaymentsReport() {
        clickOnElement(viewNetPaymentsReport);
        return this;
    }

    public CommonNormalAndEnhancedDashboard viewReturnAmountReport() {
        clickOnElement(viewReturnAmountReport);
        return this;
    }

    public CommonNormalAndEnhancedDashboard viewDiscountAmountReport() {
        clickOnElement(viewDiscountAmountReport);
        return this;
    }
    public CommonNormalAndEnhancedDashboard viewOrderTypesReport() {
        clickOnElement(viewOrderTypesReport);
        return this;
    }
    public CommonNormalAndEnhancedDashboard viewTopProductByNetSalesReport() {
        clickOnElement(viewTopProductByNetSalesReport);
        return this;
    }

    public CommonNormalAndEnhancedDashboard viewTopPaymentsReport() {
        clickOnElement(viewTopPaymentsReport);
        return this;
    }

    public CommonNormalAndEnhancedDashboard viewTopBranchesByNetSalesReport() {
        clickOnElement(viewTopBranchesByNetSalesReport);
        return this;
    }

    public CommonNormalAndEnhancedDashboard clickTagsBranchesSwitchLink() {
        clickOnElement(tagsBranchesSwitchLink);
        return this;
    }

    public String getReturnAmount() {
        return getElementText(amountOfReturn);
    }

    public String getDiscountAmount() {
        return getElementText(amountOfDiscount);
    }
    public CommonNormalAndEnhancedDashboard setViewInJson(String view){
        JsonReader.updateJsonKey("Dashboard.json","view",view);
        return this;
    }
    public CommonNormalAndEnhancedDashboard setDiscountAmountInJson(){
        JsonReader.updateJsonKey("Dashboard.json","discountAmount",getDiscountAmount());
        return this;
    }
    public CommonNormalAndEnhancedDashboard setReturnAmountInJson(){
        JsonReader.updateJsonKey("Dashboard.json","returnAmount",getReturnAmount());
        return this;
    }
    public String getViewFromJson(){
        return JsonReader.jsonReader("view","dashboardData","Dashboard.json");
    }
    public String getOrdersCountFromJson(){
        return JsonReader.jsonReader("ordersCount","dashboardData","Dashboard.json");
    }
    public String getNetSalesAmountFromJson(){
        return JsonReader.jsonReader("netSalesAmount","dashboardData","Dashboard.json");
    }
    public String getNetSalesAmountWithoutCurrencyFromJson(){
        return JsonReader.jsonReader("netSalesAmountCurrency","dashboardData","Dashboard.json");
    }
    public String getNetPaymentsAmountFromJson(){
        return JsonReader.jsonReader("netPaymentsAmount","dashboardData","Dashboard.json");
    }
    public String getReturnAmountFromJson(){
        return JsonReader.jsonReader("returnAmount","dashboardData","Dashboard.json");
    }
    public String getDiscountAmountFromJson(){
        return JsonReader.jsonReader("discountAmount","dashboardData","Dashboard.json");
    }


    public CommonNormalAndEnhancedDashboard assertDashboardPageTitle(String textLocalization) {
        assertCurrentPageTitle(JsonReader.jsonReader("dashboardPageTitle", textLocalization, "Dashboard.json"));
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertDayViewText(String textLocalization) {
        assertElementText(dayView, JsonReader.jsonReader("dayView", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertWeekViewText(String textLocalization) {
        assertElementText(weekView, JsonReader.jsonReader("weekView", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertMonthViewText(String textLocalization) {
        assertElementText(monthView, JsonReader.jsonReader("monthView", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertReturnAmountCardLabelText(String textLocalization) {
        assertElementContainsText(returnAmountCardLabel, JsonReader.jsonReader("returnAmountCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertDiscountAmountCardLabelText(String textLocalization) {
        assertElementContainsText(discountAmountCardLabel, JsonReader.jsonReader("discountAmountCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertOrderTypesCardLabelText(String textLocalization) {
        assertElementContainsText(orderTypesCardLabel, JsonReader.jsonReader("orderTypesCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertViewOrdersReportText(String textLocalization) {
        assertElementText(viewOrdersReport, JsonReader.jsonReader("viewReportText", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertViewNetSalesReportText(String textLocalization) {
        assertElementText(viewNetSalesReport, JsonReader.jsonReader("viewReportText", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertViewNetPaymentsReportText(String textLocalization) {
        assertElementText(viewNetPaymentsReport, JsonReader.jsonReader("viewReportText", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertViewReturnReportText(String textLocalization) {
        assertElementText(viewReturnAmountReport, JsonReader.jsonReader("viewReportText", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertViewDiscountReportText(String textLocalization) {
        assertElementText(viewDiscountAmountReport, JsonReader.jsonReader("viewReportText", textLocalization, "Dashboard.json"));
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertViewOrderTypesReportText(String textLocalization) {
        assertElementText(viewOrderTypesReport, JsonReader.jsonReader("viewReportText", textLocalization, "Dashboard.json"));
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertHourlySalesCardLabelText(String textLocalization) {
        assertElementText(hourlySalesCardLabel, JsonReader.jsonReader("hourlySalesCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertTopProductByNetSalesCardLabelText(String textLocalization) {
        assertElementContainsText(topProductByNetSalesCardLabel, JsonReader.jsonReader("topProductByNetSaleCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertTopPaymentCardLabelText(String textLocalization) {
        assertElementContainsText(topPaymentsCardLabel, JsonReader.jsonReader("topPaymentCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertTopBranchesByNetSalesLabelText(String textLocalization) {
        assertElementContainsText(topBranchesByNetSalesCardLabel, JsonReader.jsonReader("topBranchesByNetSalesCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertViewTopProductByNetSalesReportText(String textLocalization) {
        assertElementText(viewTopProductByNetSalesReport, JsonReader.jsonReader("viewReportText", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertViewTopPaymentsReportText(String textLocalization) {
        assertElementText(viewTopPaymentsReport, JsonReader.jsonReader("viewReportText", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertViewTopBranchesByNetSalesReportText(String textLocalization) {
        assertElementText(viewTopBranchesByNetSalesReport, JsonReader.jsonReader("viewReportText", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertSelectByFilterLabelText(String textLocalization) {
        assertElementText(selectByFilterLabel, JsonReader.jsonReader("selectByFilterLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertBranchFilterLabelText(String textLocalization) {
        assertElementText(branchFilterLabel, JsonReader.jsonReader("branchFilterLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertTagsLinkText(String textLocalization) {
        assertElementText(tagsBranchesSwitchLink, JsonReader.jsonReader("tagsLinkText", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertBranchesLinkText(String textLocalization) {
        assertElementText(tagsBranchesSwitchLink, JsonReader.jsonReader("branchesLinkText", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertTopBranchesByNetSalesDescriptionText(String textLocalization) {
        assertElementText(topBranchesByNetSalesDescription, JsonReader.jsonReader("topBranchesByNetSalesCardDescription", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertTopPaymentsCardDescriptionText(String textLocalization) {
        assertElementText(topPaymentsCardDescription, JsonReader.jsonReader("topPaymentCardDescription", textLocalization, "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertTopProductsByNetSalesDescriptionText(String textLocalization) {
        assertElementText(topProductByNetSaleDescription, JsonReader.jsonReader("topProductByNetSaleCardDescription", textLocalization, "Dashboard.json"));
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertVisibilityOfCanvasCharts() {
        wait.waitVisibilityOfElements(canvasCharts,50);
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertVisibilityOfTopBranchesByNetSalesCanvas() {
        assertVisibilityOfElement(topBranchesByNetSalesCanvas);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertVisibilityOfTopProductsByNetSalesCanvas() {
        assertVisibilityOfElement(topProductByNetSaleCanvas);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertVisibilityOfTopPaymentsCardCanvas() {
        assertVisibilityOfElement(topPaymentsCardCanvas);
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertThatOrderCanvasChartIsVisible() {
        assertVisibilityOfElement(ordersCanvasChart);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertThatNetSalesCanvasChardIsVisible() {
        assertVisibilityOfElement(netSalesCanvasChart);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertThatNetPaymentsCanvasChartIsVisible() {
        assertVisibilityOfElement(netPaymentsCanvasChart);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertThatReturnAmountCanvasChartIsVisible() {
        assertVisibilityOfElement(returnAmountCanvasChart);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertThatDiscountAmountCanvasChartIsVisible() {
        assertVisibilityOfElement(discountAmountCanvasChart);
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertThatOrderTypesCanvasChartIsVisible() {
        assertVisibilityOfElement(orderTypesCanvasChart);
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertThatOrderTypesCardIsVisible() {
        assertVisibilityOfElement(orderTypesCard);
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertThatHourlySalesCardIsVisible() {
        assertVisibilityOfElement(hourlySalesCard);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertThatHourlySaleCanvasChartIsVisible() {
        assertVisibilityOfElement(hourlySalesCanvasChart);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertThatTopProductByNetSalesCardIsVisible() {
        assertVisibilityOfElement(topProductByNetSalesCard);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertThatTopPaymentsCardsVisible() {
        assertVisibilityOfElement(topPaymentsCard);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertThatTopBranchesByNetSalesCardIsVisible() {
        assertVisibilityOfElement(topBranchesByNetSalesCard);
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertThatDashboardIsOpened() {
        assertVisibilityOfElement(dashboardPageLink);
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertThatBranchFilterFieldIsVisible() {
        assertVisibilityOfElement(branchFilterField);
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertInVisibilityOfChartsLoadingIcon() {
        assertElementDoesNotExist(chartLoadingIcon);
        return this;
    }
    public CommonNormalAndEnhancedDashboard assertDayViewIsHighlighted() {
        assertElementContainClass(dayView, JsonReader.jsonReader("viewHighlightedClasses", "elementAttributesValueText", "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertWeekViewIsHighlighted() {
        assertElementContainClass(weekView, JsonReader.jsonReader("viewHighlightedClasses", "elementAttributesValueText", "Dashboard.json"));
        return this;
    }

    public CommonNormalAndEnhancedDashboard assertMonthViewIsHighlighted() {
        assertElementContainClass(monthView, JsonReader.jsonReader("viewHighlightedClasses", "elementAttributesValueText", "Dashboard.json"));
        return this;
    }


}
