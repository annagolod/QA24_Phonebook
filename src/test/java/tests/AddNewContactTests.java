package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("tretam0810@gmail.com").withPassword("Phone54321#"));
        }
    }

    @Test
    public void AddNewContactSuccess(){
        Contact contact = Contact.builder()
                .name("John")
                .lastName("Brown")
                .phone("1234567890")
                .email("abc@nv.com")
                .address("New-York")
                .description("Boss")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertEquals(app.getHelperContact().getLastAddedContactCard(),
                contact.getName() + "\n" + contact.getPhone());
    }
}
