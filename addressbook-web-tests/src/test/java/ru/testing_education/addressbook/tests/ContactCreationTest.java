package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.goTo().homePage("home");
    List<ContactInfo> before = app.contact().list();

    ContactInfo newContact = new ContactInfo().withFirstName("Egor").withMiddleName("Andreyvich")
            .withSecondName("Fedorov").withAddress("Spb").withHomePhone("111-11-11")
            .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withGroup("test_group");
    app.contact().create(newContact, true);
    app.goTo().homePage("home");

    List<ContactInfo> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

   // newContact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(newContact);

    Comparator<? super ContactInfo> byId = (c1, c2) -> (Integer.compare(c1.getId(), c2.getId()));

    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


  }
}



