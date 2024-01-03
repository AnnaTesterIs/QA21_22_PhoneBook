package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        //if SignOut present --> logOut
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);

        //int i = (int)(System.currentTimeMillis()/1000%3600);
        User user = new User().setEmail("test"+i+"anna.book@gmail.com").setPassword("SAMAsdSAMa202ssss3@");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
    }

    @Test(description = "Bug report #12456, Fixed")

    public void registrationWrongEmail(){
        User user = new User().setEmail("test.anna.bookgmail.com").setPassword("SAMAsdSAMa202ssss3@");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
    }
    @Test
    public void registrationWrongPassword(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().setEmail("test"+i+"anna@bookgmail.com").setPassword("SAMAsdSAMa2");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
    }

    @Test
    public void registrationExistUser(){

        User user = new User().setEmail("test.anna.book@gmail.com").setPassword("SAMASAMaq2023@");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }
}
