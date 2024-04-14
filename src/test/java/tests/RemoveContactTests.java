package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("tretam0810@gmail.com").withPassword("Phone54321#"));
        }
        app.getHelperContact().provideContacts();
    }

    @Test(groups = {"smoke"})
    public void removeFirstContact(){
    //Assert size of list less by one
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);
    }

    @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();
        Assert.assertEquals(app.getHelperContact().getMessage(), "No Contacts here!");
    }
}
