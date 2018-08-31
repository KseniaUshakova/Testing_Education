package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToHomePage("home");

    List<ContactInfo> before = app.getContactHelper().getContactList();


    app.getContactHelper().createNewContact(new ContactInfo("Nata", "Petrovna", "Smirniva", "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru", "test_group"), true);
    app.getNavigationHelper().goToHomePage("home");


    List<ContactInfo> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size()+1);
  }
  }



