package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import utils.JsonReader;
import utils.Wait;

public class MainDashboardPage extends PageBase {
    final private By dashboardPageLink = By.cssSelector("#main > div > div > div.flex.items-center.mb-10 > div > div > div.px-8.lg\\:px-16 > div > a.mr-8.whitespace-nowrap.border-b-2.border-primary.text-primary");
    final private By searchField = By.name("search-q");
    final private By welcomeMessage = By.tagName("h1");
    final private By dashboardMenuItem = By.id("sidebar_dashboard");
    final private By customersLink = By.cssSelector("#sidebar_customers");
    final private By reportsLink = By.partialLinkText("Reports");
    final private By manageLink = By.id("sidebar_manage");
    final private By menuButton = By.id("sidebar_menu");
    final private By inventoryLink = By.cssSelector("#sidebar-body > ul > li:nth-child(6)");
    final private By orders = By.xpath("//a[@href='/orders']");
    final private By reportsActive = By.className("active");
    final private By usernameEmail = By.xpath("(//a[contains(@class,'w-full block')])[2]");
    final private By profileIcon = By.xpath("//*[@id=\"header\"]/div/div[2]/div[3]/div[1]/button");
    final private By myAppsIcon = By.xpath("//button[contains(@class,'me-4 flex')]");
    final private By notificationIcon = By.xpath("//div[contains(@class,'me-4 inline-flex')]");
    Wait wait = new Wait(driver);


    public MainDashboardPage(WebDriver driver) {
        super(driver);
    }

    public MainDashboardPage clickOnDashboardLink() {
        wait.waitInVisibilityOfProgressBar(50);
        clickOnElement(dashboardMenuItem);
        return this;
    }

    public MainDashboardPage clickOnCustomersLink() {
        clickOnElement(customersLink);
        return this;
    }

    public MainDashboardPage clickOnReportsLink() {
        clickOnElement(reportsLink);
        return this;
    }

    public MainDashboardPage clickOnManage() {
        clickOnElement(manageLink);
        return this;
    }

    public MainDashboardPage clickOnTheMenu() {
        clickOnElement(menuButton);
        return this;
    }

    public MainDashboardPage clickOnInventory() {
        clickOnElement(inventoryLink);
        return this;
    }

    public MainDashboardPage clickOnProfileIcon() {
        clickOnElement(profileIcon);
        return this;
    }

    public MainDashboardPage clickOnUsernameEmail() {
        clickOnElement(usernameEmail);
        return this;
    }

    public MainDashboardPage openProfilePage() {
        clickOnProfileIcon();
        clickOnUsernameEmail();
        return this;
    }

    public MainDashboardPage clickOnOrders() {
        clickOnElement(orders);
        return this;
    }

    public MainDashboardPage assertDashboardPageUrl() {
        assertCurrentPageUrl(JsonReader.jsonReader("dashboardPageUrl", "urls", "Dashboard.json"));
        return this;
    }

    public MainDashboardPage assertDashboardMenuItemText(String textLocalization) {
        assertElementText(dashboardMenuItem, JsonReader.jsonReader("dashboardMainMenu", textLocalization, "Dashboard.json"));
        return this;
    }

    public MainDashboardPage assertOrdersMenuItemText(String textLocalization) {
        assertElementText(orders, JsonReader.jsonReader("ordersMainMenu", textLocalization, "Dashboard.json"));
        return this;
    }

    public MainDashboardPage assertReportsMenuItemText(String textLocalization) {
        assertElementText(reportsLink, JsonReader.jsonReader("reportsMainMenu", textLocalization, "Dashboard.json"));
        return this;
    }

    public MainDashboardPage assertThatProfileIconIsVisible() {
        assertVisibilityOfElement(profileIcon);
        return this;
    }

    public MainDashboardPage assertThatMyAppsIconIsVisible() {
        assertVisibilityOfElement(myAppsIcon);
        return this;
    }

    public MainDashboardPage assertThatNotificationIconIsVisible() {
        assertVisibilityOfElement(notificationIcon);
        return this;
    }

    public MainDashboardPage assertThatSearchFieldIsVisible() {
        assertVisibilityOfElement(searchField);
        return this;
    }

    public MainDashboardPage assertThatWelcomeMessageIsVisible() {
        assertVisibilityOfElement(welcomeMessage);
        return this;
    }
}