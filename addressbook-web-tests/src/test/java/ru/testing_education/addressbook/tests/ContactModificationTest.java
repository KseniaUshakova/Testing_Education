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
      app.contact().create(new ContactInfo().withFirstName("Sveta").withMiddleName("Petrovna").withSecondName("Foqstand")
              .withAddress("Spb").withHomePhone("+8911-000-11-99").withEmail1("test@inbox.ru")
              .withEmail2("test2@mail.ru").withGroup("test_group"), true);
    }
  }

  @Test
  public void testContactModification() {

    app.goTo().homePage("home");
    List<ContactInfo> before = app.contact().list();
    int index = before.size()-1;

    ContactInfo newcontact = new ContactInfo().withId(before.get(index).getId())
            .withFirstName("Anna").withMiddleName("Vladimirovna")
            .withSecondName("Modify")
            .withAddress("Moscow").withHomePhone("111-11-11")
            .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru");


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
