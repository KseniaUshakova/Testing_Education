package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @Test
  public void GroupDeletionTest() {

    app.getNavigationHelper().goToGroupPage("groups");
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(before.size()-2);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returntoGroupPage("group page");
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size()-1);

  }

}
