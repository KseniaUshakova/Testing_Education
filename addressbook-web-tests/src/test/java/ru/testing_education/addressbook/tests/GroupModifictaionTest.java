package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

public class GroupModifictaionTest extends TestBase {

  @Test

  public void testGroupModification() {

    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().initGroupModifictation();
    app.getGroupHelper().fillGroupForm(new GroupData("_Group100", "test_2", "test2"));
    app.getGroupHelper().submitModificaction();
    app.getGroupHelper().returntoGroupPage("group page");

  }
}
