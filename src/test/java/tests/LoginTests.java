package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        //if SignOut presents ----> logout
        if (app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method was log out");
        }

    }
    @Test
    public void loginSuccess(){
        //logger.info("Start test with name 'loginSuccess'");
        logger.info("Test data--> email: tretam0810@gmail.com, password: Phone54321#");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tretam0810@gmail.com", "Phone54321#");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check that button 'Sign Out' is present");

    }

    @Test
    public void loginSuccessModel(){
        logger.info("Test data--> email: tretam0810@gmail.com, password: Phone54321#");
        User user = new User().withEmail("tretam0810@gmail.com").withPassword("Phone54321#");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check that button 'Sign Out' is present");

    }
    @Test
    public void loginWrongEmail(){
        logger.info("Test data--> email: tretam0810gmail.com, password: Phone54321#");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tretam0810gmail.com","Phone54321#");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check that an alert 'Wrong email or password' appears");

    }

    @Test
    public void loginWrongPassword(){
        logger.info("Test data--> email: tretam0810@gmail.com, password: Phone54321");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tretam0810@gmail.com","Phone54321");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check that an alert 'Wrong email or password' appears");

    }
    @Test
    public void loginUnregisteredUser(){
        logger.info("Test data--> email: tretam@gmail.com, password: Phone54321#");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tretam@gmail.com","Phone54321#");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check that an alert 'Wrong email or password' appears");

    }

}
