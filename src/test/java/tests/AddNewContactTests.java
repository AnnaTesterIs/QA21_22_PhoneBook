package tests;

import models.Contact;
import models.User;
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
                    .name("Max")
                   .lastName("Ma"+i+"best")
                    .phone("678900"+i)
                   .email("test"+i+"@gmail.com")
                    .address("Berlin")
                   .description("best")
                    .build();
            app.getHelperContact().openAddContactForm();
            app.getHelperContact().fillContactForm(contact);
            app.getHelperContact().saveButton();

        }

}
