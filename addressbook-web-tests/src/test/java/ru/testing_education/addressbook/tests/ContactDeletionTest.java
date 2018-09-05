package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.List;

public class ContactDeletionTest extends TestBase {


  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage("home");
    if (app.contact().list().size()==00) {
      app.contact().create(new ContactInfo("Sveta", "Just",
              "Foqstand", "Spb", "111-11-11", "test@inbox.ru",
              "test2@mail.ru", "test_group"), true);
    }
  }


  @Test
  public void testContactDeletion() {

    app.goTo().homePage("home");
    List<ContactInfo> before = app.contact().list();
    int index = before.size()-1;
    app.contact().delete(index);
    app.goTo().homePage("home");
    List<ContactInfo> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(index);
    Assert.assertEquals(after, before);

  }



}
