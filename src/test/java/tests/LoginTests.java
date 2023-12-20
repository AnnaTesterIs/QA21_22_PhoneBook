package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        //if SignOut present --> logOut
        if(app.getHelperUser().isLogged()){
          app.getHelperUser().logout();
        }
    }
    @Test
    public void  loginSuccessTest(){
app.getHelperUser().openLoginRegistrationForm();
app.getHelperUser().fillLoginRegistrationForm("test.anna.book@gmail.com", "SAMASAMa2023@");
app.getHelperUser().submitLogin();

Assert.assertTrue(app.getHelperUser().isLogged());
        //Assert.assertEquals();
       // Assert.assertNotEquals();
        //Assert.assertTrue();
        //Assert.assertFalse();

    }
    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("test.anna.bookgmail.com", "SAMASAMa2023@");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }
    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("test.anna.book@gmail.com", "SAMASAMa2023");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }
    @Test
    public void loginUnregistered(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tests.anna.book@gmail.com", "SAMASAMaa2023@");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }




}
