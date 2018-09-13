package ru.testing_education.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
      int countOfTestGr = (int) listOfExistedGroups.stream()
              .map(GroupData::getGroupName).filter((n) -> n.equals("test_group")).count();

      if (countOfTestGr == 0) {
        app.group().create(new GroupData().withGroupName("test_group"));

      }
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }

    Gson gson = new Gson();
    List<ContactInfo> contacts= gson.fromJson(json, new TypeToken<List<ContactInfo>>() {}.getType());
    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

  }


  @DataProvider
  public Iterator<Object[]> validContactsFromCsv() throws IOException {

    List<Object[]> listContacts = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {

      String[] split = line.split(";");
      listContacts.add(new Object[]{new ContactInfo()
              .withFirstName(split[0]).withMiddleName(split[1]).withSecondName(split[2])
              .withHomePhone(split[3]).withAddress(split[4]).withEmail1(split[5]).withPhoto(new File(split[6]))});
      line = reader.readLine();
    }

    // listContacts.add(new Object[]{new ContactInfo()
    //         .withFirstName("Fedya").withMiddleName("Andreyvich").withSecondName("Smirnov")
    //         .withAddress("Moscow" + "\n" + "Irinovskii 38" + "\n" + "kv 98")
    //         .withHomePhone("111-11-11").withMobilePhone("+516-4575-6").withWorkPhone("3456 354")
    //         .withEmail1("test@inbox.ru").withEmail2("test2@mail.ru").withEmail3("easy@gh.com")
    //         .withGroup("test_group").withPhoto(new File("src/test/resources/2063424_cats.jpg"))});

    return listContacts.iterator();

  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactInfo newContact) {

    app.goTo().homePage("home");
    Contacts before = (Contacts) app.contact().all();
    System.out.println(newContact.getFirstName());
    app.contact().create(newContact, true);
    app.goTo().homePage("home");
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = (Contacts) app.contact().all();
    assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((g) -> (g.getId())).max().getAsInt()))));

  }

  @Test(enabled = false)

  public void testCurDir() {
    File curDir = new File(".");
    System.out.println(curDir.getAbsolutePath());
    File photo = new File("src/test/resources/2063424_cats.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}



