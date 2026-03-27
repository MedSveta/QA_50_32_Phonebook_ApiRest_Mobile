package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ErrorScreen extends BaseScreen {
    public ErrorScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "android:id/message")
    WebElement textError;

    @AndroidFindBy(id = "android:id/aerr_restart")
    WebElement crashScreenBtn;

    @AndroidFindBy(id = "android:id/title_template")
    WebElement appStop;

    public boolean validateTextInError(String text, int time) {
        return isTextInElementPresent(textError, text, time);
    }

    public boolean validateTextInCrashScreen(String text, int time) {
        return isTextInElementPresent(crashScreenBtn, text, time);
    }

    public boolean isAppStopDisplay() {
        return isElementPresent(appStop, 5);
    }
}
