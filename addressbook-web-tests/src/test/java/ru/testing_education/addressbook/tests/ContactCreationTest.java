package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
    assertThat(after.size(), equalTo(before.size() + 1));

    newContact.withId(after.stream().mapToInt((g)->(g.getId())).max().getAsInt());

    before.add(newContact);

    assertThat(before, equalTo(after));


  }
}



