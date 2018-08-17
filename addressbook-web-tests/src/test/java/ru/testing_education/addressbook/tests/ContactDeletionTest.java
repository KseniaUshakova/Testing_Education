package ru.testing_education.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage("home");
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage("home");

    }
    
}
