package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase {

    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("tretam0810@gmail.com").withPassword("Phone54321#"));
        }
    }

    @Test
    public void AddNewContactSuccessAll() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
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
    public void AddNewContactSuccess() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Jack" + i)
                .lastName("White")
                .phone("4567890" + i)
                .email("def" + i + "@nv.com")
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
    public void addNewContactWrongName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName("White")
                .phone("1234567354")
                .email("abc123@nv.com")
                .address("London")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isElementPresent(By.xpath("//*[text() = 'Save']")));
        Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.cssSelector("a.active[href='/add']")));
        Assert.assertFalse(app.getHelperContact().isElementPresent
                (By.cssSelector("a.active[href='/contacts']")));

        app.getHelperContact().click(By.cssSelector("a[href='/contacts']"));
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactWrongAddress() {
        Contact contact = Contact.builder()
                .name("John")
                .lastName("White")
                .phone("1234567925")
                .email("abc123@nv.com")
                .address("")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isElementPresent(By.xpath("//*[text() = 'Save']")));
        Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.cssSelector("a.active[href='/add']")));
        Assert.assertFalse(app.getHelperContact().isElementPresent
                (By.cssSelector("a.active[href='/contacts']")));

        app.getHelperContact().click(By.cssSelector("a[href='/contacts']"));
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactWrongLastName() {
        Contact contact = Contact.builder()
                .name("John")
                .lastName("")
                .phone("1234856354")
                .email("abc123@nv.com")
                .address("London")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isElementPresent(By.xpath("//*[text() = 'Save']")));
        Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.cssSelector("a.active[href='/add']")));
        Assert.assertFalse(app.getHelperContact().isElementPresent
                (By.cssSelector("a.active[href='/contacts']")));

        app.getHelperContact().click(By.cssSelector("a[href='/contacts']"));
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactWrongPhone() {
        Contact contact = Contact.builder()
                .name("John")
                .lastName("White")
                .phone("1234567")
                .email("abc123@nv.com")
                .address("London")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test
    public void addNewContactWrongEmail() {
        Contact contact = Contact.builder()
                .name("John")
                .lastName("White")
                .phone("1234567890")
                .email("abc123nv.com")
                .address("London")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid: должно иметь формат адреса электронной почты"));
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperContact().click(By.cssSelector("a[href='/contacts']"));
    }

}
