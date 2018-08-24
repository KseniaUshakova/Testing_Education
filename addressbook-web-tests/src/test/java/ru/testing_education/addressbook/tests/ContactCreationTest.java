package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.getContactHelper().createNewContact(new ContactInfo("Maria", "Vladimirovna", "Smirnova", "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru", "test_group"), true);
    app.getNavigationHelper().goToHomePage("home");
  }


}
