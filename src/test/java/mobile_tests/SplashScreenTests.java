package mobile_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class SplashScreenTests extends TestBase{

    @Test
    public void splashScreenPositiveTest(){
        SplashScreen splashScreen = new SplashScreen(driver);
        Assert.assertTrue(splashScreen
                .validateVersionApp("Version 1.0.0", 2));
    }
}
