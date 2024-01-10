package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        //if SignOut present --> logOut
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }

    @Test
    public void  loginSuccessTest(){
        logger.info("Start test with name `loginSuccessTest`");
        logger.info("Test data --> email: `test.anna.book@gmail.com` & password: `SAMASAMa2023@`");
app.getHelperUser().openLoginRegistrationForm();
app.getHelperUser().fillLoginRegistrationForm("test.anna.book@gmail.com", "SAMASAMa2023@");
app.getHelperUser().submitLogin();

Assert.assertTrue(app.getHelperUser().isLogged());
logger.info("Assert check is Element button `Sign Out` present");
        //Assert.assertEquals();
       // Assert.assertNotEquals();
        //Assert.assertTrue();
        //Assert.assertFalse();

    }

@Test
public void loginSuccessTest1(){
    logger.info("Test data --> email: `test.anna.book@gmail.com` & password: `SAMASAMa2023@`");
        User user = new User().setEmail("test.anna.book@gmail.com").setPassword("SAMASAMa2023@");
    app.getHelperUser().openLoginRegistrationForm();
    app.getHelperUser().fillLoginRegistrationForm(user);
    app.getHelperUser().submitLogin();
    Assert.assertTrue(app.getHelperUser().isLogged());
    logger.info("Assert check is Element button `Sign Out` present");

    }

    @Test
    public void loginWrongEmail(){
        logger.info("Test data --> email: `test.anna.bookgmail.com` & password: `SAMASAMa2023@`");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("test.anna.bookgmail.com", "SAMASAMa2023@");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert with error text `Wrong email or password`");
    }
    @Test
    public void loginWrongPassword(){
        logger.info("Test data --> email: `test.anna.book@gmail.com` & password: `SAMASAMa2023`");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("test.anna.book@gmail.com", "SAMASAMa2023");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert with error text `Wrong email or password`");
    }
    @Test
    public void loginUnregistered(){
        logger.info("Test data --> email: `tests.anna.book@gmail.com` & password: `SAMASAMaa2023@`");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tests.anna.book@gmail.com", "SAMASAMaa2023@");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert with error text `Wrong email or password`");
    }




}
