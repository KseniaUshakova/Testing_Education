package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage("home");
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createNewContact(new ContactInfo("Maria", "Vladimirovna", "Smirnova", "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru", "test_group"), true);
    }
    app.getNavigationHelper().goToHomePage("home");
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().goToHomePage("home");

  }

}
