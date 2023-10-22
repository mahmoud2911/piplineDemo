package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import utils.JsonReader;
import utils.Wait;

public class DashboardPage extends PageBase {
    final private By generalTab = By.xpath("(//a[contains(@class,'me-8 whitespace-nowrap')])[1]");
    final private By branchesTab = By.xpath("(//a[contains(@class,'me-8 whitespace-nowrap')])[2]");
    final private By inventoryTab = By.xpath("(//a[contains(@class,'me-8 whitespace-nowrap')])[3]");
    final private By callCenterTab = By.xpath("(//a[contains(@class,'me-8 whitespace-nowrap')]/following-sibling::a)[3]");
    final private By tryNewEnhancedDashboardLink = By.xpath("//span[@class='underline ms-2']");
    final private By betaIcon = By.xpath("//div[contains(@class,'inline-block bg-positive-500')]");
    final private By filterIcon = By.xpath("//div[contains(@class,'flex flex-wrap')]/following-sibling::button[1]");
    final private By ordersCard = By.xpath("(//div[contains(@class,'w-full 2xl:w-1/4')])[1]");
    final private By ordersCardLabel = By.xpath("(//span[@class='font-semibold text-black'])[1]");
    final private By ordersCount = By.xpath("(//span[@class='text-black'])[1]");
    final private By netSalesCard = By.xpath("(//div[contains(@class,'w-full 2xl:w-1/4')])[2]");
    final private By netSalesCardLabel = By.xpath("(//span[@class='font-semibold text-black'])[2]");
    final private By netSalesAmount = By.xpath("(//span[@class='text-black'])[2]");
    final private By netPaymentsCard = By.xpath("(//div[contains(@class,'w-full 2xl:w-1/4')])[3]");
    final private By netPaymentsCardLabel = By.xpath("(//span[@class='font-semibold text-black'])[3]");
    final private By netPaymentsAmount = By.xpath("(//span[@class='text-black'])[3]");
    final private By returnAmountCard = By.xpath("(//div[contains(@class,'w-full 2xl:w-1/4')]/following-sibling::div)[3]");
    final private By discountAmountCard = By.xpath("//main[@id='main']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[5]");

    Wait wait = new Wait(driver);

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage clickOnTryEnhancedDashboardLink() {
        wait.waitInVisibilityOfProgressBar(50);
        clickOnElement(tryNewEnhancedDashboardLink);
        wait.waitInVisibilityOfProgressBar(50);
        return this;
    }

    public DashboardPage clickOnFilterIcon() {
        clickOnElement(filterIcon);
        return this;
    }

    public String getOrdersNumber() {
        return getElementText(ordersCount);
    }

    public String getNetSalesAmount() {
        return getElementText(netSalesAmount);
    }

    public String getNetSalesAmountWithoutCurrency() {
        return getElementText(netSalesAmount).replaceAll("[^0-9]", "");
    }

    public String getNetPaymentAmount() {
        return getElementText(netPaymentsAmount);
    }

    public DashboardPage setOrdersNumberInJson() {
        JsonReader.updateJsonKey("Dashboard.json", "ordersCount", getOrdersNumber());
        return this;
    }

    public DashboardPage setNetSalesAmountInJson() {
        JsonReader.updateJsonKey("Dashboard.json", "netSalesAmount", getNetSalesAmount());
        return this;
    }

    public DashboardPage setNetSalesAmountWithoutCurrencyInJson() {
        JsonReader.updateJsonKey("Dashboard.json", "netSalesAmountCurrency", getNetSalesAmountWithoutCurrency());
        return this;
    }

    public DashboardPage setNetPaymentsAmountInJson() {
        JsonReader.updateJsonKey("Dashboard.json", "netPaymentsAmount", getNetPaymentAmount());
        return this;
    }

    public DashboardPage assertDashboardPageUrl() {
        assertCurrentPageUrl(JsonReader.jsonReader("dashboardPageUrl", "urls", "Dashboard.json"));
        return this;
    }

    public DashboardPage assertGeneralTabText(String textLocalization) {
        assertElementText(generalTab, JsonReader.jsonReader("generalTab", textLocalization, "Dashboard.json"));
        return this;
    }

    public DashboardPage assertBranchesTabText(String textLocalization) {
        assertElementText(branchesTab, JsonReader.jsonReader("branchesTab", textLocalization, "Dashboard.json"));
        return this;
    }

    public DashboardPage assertInventoryTabText(String textLocalization) {
        assertElementText(inventoryTab, JsonReader.jsonReader("inventoryTab", textLocalization, "Dashboard.json"));
        return this;
    }

    public DashboardPage assertCallCenterTabText(String textLocalization) {
        assertElementText(callCenterTab, JsonReader.jsonReader("callCenterTab", textLocalization, "Dashboard.json"));
        return this;
    }

    public DashboardPage assertTryNewEnhancedDashboardText(String textLocalization) {
        assertElementText(tryNewEnhancedDashboardLink, JsonReader.jsonReader("tryEnhancedDashboardLinkText", textLocalization, "Dashboard.json"));
        return this;
    }

    public DashboardPage assertBetaIconText(String textLocalization) {
        assertElementText(betaIcon, JsonReader.jsonReader("betaIcon", textLocalization, "Dashboard.json"));
        return this;
    }

    public DashboardPage assertOrdersCardLabelText(String textLocalization) {
        assertElementText(ordersCardLabel, JsonReader.jsonReader("ordersCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public DashboardPage assertNetSalesCardLabelText(String textLocalization) {
        assertElementContainsText(netSalesCardLabel, JsonReader.jsonReader("netSalesCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public DashboardPage assertNetPaymentsCardLabelText(String textLocalization) {
        assertElementContainsText(netPaymentsCardLabel, JsonReader.jsonReader("netPaymentsCardLabel", textLocalization, "Dashboard.json"));
        return this;
    }

    public DashboardPage assertGeneralTabIsHighlighted() {
        assertElementContainClass(generalTab, JsonReader.jsonReader("tabHighlightedClasses", "elementAttributesValueText", "Dashboard.json"));
        return this;
    }

    public DashboardPage assertThatFilterIconIsVisible() {
        assertVisibilityOfElement(filterIcon);
        return this;
    }

    public DashboardPage assertThatOrdersCardIsVisible() {
        assertVisibilityOfElement(ordersCard);
        return this;
    }

    public DashboardPage assertThatNetSalesCardIsVisible() {
        assertVisibilityOfElement(netSalesCard);
        return this;
    }

    public DashboardPage assertThatNetPaymentsCardIsVisible() {
        assertVisibilityOfElement(netPaymentsCard);
        return this;
    }

    public DashboardPage assertThatReturnAmountCardIsVisible() {
        assertVisibilityOfElement(returnAmountCard);
        return this;
    }

    public DashboardPage assertThatDiscountAmountCardIsVisible() {
        assertVisibilityOfElement(discountAmountCard);
        return this;
    }
}
