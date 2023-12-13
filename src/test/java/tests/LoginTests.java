package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
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
}
