package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openAddContactForm() {
       // click(By.xpath("//a[text()='ADD']"));
        click(By.cssSelector("a[href= '/add']"));

    }


    public void fillContactForm(Contact contact) {
        //type(By.xpath("//input[name()]"), contact.getName());
        type(By.cssSelector("[placeholder = 'Name']"),contact.getName());
        type(By.cssSelector("[placeholder = 'Last Name']"), contact.getLastName());
        type(By.cssSelector("[placeholder = 'Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder = 'email']"), contact.getEmail());
        type(By.cssSelector("[placeholder = 'Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder = 'description']"), contact.getDescription());
    }

    public void saveButton() {
       //click(By.xpath("//*[@id=\"root\"]/div[2]/div/div/button/b"));
        click(By.cssSelector(".add_form__2rsm2>button"));

    }

    public boolean isContactAddedByName(String name) {
        List<WebElement>list = wd.findElements(By.cssSelector("h2"));
        for (WebElement el:list){
            if (el.getText().equals(name)){
                return true;
            }
        }
        return false;
    }


    public boolean isContactAddedByPhone(String phone) {
        List<WebElement>list = wd.findElements(By.cssSelector("h3"));
        for (WebElement el:list){
            if (el.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public int removeOneContact() {
        int before = counterOfContacts();
        logger.info("Number of Contacts list before remove is --->" + before);
        removeContact();
        int after = counterOfContacts();
        logger.info("Number of Contacts list after remove is --->" + after);
        return before - after;

    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(1000);
    }

    private int counterOfContacts() {
        //return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        int res = list.size();
        return res;
    }


    public void removeAllContacts() {
        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size()!=0){
            removeContact();
        }
    }

    public void provideContacts() {
        if(counterOfContacts()<3){
            for(int i = 0; i<3; i++){
                //addNewContact
                addOneContact();
            }
        }
    }

    private void addOneContact() {
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Harry" + i)
                .lastName("Potter")
                .address("Hogwarts")
                .email("harry" + i+"@gmail.com")
                .phone("34455678" + i)
                .description("friend")
                .build();
        openAddContactForm();
        fillContactForm(contact);
        saveButton();
        pause(500);
    }
}
