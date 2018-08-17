package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void GroupDeletionTest() {

    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returntoGroupPage("group page");
  }

}
