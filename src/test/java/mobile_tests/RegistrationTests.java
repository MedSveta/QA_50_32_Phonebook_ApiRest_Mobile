package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.ErrorScreen;
import screens.LoginRegistrationScreen;
import screens.SplashScreen;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;

public class RegistrationTests extends TestBase {
    LoginRegistrationScreen loginRegistrationScreen;

    @BeforeMethod
    public void openAuthScreen() {
        new SplashScreen(driver);
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
    }

    @Test
    public void registrationPositiveTest() {
        User user = positiveUser();
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ContactListScreen(driver).validateTextInContactListScreenAfterRegistration
                ("No Contacts. Add One more!", 5));
    }

    @Test
    public void registrationNegative_EmptyEmailTest() {
        User user = positiveUser();
        user.setUsername("");
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver).validateTextInError
                ("username=must not be blank", 5));
    }

    @Test
    public void registrationNegative_WithSpaceEmailTest() {
        User user = positiveUser();
        user.setUsername(" ");
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
//        Assert.assertTrue(new ErrorScreen(driver).validateTextInCrashScreen
//                ("Open app again", 5));
        Assert.assertTrue(new ErrorScreen(driver).isAppStopDisplay());
    }

    @Test
    public void registrationNegative_EmptyFieldsTest() {
        User user = new User("", "");
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver).isAppStopDisplay());
    }

    @Test
    public void registrationNegative_AlreadyExistsUser_Test(){
        User user  =  new User(getProperty("base.properties",
                "login"), getProperty("base.properties",
                "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver).validateTextInError
                ("User already exists", 5));
    }
}
