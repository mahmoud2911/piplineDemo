package common;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import org.openqa.selenium.By;
import utils.JsonReader;
import utils.Wait;

public class Common extends PageBase {


    final private By successMessage = By.xpath("//div[contains(@class,'notification bg-positive-500')]");
    final private By modalBody = By.id("decorated-modal-body");
    final private By modalTitle = By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div/div/div[5]/div/div/div[1]");
    final private By modalCloseButton = By.id("form_close_button");
    final private By modalSaveButton = By.id("form_save_button");
    final private By loadingIcon = By.xpath("//*[@id=\"wrapper\"]/div/div/svg");
    final private By cardHeader = By.className("card-header");

    Wait wait = new Wait(driver);

    public Common(WebDriver driver) {
        super(driver);
    }

    public Common clickOnModalSaveButton() {
        clickOnElement(modalSaveButton);
        wait.waitInVisibilityOfProgressBar(40);
        return this;
    }

    public Common clickOnModalCloseButton() {
        clickOnElement(modalCloseButton);
        return this;
    }

    public Common clickOnModalExportButton() {
        clickOnElement(modalSaveButton);
        return this;
    }

    public Common assertSuccess(String textLocalization) {
        assertElementText(successMessage, JsonReader.jsonReader("savedSuccess", textLocalization, "Common.json"));
        return this;
    }

    public Common assertModalTitle(String textLocalization) {
        assertElementText(modalTitle, JsonReader.jsonReader("filterModalTitle", textLocalization, "Common.json"));
        return this;
    }

    public Common assertModalIsVisible() {
        assertVisibilityOfElement(modalBody);
        return this;
    }

    public Common assertExportButtonText(String textLocalization) {
        assertElementText(modalSaveButton, JsonReader.jsonReader("exportButtonText", textLocalization, "Common.json"));
        return this;
    }

    public Common assertSaveButtonText(String textLocalization) {
        assertElementText(modalSaveButton, JsonReader.jsonReader("saveButtonText", textLocalization, "Common.json"));
        return this;
    }

    public Common assertCloseButtonText(String textLocalization) {
        assertElementText(modalCloseButton, JsonReader.jsonReader("closeButtonText", textLocalization, "Common.json"));
        return this;
    }

    public Common assertVisibilityOfLoadingIcon() {
        wait.waitVisibilityOfElement(loadingIcon, 50);
        return this;
    }

    public Common assertVisibilityOfCardHeader() {
        wait.waitVisibilityOfElement(cardHeader, 50);
        return this;
    }

    public Common assertInvisibilityOfLoadingIcon() {
        wait.waitInVisibilityOfElement(loadingIcon, 50);
        return this;
    }

    public Common assertInvisibilityOfProgressBar() {
        wait.waitInVisibilityOfProgressBar(50);
        return this;
    }

    public Common assertPopUpIsNotExist() {
        assertElementDoesNotExist(modalBody);
        return this;
    }
}
