package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Direction;
import utils.SwipeUtils;

import java.time.Duration;
import java.util.List;

public class ContactListScreen extends BaseScreen implements SwipeUtils {
    public ContactListScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    WebElement noContacts;
    @AndroidFindBy(accessibility = "add")
    WebElement btnPlus;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Contact was added!']")
    WebElement messageContactWasAdded;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Contact was updated!']")
    WebElement messageContactWasUpdated;
    @AndroidFindBy(id = "android:id/button1")
    WebElement btnYes;
    @AndroidFindBy(xpath = "(//*[@resource-id='com.sheygam.contactapp:id/rowContainer'])")
    List<WebElement> contactListScreen;

    public boolean validateTextInContactListScreenAfterRegistration
            (String text, int time) {
        return isTextInElementPresent(noContacts, text, time);
    }

    public boolean isBtnPlusPresent(){
        return isElementPresent(btnPlus, 5);
    }

    public void clickBtnPlus() {
        btnPlus.click();
    }

    public boolean isTextInMessageContactWasAddedPresent(String text, int time){
        return isTextInElementPresent(messageContactWasAdded, text, time);
    }

    public boolean isTextInMessageContactWasUpdatedPresent(String text, int time){
        return isTextInElementPresent(messageContactWasUpdated, text, time);
    }

    public void deleteContactMiddle(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOf(btnPlus));
        swipeScreen(driver, Direction.RIGHT);
        btnYes.click();
    }
    public void deleteFirstContact(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOf(btnPlus));
        swipeInsideElement(driver, contactListScreen.get(0), Direction.RIGHT);
        btnYes.click();
    }

    public void editFirstContact(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOf(btnPlus));
        swipeInsideElement(driver, contactListScreen.get(0), Direction.LEFT);
    }
}
