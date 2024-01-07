package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {
    @BeforeClass
    public void preConditions() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("test.anna.book@gmail.com").setPassword("SAMASAMa2023@"));
        }
    }

        @Test
        public void addContactSuccessAllFields(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
            Contact contact = Contact.builder()
                    .name("Max"+i)
                   .lastName("Ma best")
                    .phone("6789000"+i)
                   .email("test"+i+"@gmail.com")
                    .address("Berlin")
                   .description("best")
                    .build();
            app.getHelperContact().openAddContactForm();
            app.getHelperContact().fillContactForm(contact);
            app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
           // app.getHelperContact().pause(2500);
            app.getHelperContact().saveButton();
            Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
            Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

        }

    @Test
    public void addContactSuccessRequiredFields(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        Contact contact = Contact.builder()
                .name("Max"+i)
                .lastName("Best")
                .phone("678900"+i)
                .email("test"+i+"@gmail.com")
                .address("Berlin")
                .description("best")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(2500);
        app.getHelperContact().saveButton();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }
    @Test
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Best")
                .phone("67894567400")
                .email("test@gmail.com")
                .address("")
                .description("empty name")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(2500);
        app.getHelperContact().saveButton();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("Max")
                .lastName("Best")
                .phone("6456784569")
                .email("test@gmail.com")
                .address("")
                .description("empty address")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(2500);
        app.getHelperContact().saveButton();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .name("Max")
                .lastName("")
                .phone("677899948900")
                .email("test@gmail.com")
                .address("Berlin")
                .description("empty last name")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
      //  app.getHelperContact().pause(2500);
        app.getHelperContact().saveButton();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongPhone(){
        Contact contact = Contact.builder()
                .name("Max")
                .lastName("Best")
                .phone("")
                .email("test@gmail.com")
                .address("")
                .description("empty phone")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
      //  app.getHelperContact().pause(2500);
        app.getHelperContact().saveButton();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
    }

    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("Max")
                .lastName("Best")
                .phone("62345678978900")
                .email("testgmail.com")
                .address("Paris")
                .description("wrong email")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
      //  app.getHelperContact().pause(2500);
        app.getHelperContact().saveButton();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
       Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));
    }


}
