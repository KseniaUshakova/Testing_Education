package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.getContactHelper().initContactCreation("add new");
    app.getContactHelper().fillContactDetails(new ContactInfo("Kate_Chrome", "Vladimirovna", "Ushakova", "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage("home");
  }


}
