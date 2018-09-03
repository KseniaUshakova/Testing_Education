package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test

  public void testContactModification() {

    app.getNavigationHelper().goToHomePage("home");
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createNewContact(new ContactInfo("Maria", "Vladimirovna", "Smirnova", "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru", "test_group"), true);
    }
    app.getNavigationHelper().goToHomePage("home");
    List<ContactInfo> before = app.getContactHelper().getContactList();


    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().initEditContact();
    ContactInfo newcontact = new ContactInfo(before.get(before.size()-1).getId(),
            "NewOne", "Vladimirovna", "Ushakova",
            "Spb", "111-11-11", "test@inbox.ru",
            "test2@mail.ru", null);
    app.getContactHelper().fillContactDetails(newcontact, false);
    app.getContactHelper().acceptUpdateContact();
    app.getNavigationHelper().goToHomePage("home");
    List<ContactInfo> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(newcontact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }

}
