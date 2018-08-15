package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGrouppage("groups");
    app.initGroupCreation("new");
    app.fillGroupForm(new GroupData("Group1", "test_2", "test2"));
    app.subminGroupCretaion("submit");
    app.returntoGroupPage("group page");
  }

}
