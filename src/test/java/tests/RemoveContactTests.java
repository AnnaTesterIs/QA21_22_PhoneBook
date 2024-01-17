package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{
    @BeforeMethod
    public void preConditions() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("test.anna.book@gmail.com").setPassword("SAMASAMa2023@"));
        }
        logger.info("Before method finish logout");
        app.getHelperContact().provideContacts();
        // if list <3 ==> add 3 contacts
        logger.info("Before method: if number Of Contacts <3 added 3 contacts");

    }
    @Test
    public void removeFirstContact(){
        //assert size list less by one
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);
        logger.info("Assert check that Number of Contacts list is one less");

    }
    @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();
        Assert.assertEquals(app.getHelperContact().getMessage(), "No Contacts here!");
        //"No contacts Here"
        logger.info("Assert check is text `No Contacts here`");
    }

}
