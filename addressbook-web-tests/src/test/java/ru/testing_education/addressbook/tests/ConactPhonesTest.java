package ru.testing_education.addressbook.tests;

import org.hamcrest.MatcherAssert;
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
              .withAddress("Spb").withHomePhone("+(8911)00019").withMobilePhone("+516-4575-6").withWorkPhone("3456 354")
              .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withGroup("test_group"), true);
    }
  }

  @Test

  public void testContactsPhone() {

    app.goTo().homePage("home");
    ContactInfo contact = app.contact().all().iterator().next();
    ContactInfo contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(cleaned(contactInfoFromEditForm.getHomePhone()), equalTo(contact.getHomePhone()));
    assertThat(cleaned(contactInfoFromEditForm.getMobilePhone()), equalTo(contact.getMobilePhone()));
    assertThat(cleaned(contactInfoFromEditForm.getWorkPhone()), equalTo(contact.getWorkPhone()));
  }

    public String cleaned (String phone){

    return phone.replaceAll("\\s","").replaceAll("[-()]","");

    }



}







