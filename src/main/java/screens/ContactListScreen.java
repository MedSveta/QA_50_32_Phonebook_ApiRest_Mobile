package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    WebElement noContacts;
    @AndroidFindBy(accessibility = "add")
    WebElement btnPlus;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Contact was added!']")
    WebElement messageContactWasAdded;

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
}
