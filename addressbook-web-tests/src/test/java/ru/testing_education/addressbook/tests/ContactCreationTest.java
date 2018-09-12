package ru.testing_education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().page("groups");

    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withGroupName("test_group"));
    } else {

      Groups listOfExistedGroups = app.group().all();
      int countOfTestGr = (int) listOfExistedGroups.stream().map(GroupData::getGroupName).filter((n) -> n.equals("test_group")).count();

      if (countOfTestGr == 0) {
        app.group().create(new GroupData().withGroupName("test_group"));

      }
    }
  }


  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> listContacts = new ArrayList<Object[]>();

    listContacts.add(new Object[]{new ContactInfo()
            .withFirstName("Anya").withSecondName("Ivanova")});

    listContacts.add(new Object[]{new ContactInfo()
            .withFirstName("Fedya").withMiddleName("Andreyvich").withSecondName("Smirnov")
            .withAddress("Moscow" + "\n" + "Irinovskii 38" + "\n" + "kv 98")
            .withHomePhone("111-11-11").withMobilePhone("+516-4575-6").withWorkPhone("3456 354")
            .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withEmail3("easy@gh.com")
            .withGroup("test_group").withPhoto(new File("src/test/resources/2063424_cats.jpg"))});

    return listContacts.iterator();

  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactInfo newContact) {

    app.goTo().homePage("home");
    Contacts before = (Contacts) app.contact().all();

    //  File photo = new File("src/test/resources/2063424_cats.jpg");
    //   ContactInfo newContact = new ContactInfo()
    //          .withFirstName("Fedya").withMiddleName("Andreyvich").withSecondName("Smirnov")
    //          .withAddress("Moscow"+"\n"+"Irinovskii 38"+"\n"+"kv 98")
    //          .withHomePhone("111-11-11").withMobilePhone("+516-4575-6").withWorkPhone("3456 354")
    //          .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withEmail3("easy@gh.com")
    //          .withGroup("test_group")
    //         .withPhoto(photo);

    app.contact().create(newContact, true);
    app.goTo().homePage("home");

    assertThat(app.contact().count(), equalTo(before.size() + 1));

    Contacts after = (Contacts) app.contact().all();
    assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((g) -> (g.getId())).max().getAsInt()))));

  }

  @Test

  public void testCurDir() {
    File curDir = new File(".");
    System.out.println(curDir.getAbsolutePath());
    File photo = new File("src/test/resources/2063424_cats.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}



