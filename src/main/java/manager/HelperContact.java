package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
}
