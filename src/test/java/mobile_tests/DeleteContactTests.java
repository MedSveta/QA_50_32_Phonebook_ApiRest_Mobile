package mobile_tests;

import dto.Contact;
import dto.ContactsDto;
import dto.TokenDto;
import dto.User;
import io.restassured.response.Response;
import manager.AuthenticationController;
import manager.ContactController;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.LoginRegistrationScreen;
import utils.BaseApi;
import utils.ContactFactory;

import static utils.PropertiesReader.getProperty;

public class DeleteContactTests extends TestBase {
    LoginRegistrationScreen loginRegistrationScreen;
    ContactListScreen contactListScreen;
    TokenDto tokenDto;
    ContactsDto contactsDtoBeforeDelete, contactsDtoAfterDelete;

    @BeforeMethod
    public void login() {
//        User user = new User(getProperty("base.properties", "login"),
//                getProperty("base.properties", "password"));
        User user = new User("tesla@yandex.il","Nobug@$#^&*!1");
        Contact contact = ContactFactory.positiveContact();
        tokenDto = AuthenticationController.requestRegLogin(user, BaseApi.LOGIN_URL)
                .as(TokenDto.class);
        Response response = ContactController.requestGetAllUserContacts(tokenDto.getToken());
        System.out.println(response.getStatusLine());
        if(response.getStatusCode() == 200) {
            contactsDtoBeforeDelete = response.as(ContactsDto.class);
            if(contactsDtoBeforeDelete.getContacts().isEmpty()){
                ContactController.requestAddNewContact(contact, tokenDto.getToken());
                contactsDtoBeforeDelete = ContactController.requestGetAllUserContacts
                        (tokenDto.getToken()).as(ContactsDto.class);
            }
        }
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
    }

    @Test
    public void deleteContactPositiveTest() {
        int sizeBeforeDelete = contactsDtoBeforeDelete.getContacts().size();
        contactListScreen.deleteContactMiddle();
        int sizeAfterDelete = ContactController
                .requestGetAllUserContacts(tokenDto.getToken())
                .as(ContactsDto.class).getContacts().size();
        System.out.println(sizeBeforeDelete+" - "+ sizeAfterDelete);
        Assert.assertEquals(sizeAfterDelete, sizeBeforeDelete-1);
    }

    @Test
    public void deleteFirstContactPositiveTest() {
        int sizeBeforeDelete = contactsDtoBeforeDelete.getContacts().size();
        contactListScreen.deleteFirstContact();
        int sizeAfterDelete = ContactController
                .requestGetAllUserContacts(tokenDto.getToken())
                .as(ContactsDto.class).getContacts().size();
        System.out.println(sizeBeforeDelete+" - "+ sizeAfterDelete);
        Assert.assertEquals(sizeAfterDelete, sizeBeforeDelete-1);
    }
}
