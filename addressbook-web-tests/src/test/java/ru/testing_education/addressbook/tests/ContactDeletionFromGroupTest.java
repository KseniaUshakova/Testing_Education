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

public class ContactDeletionFromGroupTest extends TestBase {

  Logger logger = LoggerFactory.getLogger(ContactDeletionFromGroupTest.class);


  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().isEmpty()) {
      app.group().create(new GroupData().withGroupName("test_group"));
    }

    Groups groups = app.db().groups();

    for (GroupData group : groups) {

      if (group.getContacts().size() == 0) {

        Random random = new Random();
        ContactInfo newAddedContact =
                new ContactInfo().withFirstName("Contact " + random.nextInt()).inGroup(group);
        app.contact().create(newAddedContact, true);

      }
    }
  }


  @Test
  public void testDeletiongContactFromGroup() {

    GroupData fromGroup = app.db().groups().iterator().next();
    Contacts contactListForChoosenGroupBefore = fromGroup.getContacts();
    ContactInfo deletedContact = contactListForChoosenGroupBefore.iterator().next();

    app.goTo().homePage("home");
    app.contact().deleteFromGroup(deletedContact, fromGroup);

    Contacts contactListForChoosenGroupAfter = app.db().groups().stream()
            .filter(g -> g.getGroupId() == fromGroup.getGroupId())
            .findFirst().get().getContacts();

    assertThat(contactListForChoosenGroupAfter, equalTo(contactListForChoosenGroupBefore.without(deletedContact)));
    verifyContactListUI();

  }
}

