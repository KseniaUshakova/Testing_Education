package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {

  @Test
  public void GroupDeletionTest() {

    app.getNavigationHelper().goToGroupPage("groups");

    if (!app.getGroupHelper().isThereAGroup()) {

      app.getGroupHelper().createGroup(new GroupData("test_group", null, null));

    }

    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returntoGroupPage("group page");
  }

}
