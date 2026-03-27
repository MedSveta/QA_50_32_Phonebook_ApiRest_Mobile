package screens;

import dto.Contact;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputName")
    WebElement inputName;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputLastName")
    WebElement inputLastName;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputEmail")
    WebElement inputEmail;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputPhone")
    WebElement inputPhone;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputAddress")
    WebElement inputAddress;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputDesc")
    WebElement inputDescription;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/createBtn")
    WebElement btnCreate;

    public void typeContactForm(Contact contact) {
        inputName.sendKeys(contact.getName());
        inputLastName.sendKeys(contact.getLastName());
        inputEmail.sendKeys(contact.getEmail());
        inputPhone.sendKeys(contact.getPhone());
        inputAddress.sendKeys(contact.getAddress());
        inputDescription.sendKeys(contact.getDescription());
    }

    public void clickBtnCreate(){
        btnCreate.click();
    }
}
