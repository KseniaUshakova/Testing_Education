package ru.testing_education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage("home");
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactInfo()
              .withFirstName("Sveta").withMiddleName("Petrovna").withSecondName("Foqstand")
              .withAddress("Spb")
              .withHomePhone("+(8911)00019").withMobilePhone("+516-4575-6").withWorkPhone("3456 354")
              .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withEmail3("@gh.db"), true);
    }
  }

  @Test

  public void testContactEmail() {

    app.goTo().homePage("home");
    ContactInfo contact = app.contact().all().iterator().next();
    ContactInfo contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(mergeEmails(contactInfoFromEditForm), equalTo(contact.getAllEmails()));
  }

  private String mergeEmails(ContactInfo contact) {

    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((p) -> !p.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {

    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

  }


}







