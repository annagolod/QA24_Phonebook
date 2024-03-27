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
    public void AddNewContactSuccessAll(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Contact contact = Contact.builder()
                .name("John")
                .lastName("Brown")
                .phone("1234567" + i)
                .email("abc" + i + "@nv.com")
                .address("New-York")
                .description("Boss")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertEquals(app.getHelperContact().getLastAddedContactCard(),
                contact.getName() + "\n" + contact.getPhone());
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void AddNewContactSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Contact contact = Contact.builder()
                .name("Jack" + i)
                .lastName("White")
                .phone("1234567" + i)
                .email("abc" + i + "@nv.com")
                .address("London")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertEquals(app.getHelperContact().getLastAddedContactCard(),
                contact.getName() + "\n" + contact.getPhone());
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }

    @Test
    public void addNewContactWrongName(){
    }

    @Test
    public void addNewContactWrongAddress(){
    }

    @Test
    public void addNewContactWrongLastName(){
    }

    @Test
    public void addNewContactWrongPhone(){
    }

    @Test
    public void addNewContactWrongEmail(){
    }


}
