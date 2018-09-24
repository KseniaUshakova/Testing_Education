package ru.testing_education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().isEmpty()) {
      app.group().create(new GroupData().withGroupName("test_group"));
    }

    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactInfo().withFirstName("Masha"), true);
    }

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    for (ContactInfo contact : contacts) {
      if (contact.getGroups().size() == groups.size()) {
        app.group().create(new GroupData().withGroupName("additional_group"));
      }
    }
  }


  @Test
  public void testAddingContactToGroup() {

    GroupData toGroup = app.db().groups().iterator().next();
    app.goTo().homePage("home");
    app.contact().selectByGroup(toGroup);
    Contacts contactListForChoosenGroupBefore = app.contact().all();
    Contacts before = app.db().contacts();
    ContactInfo addedContact = before.iterator().next();

    if (contactListForChoosenGroupBefore.contains(addedContact)) {

      app.goTo().homePage("home");
      Random random = new Random();
      ContactInfo newAddedContact = new ContactInfo().withFirstName("Contact " + random.nextInt());
      app.contact().create(newAddedContact, true);

      addedContact = app.db().contacts().stream()
              .filter(c -> c.getFirstName().equals(newAddedContact.getFirstName()))
              .findFirst()
              .get();
    }

    app.goTo().homePage("home");
    app.contact().addToGroup(addedContact, toGroup);

    app.goTo().homePage("home");
    app.contact().selectByGroup(toGroup);
    Contacts contactListForChoosenGroupAfter = app.contact().all();

    assertThat(contactListForChoosenGroupAfter, equalTo(contactListForChoosenGroupBefore.withAdded(addedContact)));
    verifyContactListUI();

  }
}

