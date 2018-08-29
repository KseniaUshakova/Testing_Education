package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().goToGroupPage("groups");
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test_group", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size()+1);


    int max=0;

    for (GroupData g: after) {
      if (g.getGroupId()>max) {
        max = g.getGroupId();
      }
    }

    group.setGroupId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<java.lang.Object>(after));



  }

}
