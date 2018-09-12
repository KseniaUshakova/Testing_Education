package ru.testing_education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;

import java.io.File;

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
    Contacts before = app.contact().all();

    ContactInfo modifiedContact = before.iterator().next();
    ContactInfo newcontact = new ContactInfo().withId(modifiedContact.getId())
            .withFirstName("Modified").withMiddleName("Modified").withSecondName("Modified")
            .withAddress("Spb" + "\n" + "Irinovskii 38" + "\n" + "kv 98")
            .withHomePhone("9098").withMobilePhone("+516-4575-6").withWorkPhone("3456 354")
            .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withEmail3("easy@gh.com")
            .withGroup("test_group").withPhoto(new File("src/test/resources/2063424_cats.jpg"));

    app.contact().modify(newcontact);
    app.goTo().homePage("home");

    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(newcontact)));

  }

}
