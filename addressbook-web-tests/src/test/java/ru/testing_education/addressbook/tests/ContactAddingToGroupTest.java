package ru.testing_education.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroupTest extends TestBase {


  Logger logger = LoggerFactory.getLogger(ContactAddingToGroupTest.class);

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
        Random random = new Random();
        app.goTo().page("groups");
        app.group().create(new GroupData().withGroupName("additional_group " +random.nextInt()));
      }
    }
  }


  @Test
  public void testAddingContactToGroup() {

    GroupData toGroup = app.db().groups().iterator().next();
    ContactInfo addedContact = app.db().contacts().iterator().next();
    Contacts contactListForChoosenGroupBefore = toGroup.getContacts();

    if (contactListForChoosenGroupBefore.contains(addedContact)) {

      app.goTo().homePage("home");
      Random random = new Random();
      ContactInfo newAddedContact = new ContactInfo().withFirstName("Contact " + random.nextInt());
      app.contact().create(newAddedContact, true);

      addedContact = app.db().contacts().stream()
              .filter(c -> c.getFirstName().equals(newAddedContact.getFirstName()))
            //  &&c.getId()==newAddedContact.getId())
              .findFirst()
              .get();
    }

    app.goTo().homePage("home");
    System.out.println("Adding of  contact=" + addedContact.getFirstName() + " to group = " + toGroup.getGroupName());

    app.contact().addToGroup(addedContact, toGroup);

    Contacts contactListForChoosenGroupAfter = app.db().groups().stream()
            .filter(g -> g.getGroupId() == toGroup.getGroupId())
            .findFirst().get().getContacts();

    assertThat(contactListForChoosenGroupAfter, equalTo(contactListForChoosenGroupBefore.withAdded(addedContact)));
    verifyContactListUI();

  }
}

