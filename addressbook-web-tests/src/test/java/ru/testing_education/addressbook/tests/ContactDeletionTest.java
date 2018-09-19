package ru.testing_education.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTest extends TestBase {

  Logger logger = LoggerFactory.getLogger(ContactDeletionTest.class);


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactInfo().withFirstName("Sveta"), true);
    }
  }

  @Test
  public void testContactDeletion() {

    Contacts before = app.db().contacts();
    ContactInfo deletedContact = before.iterator().next();
    app.goTo().homePage("home");

    app.contact().delete(deletedContact);
    app.goTo().homePage("home");
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
