package com.ait.phonebook.tests;

import com.ait.phonebook.models.User;
import com.ait.phonebook.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    //precondition
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    //click on login link
    @Test
    public void loginRegisteredUserPositiveTest(){

        //click on login link
        app.getUser().clickOnLoginLink();

        //enter emeil [placeholder='Email']
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));


        //click login button
        app.getUser().clickOnLoginButton();
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());

        //accert

    }
  @Test
    public void loginRegisteredUserNegativeWithoutEmailTest(){

        app.getUser().clickOnLoginLink();

        app.getUser().fillLoginRegisterForm( new User()
                .setPassword(UserData.PASSWORD));

        app.getUser().clickOnLoginButton();
        Assert.assertTrue(app.getUser().isAlertPresent());

    }


}
