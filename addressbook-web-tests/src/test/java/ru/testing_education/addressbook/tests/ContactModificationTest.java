package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.ContactInfo;

public class ContactModificationTest extends TestBase {

  @Test

  public void testContactModification(){

    app.getNavigationHelper().goToHomePage("home");
    app.getContactHelper().selectOneContact();
    app.getContactHelper().initEditContact();
    app.getContactHelper().fillContactDetails(new ContactInfo("id23", "Vladimirovna", "Ushakova", "Spb", "111-11-11", "test@inbox.ru", "test2@mail.ru"));
    app.getContactHelper().acceptUpdateContact();
    app.getNavigationHelper().goToHomePage("home");


  }

}