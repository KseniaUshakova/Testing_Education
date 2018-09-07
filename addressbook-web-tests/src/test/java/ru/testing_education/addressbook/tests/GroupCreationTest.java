package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goTo().page("groups");
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withGroupName("test_group");
    app.group().create(group);
    Set<GroupData> after = app.group().all();

    Assert.assertEquals(after.size(), before.size()+1);

  //  Comparator<? super GroupData> byId = ((g1, g2) -> Integer.compare(g1.getGroupId(),g2.getGroupId()));

    group.withGroupId(after.stream().mapToInt((g)->(g.getGroupId())).max().getAsInt());

    before.add(group);

    Assert.assertEquals(before,after);


  }

}
