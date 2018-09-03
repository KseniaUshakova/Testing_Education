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

    ContactInfo newContact = new ContactInfo("Olga", "Petrovna", "Ivanova",
            "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru", "test_group");
    app.getContactHelper().createNewContact(newContact, true);
    app.getNavigationHelper().goToHomePage("home");
    List<ContactInfo> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);


    Comparator<? super ContactInfo> byContactId = new Comparator<ContactInfo>() {
      @Override
      public int compare(ContactInfo o1, ContactInfo o2) {
        return Integer.compare(o1.getId(), o2.getId());
      }
    };

    int max = after.stream().max(byContactId).get().getId();
    newContact.setId(max);
    before.add(newContact);


    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


  }
  }



