package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.goTo().homePage("home");
    Contacts before = (Contacts) app.contact().all();

    File photo = new File("src/test/resources/2063424_cats.jpg");
    ContactInfo newContact = new ContactInfo()
            .withFirstName("Fedya").withMiddleName("Andreyvich").withSecondName("Smirnov")
            .withAddress("Moscow"+"\n"+"Irinovskii 38"+"\n"+"kv 98")
            .withHomePhone("111-11-11").withMobilePhone("+516-4575-6").withWorkPhone("3456 354")
            .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withEmail3("easy@gh.com")
            .withGroup("test_group")
            .withPhoto(photo);
    app.contact().create(newContact, true);
    app.goTo().homePage("home");

    assertThat(app.contact().count(), equalTo(before.size() + 1));

    Contacts after = (Contacts) app.contact().all();
    assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((g)->(g.getId())).max().getAsInt()))));

  }

  @Test

  public void testCurDir(){
    File curDir = new File (".");
    System.out.println(curDir.getAbsolutePath());
    File photo = new File("src/test/resources/2063424_cats.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}



