package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().goToGroupPage("groups");
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size()+1);
    }

}
