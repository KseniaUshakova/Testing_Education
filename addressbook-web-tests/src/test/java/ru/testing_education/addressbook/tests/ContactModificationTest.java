package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTest extends TestBase {


  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage("home");
    if (app.contact().all().size()==00) {
      app.contact().create(new ContactInfo().withFirstName("Sveta").withMiddleName("Petrovna").withSecondName("Foqstand")
              .withAddress("Spb").withHomePhone("+8911-000-11-99").withEmail1("test@inbox.ru")
              .withEmail2("test2@mail.ru").withGroup("test_group"), true);
    }
  }

  @Test
  public void testContactModification() {

    app.goTo().homePage("home");
    Set<ContactInfo> before = app.contact().all();

    ContactInfo modifiedContact = before.iterator().next();

    int index = before.size()-1;

    ContactInfo newcontact = new ContactInfo().withId(modifiedContact.getId())
            .withFirstName("Anna").withMiddleName("Vladimirovna")
            .withSecondName("Modify")
            .withAddress("Moscow").withHomePhone("111-11-11")
            .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru");


    app.contact().modify(newcontact);
    app.goTo().homePage("home");

    Set<ContactInfo> after = app.contact().all();

    assertThat(after.size(), equalTo(before.size()));
    before.remove(modifiedContact);
    before.add(newcontact);

    assertThat(before, equalTo(after));

  }

}
