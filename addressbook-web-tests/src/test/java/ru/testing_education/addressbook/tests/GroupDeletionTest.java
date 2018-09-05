package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToGroupPage("groups");
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
    }
  }

  @Test
  public void GroupDeletionTest() {

    List<GroupData> before = app.getGroupHelper().getGroupList();

    int index = before.size()-1;

    app.getGroupHelper().selectGroup(index);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returntoGroupPage("group page");
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), index);

    before.remove(index);

    Assert.assertEquals(before, after);

  }

}
