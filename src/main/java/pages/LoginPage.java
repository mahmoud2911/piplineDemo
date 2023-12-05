package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.JsonReader;
import utils.Wait;

public class LoginPage extends PageBase {
    final private By loginPageLabel = By.linkText("Login To Your Account");
    final private By accountNumberField = By.id("business_ref");
    final private By emailField = By.name("email");
    final private By passwordField = By.name("password");
    final private By loginButton = By.cssSelector("button");
    final private By consoleHome = By.id("sidebar_dashboard");
    final private By googleEmailField = By.id("identifierId");
    final private By googlePasswordField = By.name("Passwd");
    final private By nextButton = By.xpath("(//span[@class='VfPpkd-vQzf8d'])[2]");
    Wait wait = new Wait(driver);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * function to fill console login form
     */
    public LoginPage addLoginCredentials() {
        setElementText(accountNumberField, JsonReader.jsonReader("accountNumber", "LoginCredentials.json"));
        setElementText(emailField, JsonReader.jsonReader("email", "LoginCredentials.json"));
        setElementText(passwordField, JsonReader.jsonReader("password", "LoginCredentials.json"));
        return this;
    }

    public LoginPage clickOnLoginButton() {
        clickOnElement(loginButton);
        wait.waitInVisibilityOfProgressBar(50);
        return this;
    }

    /**
     * function to log in to the console
     *
     * @param user parameter identify specific user credentials from the Login.json file
     */
    public LoginPage loginToConsole(String user) {
        enterFoodicsAccount(user);
        setElementText(accountNumberField, JsonReader.jsonReader("accountNumber", user, "Login.json"));
        setElementText(emailField, JsonReader.jsonReader("email", user, "Login.json"));
        setElementText(passwordField, JsonReader.jsonReader("password", user, "Login.json"));
        clickOnLoginButton();
        return this;
    }

    private void enterFoodicsAccount(String user) {
        setElementText(googleEmailField, JsonReader.jsonReader("email", user, "Login.json"));
        clickOnElement(nextButton);
        setElementText(googlePasswordField, JsonReader.jsonReader("googlePass", user, "Login.json"));
        clickOnElement(nextButton);
    }

    public LoginPage assertThatLoginPageIsOpen() {
        assertElementText(loginPageLabel, JsonReader.jsonReader("loginFormTitle", "text", "Login.json"));
        return this;
    }

    public LoginPage assertThatUserIsLoggedIn() {
        assertVisibilityOfElement(consoleHome);
        assertCurrentPageUrl(JsonReader.jsonReader("dashboardUrl", "urls", "Login.json"));
        return this;
    }

    public By demo(String elementText) {
        return By.linkText(elementText);
    }

    public LoginPage clickOnSalesOption(String elementText) {
        clickOnElement(By.linkText(elementText));
        return this;
    }
}
