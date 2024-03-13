package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        //if SignOut presents ----> logout
        if (app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }

    }
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tretam0810@gmail.com", "Phone54321#");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());


    }

    @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tretam0810@gmail.com", "Phone54321#");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

    }
}
