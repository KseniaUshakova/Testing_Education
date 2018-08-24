package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
    }

}
