package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.ErrorScreen;
import screens.LoginRegistrationScreen;
import screens.SplashScreen;
import static utils.PropertiesReader.*;

public class LoginTests extends TestBase{
    LoginRegistrationScreen loginRegistrationScreen;

    @BeforeMethod
    public void openAuthScreen() {
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
    }

    @Test
    public void loginPositiveTest(){
        User user  =  new User(getProperty("base.properties",
                "login"), getProperty("base.properties",
                "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(new ContactListScreen(driver).isBtnPlusPresent());
    }

    @Test
    public void loginNegativeTest(){
        User user  =  new User(
                "", getProperty("base.properties",
                "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("Login or Password incorrect", 5));
    }

    @Test
    public void loginNegative_EmptyFields_Test(){
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("Login or Password incorrect", 5));
    }

    @Test
    public void loginNegative_WrongEmail_WithSpace_Test(){
        User user  =  new User(
                " ", getProperty("base.properties",
                "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("Login or Password incorrect", 5));
    }
}
