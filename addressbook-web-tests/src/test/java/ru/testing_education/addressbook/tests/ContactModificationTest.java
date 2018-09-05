package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {


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
  public void testContactModification() {

    app.goTo().homePage("home");
    List<ContactInfo> before = app.contact().list();
    int index = before.size()-1;

    ContactInfo newcontact = new ContactInfo(before.get(index).getId(),
            "A", "Vladimirovna", "Modify",
            "Spb", "111-11-11", "test@inbox.ru",
            "test2@mail.ru", null);


    app.contact().modify(index, newcontact);
    app.goTo().homePage("home");

    List<ContactInfo> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(newcontact);
    Comparator<? super ContactInfo> byId = (c1,c2)-> (Integer.compare(c1.getId(), c2.getId()));
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before,after);

  }

}
