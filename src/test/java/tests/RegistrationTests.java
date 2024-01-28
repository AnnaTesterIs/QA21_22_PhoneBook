package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        //if SignOut present --> logOut
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
        logger.info("Before method finish logout");
    }
    @Test
    public void registrationSuccess(){
        logger.info("Start test with name `registrationSuccess`");
        logger.info("Test data --> email: `test\"+i+\"anna.book@gmail.com` & password: `SAMAsdSAMa202ssss3@`");
        Random random = new Random();
        int i = random.nextInt(1000);
        //int i = (int)(System.currentTimeMillis()/1000%3600);
        User user = new User().setEmail("test"+i+"anna.book@gmail.com").setPassword("SAMAsdSAMa202ssss3@");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
        logger.info("Assert check is Element button `Sign Out` present");
        logger.info("Assert check is Element button `No Contacts here!` present");
    }

    @Test(description = "Bug report #12456, Fixed", groups = {"smoke"})

    public void registrationWrongEmail(){
        logger.info("Start test with name `registrationWrongEmail`");
        logger.info("Test data --> email: `test.anna.bookgmail.com` & password: `SAMAsdSAMa202ssss3@`");
        User user = new User().setEmail("test.anna.bookgmail.com").setPassword("SAMAsdSAMa202ssss3@");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
        logger.info("Assert check is Alert with error text `Wrong email or password format`");
    }
    @Test
    public void registrationWrongPassword(){
        logger.info("Start test with name `registrationWrongPassword`");
        logger.info("Test data --> email: `test\"+i+\"anna@bookgmail.com` & password: `SAMAsdSAMa2`");
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().setEmail("test"+i+"anna@bookgmail.com").setPassword("SAMAsdSAMa2");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
        logger.info("Assert check is Alert with error text `Wrong email or password format`");
    }

    @Test
    public void registrationExistUser(){
        logger.info("Start test with name `registrationExistUser`");
        logger.info("Test data --> email: `test.anna.book@gmail.com` & password: `SAMASAMaq2023@`");
        User user = new User().setEmail("test.anna.book@gmail.com").setPassword("SAMASAMaq2023@");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
        logger.info("Assert check is Alert with error text `User already exist`");
    }
}
