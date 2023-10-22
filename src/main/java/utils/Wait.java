package utils;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait extends PageBase {
    public Wait(WebDriver driver) {
        super(driver);
    }

    private WebDriverWait explicitWait(int Time) {
        return new WebDriverWait(driver.getDriver(), Duration.ofSeconds(Time));
    }

    public Wait waitVisibilityOfElements(By element, int Time) {
        WebDriverWait wait = explicitWait(Time);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(driver.getDriver().findElements(element)));
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getMessage());
        }
        return this;
    }

    public Wait waitInVisibilityOfElements(By element, int Time) {
        WebDriverWait wait = explicitWait(Time);
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(driver.getDriver().findElements(element)));
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getMessage());
        }
        return this;
    }

    public Wait waitVisibilityOfElement(By element, int Time) {
        WebDriverWait wait = explicitWait(Time);
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.getDriver().findElement(element)));
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getMessage());
        }

        return this;
    }

    public Wait waitInVisibilityOfElement(By element, int Time) {
        WebDriverWait wait = explicitWait(Time);
        try {
            wait.until(ExpectedConditions.invisibilityOf(driver.getDriver().findElement(element)));
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getMessage());
        }
        return this;
    }

    public Wait waitInVisibilityOfProgressBar(int Time) {
        WebDriverWait wait = explicitWait(Time);
        try {
            wait.until(ExpectedConditions.invisibilityOf(driver.getDriver().findElement(By.xpath("//*[@id=\"nprogress\"]/div[1]"))));
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getMessage());
        }
        return this;
    }

    public Wait waitToBeClickableElement(By element, int Time) {
        WebDriverWait wait = explicitWait(Time);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }
}
