package ru.testing_education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConactPhonesTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage("home");
    if (app.contact().all().size()==00) {
      app.contact().create(new ContactInfo().withFirstName("Sveta").withMiddleName("Petrovna").withSecondName("Foqstand")
              .withAddress("Spb").withHomePhone("+8911-000-11-99").withMobilePhone("+51645756").withWorkPhone("3456354")
              .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withGroup("test_group"), true);
    }
  }

  @Test

  public void testContactsPhone(){

    app.goTo().homePage("home");
    ContactInfo contact = app.contact().all().iterator().next();
    ContactInfo contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }


}







