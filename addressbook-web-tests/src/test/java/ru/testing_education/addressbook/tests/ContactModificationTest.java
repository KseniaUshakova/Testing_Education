package ru.testing_education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTest extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withGroupName("test_group"));
    } else {

      Groups listOfExistedGroups = app.db().groups();
      int countOfTestGr = (int) listOfExistedGroups.stream()
              .map(GroupData::getGroupName).filter((n) -> n.equals("test_group")).count();

      if (countOfTestGr == 0) {
        app.group().create(new GroupData().withGroupName("test_group"));

      }
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage("home");

      app.contact().create(new ContactInfo().withFirstName("Sveta").withMiddleName("Petrovna").withSecondName("Foqstand")
              .withAddress("Spb").withHomePhone("+8911-000-11-99").withEmail1("test@inbox.ru")
              .withEmail2("test2@mail.ru").withGroup("test_group")
              .withPhoto(new File("src/test/resources/2063424_cats.jpg")), true);
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.db().contacts();

    ContactInfo modifiedContact = before.iterator().next();
    ContactInfo newcontact = new ContactInfo().withId(modifiedContact.getId())
            .withFirstName("Modified").withMiddleName("Modified").withSecondName("Modified")
            .withAddress("Spb" + "\n" + "Irinovskii 38" + "\n" + "kv 98")
            .withHomePhone("9098").withMobilePhone("+516-4575-6").withWorkPhone("3456 354")
            .withEmail1("test@inbox.ru")
            .withEmail2("")
            .withEmail3("easy@gh.com")
            .withGroup("test_group").withPhoto(new File("src/test/resources/2063424_cats.jpg"));
    app.goTo().homePage("home");
    app.contact().modify(newcontact);
    app.goTo().homePage("home");

    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(newcontact)));

    verifyContactListUI();

  }


}
