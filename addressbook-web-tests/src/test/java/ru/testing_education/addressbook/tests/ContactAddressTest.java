package ru.testing_education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage("home");
    if (app.contact().all().size()==00) {
      app.contact().create(new ContactInfo()
              .withFirstName("Sveta").withMiddleName("Petrovna").withSecondName("Foqstand")
              .withAddress("Spb"+"\n"+"Petrovka 38"+"\n"+"kv 76")
              .withHomePhone("+(8911)00019").withMobilePhone("+516-4575-6").withWorkPhone("3456 354")
              .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withEmail3("@gh.db")
              .withGroup("test_group"), true);
    }
  }

  @Test

  public void testContactAddress() {

    app.goTo().homePage("home");
    ContactInfo contact = app.contact().all().iterator().next();
    ContactInfo contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }

  public static String cleaned (String address){

    return address.replaceAll("\\s","")
            .replaceAll("\n","")
            .replaceAll("\r","");

  }
}







