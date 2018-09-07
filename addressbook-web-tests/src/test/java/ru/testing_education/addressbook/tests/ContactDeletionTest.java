package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.List;
import java.util.Set;

public class ContactDeletionTest extends TestBase {


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
  public void testContactDeletion() {

    app.goTo().homePage("home");
    Set<ContactInfo> before = app.contact().all();
    ContactInfo deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage("home");
    Set<ContactInfo> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(deletedContact);
    Assert.assertEquals(after, before);

  }



}
