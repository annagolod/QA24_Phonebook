package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openAddNewContactForm() {
        click(By.cssSelector("[href = '/add']"));
    }

    public void fillAddNewContactForm(Contact contact) {
        type(By.cssSelector("input[placeholder = 'Name']"), contact.getName());
        type(By.cssSelector("input[placeholder = 'Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder = 'Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder = 'email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder = 'Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder = 'description']"), contact.getDescription());
    }

    public void saveContact() {
        click(By.xpath("//button[.='Save']"));
    }
    //.add_form__2rsm2>button

    public String getLastAddedContactCard() {
        return wd.findElement
                        (By.xpath("//div[@class = 'contact-item_card__2SOIM'][last()]"))
                .getText();
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement el: list){
            if(el.getText().equals(name))
                return true;
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement el: list){
            if(el.getText().equals(phone))
                return true;
        }
        return false;
    }

    public int removeOneContact() {
        int before = countOfContacts();
        logger.info("Number of contacts before remove is --> " + before);
        removeContact();
        int after = countOfContacts();
        logger.info("Number of contacts after remove is --> " + after);
        return before - after;
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()= 'Remove']"));
        pause(3000);
    }

    private int countOfContacts() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }

    public void removeAllContacts() {
        while(countOfContacts() != 0){
            removeContact();
        }
    }

    public String getMessage() {

        return wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")).getText();
    }

    public void provideContacts() {
        if(countOfContacts() < 3){
            for(int i = 0; i < 3; i++){
                addOneContact();
            }
        }
    }

    private void addOneContact() {
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Harry" + i)
                .lastName("Potter")
                .email("harry"+i+"@gmail.com")
                .phone("125713645" + i)
                .address("Hogwards")
                .description("none")
                .build();
        openAddNewContactForm();
        fillAddNewContactForm(contact);
        saveContact();
        pause(2000);
    }
}
