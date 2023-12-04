package com.ait.phonebook.tests;

import com.ait.phonebook.models.Contact;
import com.ait.phonebook.models.User;
import com.ait.phonebook.utils.ContactData;
import com.ait.phonebook.utils.DataProviders;
import com.ait.phonebook.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase{
   // @BeforeClass
    //public void beforeClass(){
     //   System.out.println("Before Class");
    //}

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }


        app.getUser().clickOnLoginLink();


        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();
    }

   @Test
    public void addContactPositiveTest(){
       app.getContact().clickOnAddLink();

       app.getContact().fillContactForm(new Contact()
               .setName(ContactData.NAME)
               .setLastname(ContactData.LAST_NAME)
               .setPhone(ContactData.PHONE)
               .setEmail(ContactData.EMAIL)
               .setAdress(ContactData.ADDRESS)
               .setDescription(ContactData.DESCRIPTION));



       app.getContact().clickOnSaveButton();

       Assert.assertTrue(app.getContact().isContactDisplayedByText(ContactData.NAME));
   }

    @AfterMethod
   public void postCondition(){

        app.getContact().removeContact();
    }


    @Test(dataProvider = "addNewContact",dataProviderClass = DataProviders.class)
    public void addContactPositiveFromDataProviderTest(String name, String lastname, String phone,
                                                       String email, String adress, String desc,Contact contact){
        app.getContact().clickOnAddLink();

        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setLastname(lastname)
                .setPhone(phone)
                .setEmail(email)
                .setAdress(adress)
                .setDescription(desc));



        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactDisplayedByText(contact.getName()));

        //  Assert.assertTrue(app.getContact().isContactDisplayedByText("Yurii"));
    }


    @Test(dataProvider = "addNewContactFromCSV",dataProviderClass = DataProviders.class)
    public void addContactPositiveFromCSVTest(Contact contact){
        app.getContact().clickOnAddLink();

        app.getContact().fillContactForm(contact);



        app.getContact().clickOnSaveButton();


    }

}
