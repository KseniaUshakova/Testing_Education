package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.Comparator;
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

    group.setGroupId(after.stream().max((o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId())).get().getGroupId());
    before.add(group);

    Comparator<? super GroupData> byId = ((g1, g2) -> Integer.compare(g1.getGroupId(),g2.getGroupId()));

    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);


  }

}
