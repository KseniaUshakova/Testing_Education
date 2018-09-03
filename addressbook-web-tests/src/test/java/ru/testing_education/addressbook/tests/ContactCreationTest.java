package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToHomePage("home");
    List<ContactInfo> before = app.getContactHelper().getContactList();

    ContactInfo newContact = new ContactInfo("Egor", "Ande", "Ytr",
            "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru", "test_group");
    app.getContactHelper().createNewContact(newContact, true);
    app.getNavigationHelper().goToHomePage("home");
    List<ContactInfo> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);


    newContact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(newContact);

    Comparator<? super ContactInfo> byId = (c1,c2)-> (Integer.compare(c1.getId(), c2.getId()));

    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


  }
  }



