package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("gr1", "test_2", "test2"));
    app.getGroupHelper().submitGroupCreation("submit");
    app.getGroupHelper().returntoGroupPage("group page");
  }

}
