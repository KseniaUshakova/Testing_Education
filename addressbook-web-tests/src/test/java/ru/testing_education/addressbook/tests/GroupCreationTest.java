package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goTo().page("groups");
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData("test_group", null, null);
    app.group().create(group);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size()+1);

    group.setGroupId(after.stream().max((o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId())).get().getGroupId());
    before.add(group);

    Comparator<? super GroupData> byId = ((g1, g2) -> Integer.compare(g1.getGroupId(),g2.getGroupId()));

    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);


  }

}
