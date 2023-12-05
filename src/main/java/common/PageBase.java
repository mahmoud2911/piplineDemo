package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.shaft.driver.SHAFT.GUI.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.Wait;

public class PageBase {

    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public PageBase clickOnElement(By element) {
        Wait wait = new Wait(driver);
        wait.waitToBeClickableElement(element, 50);
        return this;
    }

    public PageBase setElementText(By element, String text) {
        driver.element().type(element, text);
        return this;
    }

    public String getElementText(By element) {
        return driver.element().getText(element);
    }

    public WebElement getElementInsideShadowRoot(String elementPath) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (WebElement) jsExecutor.executeScript(elementPath);
    }

    public PageBase selectElementFromDropDown(By element, String text) {
        driver.element().select(element, text);
        return this;
    }

    public PageBase selectElementFromDropDownByIndex(By element, int index) {
        WebElement dropdownElement = driver.getDriver().findElement(element);
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
        return this;
    }

    public PageBase switchToNewOpenedTab() {
        String parentWindowHandle = driver.getDriver().getWindowHandle();
        for (String windowHandle : driver.getDriver().getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.browser().switchToWindow(windowHandle);
                break;
            }
        }
        return this;
    }

    public PageBase assertVisibilityOfElement(By element) {
        driver.element().assertThat(element).isVisible().perform();
        return this;
    }

    public PageBase assertElementText(By element, String text) {
        driver.element().assertThat(element).text().isEqualTo(text).perform();
        return this;
    }

    public PageBase assertElementContainsText(By element, String text) {
        driver.element().assertThat(element).text().contains(text).perform();
        return this;
    }

    public PageBase assertElementTextIgnoreCaseSensitivity(By element, String text) {
        driver.element().assertThat(element).text().equalsIgnoringCaseSensitivity(text).perform();
        return this;
    }

    public PageBase assertCurrentPageUrl(String url) {
        driver.browser().assertThat().url().isEqualTo(url).perform();
        return this;
    }

    public PageBase assertCurrentPageUrlContains(String url) {
        driver.browser().assertThat().url().contains(url).perform();
        return this;
    }

    public PageBase assertCurrentPageTitle(String title) {
        driver.browser().assertThat().title().isEqualTo(title).perform();
        return this;
    }

    public PageBase navigateBack() {
        driver.browser().navigateBack();
        return this;
    }

    public PageBase navigateForward() {
        driver.browser().navigateForward();
        return this;
    }

    public PageBase refreshCurrentPage() {
        driver.browser().refreshCurrentPage();
        return this;
    }

    public String getCurrentUrl() {
        return driver.browser().getCurrentURL();
    }

    public PageBase closeCurrentWindow() {
        driver.browser().closeCurrentWindow();
        return this;
    }

    public String getCurrentWindowTitle() {
        return driver.browser().getCurrentWindowTitle();
    }

    public PageBase assertElementExist(By element) {
        driver.element().assertThat(element).exists().perform();
        return this;
    }

    public PageBase assertElementDoesNotExist(By element) {
        driver.element().assertThat(element).doesNotExist().perform();
        return this;
    }

    public PageBase assertElementIsChecked(By element) {
        driver.element().assertThat(element).isChecked().perform();
        return this;
    }

    public PageBase assertElementIsNotChecked(By element) {
        driver.element().assertThat(element).isNotChecked().perform();
        return this;
    }

    public PageBase assertElementIsEnabled(By element) {
        driver.element().assertThat(element).isEnabled().perform();
        return this;
    }

    public PageBase assertElementIsDisabled(By element) {
        driver.element().assertThat(element).isDisabled().perform();
        return this;
    }

    public PageBase assertElementAttributeEqualText(By element, String attribute, String attributeValue) {
        driver.element().assertThat(element).attribute(attribute).isEqualTo(attributeValue).perform();
        return this;
    }

    public PageBase assertElementContainClass(By element, String classValue) {
        driver.element().assertThat(element).attribute("class").contains(classValue).perform();
        return this;
    }

}
