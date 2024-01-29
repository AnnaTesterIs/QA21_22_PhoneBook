package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {
    @BeforeClass(alwaysRun = true)
    public void preConditions() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("test.anna.book@gmail.com").setPassword("SAMASAMa2023@"));
        }
        logger.info("Before method finish logout");
    }

    @Test(dataProvider = "contactSuccess", dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFields(Contact contact) {
        int i = (int) (System.currentTimeMillis() / 1000 % 3600);
//        Contact contact = Contact.builder()
//                .name("Tony"+i)
//                .lastName("Stark")
//                .address("NY")
//                .phone("3565946" + i)
//                .email("stark" + i + "@gmail.com")
//                .description("all fields")
//                .build();
        logger.info("Tests run with data: --->"+contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        //app.getHelperContact().pause(2500);
        app.getHelperContact().saveButton();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }
    //firefox
    @Test(groups = {"smoke", "regress", "retest"})
    public void addContactSuccessRequiredFields(){
        logger.info("Start test with name `addContactSuccessRequiredFields`");
        logger.info("Test data --> name:`Max +i`& lastName:`Best`& phone: `6789000+i` & email: `test+i+@gmail.com` & address:`Berlin` & description:`best`");
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
        logger.info("Assert check is all fields filled");
        logger.info("Assert check is new contact is present in list contacts");

    }
    @Test
    public void addNewContactWrongName(){
        logger.info("Start test with name `addNewContactWrongName`");
        logger.info("Test data --> name:``& lastName:`Best`& phone: `6789000+i` & email: `test+i+@gmail.com` & address:`` & description:`empty name`");
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
        logger.info("Assert check is ADD Page Still Displayed");
    }

    @Test
    public void addNewContactWrongAddress(){
        logger.info("Start test with name `addNewContactWrongAddress`");
        logger.info("Test data --> name:`Max` & lastName:`Best`& phone: `6789000+i` & email: `test+i+@gmail.com` & address:`` & description:`empty address`");
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
        logger.info("Assert check is ADD Page Still Displayed");
    }

    @Test
    public void addNewContactWrongLastName(){
        logger.info("Start test with name `addNewContactWrongLastName`");
        logger.info("Test data --> name:`Max` & lastName:``& phone: `6789000+i` & email: `test+i+@gmail.com` & address:`` & description:`empty last name`");
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
        logger.info("Assert check is ADD Page Still Displayed");
    }

    @Test(dataProvider = "contactWrongPhone", dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongPhone(Contact contact){
        logger.info("Start test with name `addNewContactWrongPhone`");
        logger.info("Test data --> name:`Max` & lastName:`Best` & phone: `` & email: `test@gmail.com` & address:`` & description:`empty phone`");
        //Contact contact = Contact.builder()
               // .name("Max")
               // .lastName("Best")
              //  .phone("")
              //  .email("test@gmail.com")
               // .address("")
              //  .description("empty phone")
              //  .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
      //  app.getHelperContact().pause(2500);
        app.getHelperContact().saveButton();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert check is ADD Page Still Displayed");
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
        logger.info("Assert check is Alert with error text `Phone not valid: Phone number must contain only digits! And length min 10, max 15!`");
    }

    @Test
    public void addNewContactWrongEmail(){
        logger.info("Start test with name `addNewContactWrongEmail`");
        logger.info("Test data --> name:`Max` & lastName:`Best` & phone: `62345678978900` & email: `testgmail.com` & address:`Paris` & description:`wrong email`");
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
        logger.info("Assert check is ADD Page Still Displayed");
       Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));
        logger.info("Assert check is Alert with error text `Email not valid`");
    }


}
