package tests;

import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{
    @BeforeMethod
    public void preConditions() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("test.anna.book@gmail.com").setPassword("SAMASAMa2023@"));
        }
        //app.getHelperContact().provideContacts(); //if list <3 ==> add 3 contacts
    }
    @Test
    public void removeFirstContact(){
        //assert size list less by one

    }
    @Test
    public void removeAllContacts(){
        //"No contacts Here"
    }

}
