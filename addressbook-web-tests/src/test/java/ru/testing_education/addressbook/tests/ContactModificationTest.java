package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.Comparator;
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
    app.getContactHelper().initEditContact(before.size()+1);
    ContactInfo newcontact = new ContactInfo(before.get(before.size()-1).getId(),
            "A", "Vladimirovna", "Modify",
            "Spb", "111-11-11", "test@inbox.ru",
            "test2@mail.ru", null);
    app.getContactHelper().fillContactDetails(newcontact, false);
    app.getContactHelper().acceptUpdateContact();
    app.getNavigationHelper().goToHomePage("home");
    List<ContactInfo> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(newcontact);

    Comparator<? super ContactInfo> byId = (c1,c2)-> (Integer.compare(c1.getId(), c2.getId()));
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before,after);

  }

}
