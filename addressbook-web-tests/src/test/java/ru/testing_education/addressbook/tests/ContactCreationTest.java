package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToHomePage("home");

    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createNewContact(new ContactInfo("Vasy", "Vladimirovna", "Petrov", "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru", "test_group"), true);
    app.getNavigationHelper().goToHomePage("home");

    int after = app.getContactHelper().getContactCount();

    Assert.assertEquals(after, before+1);
  }
  }



