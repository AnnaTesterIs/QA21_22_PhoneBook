package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openAddContactForm() {
        click(By.xpath("//a[text()='ADD']"));

    }


    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[name()]"), contact.getName());
        type(By.xpath("//*[@id=\"root\"]/div[2]/div/div/input[2]"), contact.getLastName());
        type(By.xpath("//*[@id=\"root\"]/div[2]/div/div/input[3]"), contact.getPhone());
        type(By.xpath("//*[@id=\"root\"]/div[2]/div/div/input[4]"), contact.getEmail());
        type(By.xpath("//*[@id=\"root\"]/div[2]/div/div/input[5]"), contact.getAddress());
        type(By.xpath("//*[@id=\"root\"]/div[2]/div/div/input[6]"), contact.getDescription());
    }

    public void saveButton() {
        click(By.xpath("//*[@id=\"root\"]/div[2]/div/div/button/b"));

    }
}
