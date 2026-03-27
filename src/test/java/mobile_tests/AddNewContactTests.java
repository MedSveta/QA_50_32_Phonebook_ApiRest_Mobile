package mobile_tests;

import dto.Contact;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.ErrorScreen;
import screens.LoginRegistrationScreen;
import static utils.PropertiesReader.*;
import static utils.ContactFactory.*;

public class AddNewContactTests extends TestBase{
    LoginRegistrationScreen loginRegistrationScreen;
    ContactListScreen contactListScreen;
    AddNewContactScreen addNewContactScreen;

    @BeforeMethod
    public void login(){
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
        contactListScreen.clickBtnPlus();
        addNewContactScreen = new AddNewContactScreen(driver);
    }

    @Test
    public void addNewContactPositiveTest(){
        Contact contact = positiveContact();
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(contactListScreen
                .isTextInMessageContactWasAddedPresent("Contact was added!", 5));
    }

    @Test
    public void addNewContactNegative_WrongLengthPhoneTest(){
        Contact contact = positiveContact();
        contact.setPhone("098765443");
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("min 10, max 15!", 5));
    }

    @Test
    public void addNewContactNegative_EmptyNameTest(){
        Contact contact = positiveContact();
        contact.setName("");
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("not be blank", 5));
    }

    @Test
    public void addNewContactNegative_EmptyLastNameTest(){
        Contact contact = positiveContact();
        contact.setLastName("");
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("not be blank", 5));
    }

    @Test
    public void addNewContactNegative_EmptyAddressTest(){
        Contact contact = positiveContact();
        contact.setAddress("");
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("not be blank", 5));
    }
}
