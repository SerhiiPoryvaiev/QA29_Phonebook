package com.ait.phonebook.tests;

import com.ait.phonebook.models.Contact;
import com.ait.phonebook.models.User;
import com.ait.phonebook.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    //precondition:user should be logged out
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }
    @Test(dataProvider = "addNewContact")
    public void addContactPositiveFromDataProviderTest(String name, String lastname, String phone,
                                                       String email, String adress, String desc){
        app.getContact().clickOnAddLink();

        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setLastname(lastname)
                .setPhone(phone)
                .setEmail(email)
                .setAdress(adress)
                .setDescription(desc));



        app.getContact().clickOnSaveButton();

        //  Assert.assertTrue(app.getContact().isContactDisplayedByText("Yurii"));
    }
    @Test
    public void existeUserRegistrationNegativeTest() {

        app.getUser().clickOnLoginLink();

        //enter emeil [placeholder='Email']
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));

        //click on registration button
        app.getUser().clickOnRegistrationButton();

        //assert Sign Out button is present
        Assert.assertTrue(app.getUser().isAlertPresent());
    }


}

