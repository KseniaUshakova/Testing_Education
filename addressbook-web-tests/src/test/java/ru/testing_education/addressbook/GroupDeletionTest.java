package ru.testing_education.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void GroupDeletionTest() {

    gotoGrouppage("groups");
    selectGroup();
    deleteSelectedGroup();
    returntoGroupPage("groups");
  }

}
