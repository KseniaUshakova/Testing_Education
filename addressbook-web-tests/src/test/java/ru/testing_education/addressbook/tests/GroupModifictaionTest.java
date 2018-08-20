package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

public class GroupModifictaionTest extends TestBase {

  @Test

  public void testGroupModification() {

    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModifictation();
    app.getGroupHelper().fillGroupForm(new GroupData("Chrome_gr1", "CHROME_TEST", "test2"));
    app.getGroupHelper().submitModificaction();
    app.getGroupHelper().returntoGroupPage("group page");

  }
}
