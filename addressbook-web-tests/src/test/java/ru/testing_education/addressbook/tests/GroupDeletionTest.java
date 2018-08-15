package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void GroupDeletionTest() {

    app.gotoGrouppage("groups");
    app.selectGroup();
    app.deleteSelectedGroup();
    app.returntoGroupPage("groups");
  }

}
