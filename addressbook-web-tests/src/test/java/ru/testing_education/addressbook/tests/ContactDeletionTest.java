package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage("home");
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createNewContact(new ContactInfo("Maria", "Vladimirovna", "Smirnova", "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru", "test_group"), true);
    }
    app.getNavigationHelper().goToHomePage("home");

    List<ContactInfo> before = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().goToHomePage("home");
    List<ContactInfo> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(before.size()-1);

    for (int i=0; i<after.size(); i++) {
      Assert.assertEquals(after, before);
    }

  }

}
