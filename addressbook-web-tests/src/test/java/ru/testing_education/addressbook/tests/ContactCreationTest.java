package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.goTo().homePage("home");
    Set<ContactInfo> before = app.contact().all();

    ContactInfo newContact = new ContactInfo().withFirstName("Egor").withMiddleName("Andreyvich")
            .withSecondName("Fedorov").withAddress("Spb").withHomePhone("111-11-11")
            .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withGroup("test_group");
    app.contact().create(newContact, true);
    app.goTo().homePage("home");

    Set<ContactInfo> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

   // newContact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    newContact.withId(after.stream().mapToInt((g)->(g.getId())).max().getAsInt());

    before.add(newContact);

    Assert.assertEquals(before, after);


  }
}



