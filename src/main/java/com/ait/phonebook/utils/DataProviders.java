package com.ait.phonebook.utils;

import com.ait.phonebook.models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> addNewContact(){
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"Oliverko","Kann","1234567899","ion@gm.com","Berlin","goalkeeper"});
        list.add(new Object[]{"OBobererko","Kann","1884567899","iona@gm.com","Berlin","goalkeeper"});
        list.add(new Object[]{"Olivia","Kann","1234007899","ionaw@gm.com","Berlin","goalkeeper"});
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> addNewContactFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File
                ("src/test/resources/contacts.csv")));

        String line = reader.readLine();

        while (line != null) {

            String[] split = line.split(",");

            list.add(new Object[]{new Contact().setName(split[0]).setLastname(split[1]).setPhone(split[2])
                    .setEmail(split[3]).setAdress(split[4]).setDescription(split[5])});
            line= reader.readLine();
        }
        return list.iterator();
    }
}
