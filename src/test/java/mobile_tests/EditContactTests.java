package mobile_tests;

import dto.Contact;
import dto.TokenDto;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.EditContactScreen;
import screens.LoginRegistrationScreen;

import static utils.ContactFactory.*;

import static utils.PropertiesReader.getProperty;

public class EditContactTests extends TestBase {
    LoginRegistrationScreen loginRegistrationScreen;
    ContactListScreen contactListScreen;
    TokenDto tokenDto;
    EditContactScreen editContactScreen;
    SoftAssert softAssert = new SoftAssert();


    @BeforeMethod
    public void login() {
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
    }

    @Test
    public void editFirstContactPositiveTest() {
        Contact contact = positiveContact();
        contactListScreen.editFirstContact();
        editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(contact);
        editContactScreen.clickBtnUpdate();
        Assert.assertTrue(contactListScreen
                .isTextInMessageContactWasUpdatedPresent("Contact was updated!", 5));
    }
}
