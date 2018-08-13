package ru.testing_education.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGrouppage("groups");
    initGroupCreation("new");
    fillGroupForm(new GroupData("Group1", "test_2", "test2"));
    subminGroupCretaion("submit");
    returntoGroupPage("group page");
  }

}
